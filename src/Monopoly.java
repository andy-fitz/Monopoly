/*		 
*  		  Team Name:	Bankrupt
* 		    
* 			Authors:    Andrew Fitzgerald  12376456
* 		     	 		Ray Hamill		   15342516
* 				 		Catherine Johnson  14475132
* 		      
* 			  Class:	Monopoly.java
*
* 	    Description:    
* 
*/

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;



public class Monopoly {
	
	public static final int MAX_PLAYERS = 6;
	private static int scores; 
	private boolean gameOver;
	private int playerTurn;
	private int num_of_players;
	private ArrayList<Player> players = new ArrayList<Player>();
	private UI ui = new UI(players);
	private String names[] = new String[6];
	private DicePair dice = new DicePair();
	private static final Color[] PLAYER_COLORS = {Color.RED,Color.BLUE,Color.YELLOW,Color.GREEN,Color.MAGENTA,Color.WHITE};
	private static final String[] COLOR_NAMES = {"red","blue","yellow","green","magenta","White"};
	private ArrayList<Square> squares = new ArrayList<Square>();
	
	private Deck CommunityChestDeck = new Deck("Community Chest");
	private Deck ChanceDeck = new Deck("Chance");
	
	private Square currentSquare;
	private boolean isRisky; 
	
	
	
	 Monopoly(){
		
		 //Setting up Squares ArrayList of square objects
		 //Of type square but contains only extended class objects.
		 for(int i=0; i < 40; i++){
			 if(SquareInfo.SQUARE_TYPES[i] == 0 ){
				 squares.add(new Go(i));
			 }
			 else if(SquareInfo.SQUARE_TYPES[i] == 1 ){
				 squares.add(new Property(i));
			 }
			 else if(SquareInfo.SQUARE_TYPES[i] == 2 ){
				 squares.add(new Station(i));
			 }
			 else if(SquareInfo.SQUARE_TYPES[i] == 3 ){
				 squares.add(new Utility(i));
			 }
			 else if(SquareInfo.SQUARE_TYPES[i] == 4 ){
				 squares.add(new CommunityChest(i));
			 }
			 else if(SquareInfo.SQUARE_TYPES[i] == 5 ){
				 squares.add(new Chance(i));
			 }
			 else if(SquareInfo.SQUARE_TYPES[i] == 6 ){
				 squares.add(new Jail(i));
			 }
			 else if(SquareInfo.SQUARE_TYPES[i] == 7 ){
				 squares.add(new FreeParking(i));
			 }
			 else if(SquareInfo.SQUARE_TYPES[i] == 8 ){
				 squares.add(new GoToJail(i));
			 }
			 else if(SquareInfo.SQUARE_TYPES[i] == 9 ){
				 squares.add(new Tax(i));
			 } 
		 }	
		 
		 
		 
		 
	}
	
	//Gets player count
	public void getNumOfPlayers(){
		boolean numDetect = false;
		String numPlayersStr = "";
		
		while(numDetect == false || this.num_of_players <=1 || this.num_of_players > MAX_PLAYERS){
			
			ui.displayString("Please enter the number of Players (MAX 6): ");
			numPlayersStr = ui.getCommand();
			
			try
			{
				this.num_of_players = Integer.parseInt(numPlayersStr);
				numDetect = true;
			}catch(NumberFormatException nfe){
				ui.displayString("Thats not a number");
				numDetect = false;
			}
		}
		ui.clearInfo();
		scores = num_of_players;
	}

	public void getPlayerNames(){
		for(int i=1; i <= this.num_of_players ;i++){
			ui.displayString("Enter Player " + i + "'s name: ");
			this.names[i-1] = ui.getCommand();
			ui.displayString(this.names[i-1]);
		}
		ui.clearInfo();
		return;
	}

	public void setPlayers(){
		
		for(int i=0; i < this.num_of_players ;i++){
			players.add(new Player());
		}

		for(int i=0; i < this.num_of_players ;i++){
			players.get(i).setName(names[i]);
		}
		
		ui.display();
	}
	
	public void displayPlayers(){
		ui.displayString("\n PLAYER LIST ");
		for(int i=0; i < num_of_players ;i++){
			ui.displayString("	Player " + (i+1) + " : " +players.get(i).getName());
		}	
	}
	
	public void setPlayerTurns(){
		ui.displayString("\nRolling dice for Players to set turn order: ");
		dice.turnOrderRoll();
		
		for(int i=0; i < num_of_players ;i++){
			players.get(i).setTurnRollValue(dice.turnOrderRoll());
			ui.displayString(players.get(i).getName() + " rolled a " + players.get(i).getTurnRollValue());
		}
		
		Collections.sort(players);
	}
	
	public void setPlayerColors(){
		for( int i = 0; i < num_of_players ;i++){
			players.get(i).setPlayerColor(PLAYER_COLORS[i], COLOR_NAMES[i]);
		}
		ui.display();
	}
	
	//The main Logic of the turn system is implemented here
	public void playGame()
	{
		String command;
		
		ArrayList<Debt> DebtsOwed;
		isRisky = false;
		
		
		while(gameOver == false){
			for(playerTurn=1; playerTurn <= num_of_players; playerTurn++){
				
				
				
				if(players.get(playerTurn-1).getStatus()){
					
					boolean done=false;
					DebtsOwed = new ArrayList<Debt>();
					command = "";
					
					boolean inDebt=false;
					
					if(scores==1){
						players.get(playerTurn-1).setPlayerScore(scores);
						ui.displayString("Congradulations " + players.get(playerTurn-1).getName() + ", You have won the game!");
						done = true;
					}
					
					
					ui.displayString("It is " + players.get(playerTurn-1).getName() + " (" + players.get(playerTurn-1).getPlayerColorName() + ") " + "'s turn." );
					currentSquare = squares.get(players.get(playerTurn-1).getPosition());
					
					while(done == false && players.get(playerTurn-1).getStatus()){								
						if(players.get(playerTurn-1).getJailStatus()){				
							
							if(dice.getEnable() == true){
								
								ui.displayString("You are in jail.\nTo play a 'Get out of Jail Free Card', enter 'card'.");
								ui.displayString("To try roll a double, enter 'roll'.\nTo pay �50 to get out of jail, enter 'pay'.");
								
								command = ui.getCommand();
								ui.displayString(command);
								
								if(command.equalsIgnoreCase("card")){
									if(players.get(playerTurn-1).getJailCardCount()>0){
										ui.displayString("You have used a 'Get Out of Jail Free Card' and been released.");
										players.get(playerTurn-1).useJailCard();
										players.get(playerTurn-1).getOutOfJail();
									}
									else{
										ui.displayString("You do not have a 'Get Out of Jail Free Card'.");
									}
								}
								
								else if(command.equalsIgnoreCase("roll")){
									
										dice.rollDice();								
										ui.displayString(dice.displayTotal());
										
										if(dice.doubles()==true){
											ui.displayString("You Rolled Doubles! You have been released from Jail.");	
											players.get(playerTurn-1).getOutOfJail();
											advancePlayer(dice.getTotal());
											dice.reset();
										}
										else{
											players.get(playerTurn-1).increaseJailTurnCount();
											if(players.get(playerTurn-1).getJailTurnCount()>2){
												ui.displayString("You have been in jail for 3 turns, you have been charged �50 and been released.");
												players.get(playerTurn-1).decreaseBalance(50);
												players.get(playerTurn-1).getOutOfJail();
												dice.reset();
											}
											else{
												ui.displayString("You did not roll doubles. You will remain in jail.");
											}
										}
								}
								
								else if(command.equalsIgnoreCase("pay")){
									ui.displayString("You have been released from Jail.");
									players.get(playerTurn-1).decreaseBalance(50);
									players.get(playerTurn-1).getOutOfJail();
									dice.reset();
								}
							}
							else{
								ui.displayString("Enter 'done' to end your turn.");
								command = ui.getCommand();
								ui.displayString(command);
								if(command.equalsIgnoreCase("done")){
												
									if(dice.getEnable() == false && inDebt == false){
										done = true;
									}
									 
									else{
										ui.displayString("You must roll before you end your turn");
									}								
								}
							}
						}
					
						if(!players.get(playerTurn-1).getJailStatus()){
					
							ui.displayString("What is your command?");
							command = ui.getCommand();
							ui.displayString(command);
						
							String[] splitCommand = command.split(" ");
						
							//help
							if(command.equalsIgnoreCase("help")){
								help();
							}
						
							//Roll Command
							else if(command.equalsIgnoreCase("roll")){
							
								if(dice.getEnable() == true){
								
									int debtAmount = 0;
									roll();
									ui.display();
									
									if(players.get(playerTurn-1).getJailStatus()){
										try{
											Thread.sleep(2000);
										} catch (InterruptedException e) {
											e.printStackTrace();
											}
										done=true;
									}
									
									currentSquare = squares.get(players.get(playerTurn-1).getPosition());
								
									//Landed on Owned Property/Utility/Station
									if(currentSquare.buyable && currentSquare.getOwned() && ! currentSquare.checkIfMortgaged()){
									 
									 	Player playerOwed = null;
									 
									 	for(int i = 0; i < players.size(); i++){
										 	if(players.get(i).checkProperty(currentSquare.getSquareName())){
											 	playerOwed = players.get(i);
											 	break;
										 	}
									 	}
									 
						
									 	//Property
									 	if(currentSquare.getType() == SquareInfo.TYPE_SITE){
										 
										 	debtAmount = currentSquare.getRentPrice();
										 
										 	if (playerOwed.checkForGroup(currentSquare.getColourNum()) && currentSquare.getRentLevel()== 0){
											 	debtAmount = debtAmount*2;
										 	}
										 
									 	}
									 	//Utility
									 	else if (currentSquare.getType() == SquareInfo.TYPE_UTILITY){
											 	debtAmount = dice.getTotal() * currentSquare.getRentPrice();			 
									 	}
									 	//Station
									 	else if (currentSquare.getType() == SquareInfo.TYPE_STATION){
										 	debtAmount = currentSquare.getRentPrice();
									 	}
									 
									 
									 	if(playerOwed.getName() != players.get(playerTurn-1).getName() && playerOwed != null){
										 	inDebt = true;
										 	Debt newDebt = new Debt(debtAmount, playerOwed);
										 	DebtsOwed.add(newDebt);
										 	ui.displayString(newDebt.displayDebt());
									 	}
									 
								 	}
									//Landed on Community Chest
									else if(currentSquare.getType() == SquareInfo.TYPE_COMMUNITY){
										Card pickedCard = CommunityChestDeck.draw();
										ui.displayString("Drawing a Community Chest card : ");
										ui.displayString(pickedCard.getCardMessage());
									
										try{
											wait(100);
										}catch(Exception e){}
									
										switch(pickedCard.getCardType()){
										//Go back to
										case 0: players.get(playerTurn-1).setPosition(1);
												ui.display();
												
												currentSquare = squares.get(players.get(playerTurn-1).getPosition());
												displaySquareInfo();
												break;
											
										//Advance to
										case 1:	int distance = 40 - players.get(playerTurn-1).getPosition();
												advancePlayer(distance);
												ui.display();
												currentSquare = squares.get(players.get(playerTurn-1).getPosition());
												displaySquareInfo();
												break;
											
										//Pay fine/fee
										case 2: int payment = pickedCard.getFigure();
												players.get(playerTurn-1).decreaseBalance(payment);
												break;
									
										//Collect Money
										case 3: 
											int collectionAmount = pickedCard.getFigure();
											int birthdayPresents = 0;
											
												if(pickedCard.findWord("birthday.")){
													for(int i = 0; i < players.size(); i++){
														if(players.get(i) != players.get(playerTurn-1)){
															if(players.get(i).getStatus()){
																players.get(i).decreaseBalance(10);
																birthdayPresents = birthdayPresents + collectionAmount; 	
															}
														}
													}
													players.get(playerTurn-1).IncreaseBalance(birthdayPresents);
												}
												else{
													players.get(playerTurn-1).IncreaseBalance(collectionAmount);	
												}
												
												
												
												
												
												
												
												break;
											
										// Option Pay fine or take a chance
										case 4: boolean choiceMade = false;
												ui.displayString("Enter <pay> to pay the fine or enter <chance> to take a chance");
											
												while(choiceMade == false){
												
													String choice = ui.getCommand();
													if(choice.equalsIgnoreCase("pay")){
														ui.displayString("You payed the fine. �10 was removed from your balance");
														players.get(playerTurn-1).decreaseBalance(10);
														choiceMade = true;
													}
													else if(choice.equalsIgnoreCase("chance")){
														choiceMade = true;
														isRisky = true;
													}
													else{
														ui.displayString("Not a suitable input, try again!");
													}
												
												}
										// Get out of jail free card		
										case 5: players.get(playerTurn-1).acquireJailCard();
												break;
											
										//Go to Jail
										case 6: players.get(playerTurn -1).goToJail();
												ui.display();
												currentSquare = squares.get(players.get(playerTurn-1).getPosition());
												break;
												
										//Building Repairs
										case 7: // Don't need for Community chest
												break;
											
										default: System.out.print("Invalid card type");
									
										}
										if(pickedCard.getCardType() != 4){
											CommunityChestDeck.addToBottom(pickedCard);
										}
									}
								
									//Landed on Chance
									if (currentSquare.getType() == SquareInfo.TYPE_CHANCE || isRisky == true ){
										
										isRisky = false;
										Card pickedCard = ChanceDeck.draw();
										ui.displayString("Drawing a chance card : ");
										ui.displayString(pickedCard.getCardMessage());
									
										try{
											wait(100);
										}catch(Exception e){}
									
										switch(pickedCard.getCardType()){
									
										//Go back to
										case 0: 
												int newPosition = players.get(playerTurn-1).getPosition() - 3 ;
												
												if(newPosition < 0){
													newPosition = 40 + newPosition;
												}
												
												players.get(playerTurn-1).setPosition(newPosition);
												ui.display();
												
												currentSquare = squares.get(players.get(playerTurn-1).getPosition());
												displaySquareInfo();
												
												if(currentSquare.getType() == SquareInfo.TYPE_COMMUNITY){
													commChest();
												}
								
												break;
									
										//Advance To
										case 1: String[] destinations = {"Pall","Marylebone", "Trafalgar", "Mayfair.", "Go."};
												int[] positions = {11,15,24,39,40};
											
												for(int i= 0; i < 5; i++)
												{
													if(pickedCard.findWord(destinations[i])){
													
														if(positions[i] >= players.get(playerTurn -1).getPosition()){
															advancePlayer(positions[i] - players.get(playerTurn -1).getPosition());
														}
														else{
															advancePlayer(40 - players.get(playerTurn -1).getPosition() + positions[i]);
														}
													
													}
												}
												ui.display();
												
												currentSquare = squares.get(players.get(playerTurn-1).getPosition());
												displaySquareInfo();
												break;
											
										//Pay Fine/Fee		
										case 2: int payment = pickedCard.getFigure();
												players.get(playerTurn-1).decreaseBalance(payment);
												break;
											
										//Collect Money		
										case 3: int collectionAmount = pickedCard.getFigure();
												players.get(playerTurn-1).IncreaseBalance(collectionAmount);
												break;
											
										//Option 
										case 4:  // Don't need for Chance
												break;
											
										//Get out of Jail Free Card
										case 5: players.get(playerTurn-1).acquireJailCard();
												break;
									
										// Go to Jail
										case 6: players.get(playerTurn -1).goToJail();
												ui.display();
												currentSquare = squares.get(players.get(playerTurn-1).getPosition());
												break;
											
										// Building/Street Repairs
										case 7: ArrayList<Integer> repairCosts = pickedCard.getTwoFigures();                    // = {houseCost, hotelCost}
											 	ArrayList<Integer> buildingCounts = players.get(playerTurn-1).countBuildings(); // = {houseCount, HotelCount}
											 
											 	int houseRepairTotal = repairCosts.get(0) * buildingCounts.get(0);
											 	int hotelRepairTotal = repairCosts.get(1) * buildingCounts.get(1);
											 	int totalRepairCost = houseRepairTotal + hotelRepairTotal;
											 
											 	players.get(playerTurn-1).decreaseBalance(totalRepairCost);
											 	ui.displayString("You payed �" + totalRepairCost + " in reapairs.");
											 	break;
									
										default: System.out.print("Invalid card type");
											 
										}
									
										ChanceDeck.addToBottom(pickedCard);
									}
									
									if (currentSquare.getType() == SquareInfo.TYPE_TAX){
										players.get(playerTurn-1).decreaseBalance(currentSquare.getTaxAmount());
										ui.displayString("You have been charged �" + currentSquare.getTaxAmount() + " in tax.");
									}
								
									if (currentSquare.getType() == SquareInfo.TYPE_GOTO_JAIL){
										 players.get(playerTurn-1).goToJail();
										 dice.disableDice();
										 ui.display();
									 }
								}
							
								else{
									ui.displayString("You have already rolled, you cannot roll again.");
								}
							}
						
							//Buy Command
							else if(command.equalsIgnoreCase("buy")){
									buy();
							}
						
							//Property Query Command
							else if(command.equalsIgnoreCase("property")){
							
									ui.displayString("\nYou own the following Property: \n");
									ui.displayString("COLOUR\tRENT\tNAME");
								
								for(int i=0; i<players.get(playerTurn-1).getOwnedProperty().size(); i++){
									String mortgaged = "";
									if(players.get(playerTurn-1).getOwnedProperty().get(i).checkIfMortgaged()){
										mortgaged = " (m) ";
									}
								
									ui.displayString(players.get(playerTurn-1).getOwnedProperty().get(i).getColour() + "\t�" + players.get(playerTurn-1).getOwnedProperty().get(i).getRentPrice() + mortgaged + "\t" + players.get(playerTurn-1).getOwnedProperty().get(i).getSquareName() );
								}
							
								ui.displayString("");
							
							}
						
							//Balance Command
							else if(command.equalsIgnoreCase("balance")){
							
								String playerBalance = Integer.toString(players.get(playerTurn-1).getBalance());
								ui.displayString("Your balance: �" + playerBalance);
							
							}
						
							//Done Command
							else if(command.equalsIgnoreCase("done")){
							
							
								if(players.get(playerTurn - 1).getBalance()<0){
									ui.displayString("You have a negative balance.");
									ui.displayString("You must demolish or mortgage before you end your turn");
								}
								else{
									if(dice.getEnable() == false && inDebt == false){
										done = true;
									}
									 
									if(dice.getEnable() == true){
										ui.displayString("You must roll before you end your turn");
									}
								}
							
							}
						
							
							//Build Command
							else if(splitCommand[0].equalsIgnoreCase("build")){
							
								if(splitCommand.length == 3){
									int propListPos = players.get(playerTurn-1).findProperty(splitCommand[1]);
								
									if(players.get(playerTurn-1).getOwnedProperty().get(propListPos).getType() == SquareInfo.TYPE_SITE){
										if(propListPos != -1){
											if(! players.get(playerTurn-1).getOwnedProperty().get(propListPos).checkIfMortgaged()){
												int numHouses = Integer.parseInt(splitCommand[2]);
												if(players.get(playerTurn-1).getOwnedProperty().get(propListPos).getRentLevel() + numHouses <6){
													if(players.get(playerTurn-1).checkForGroup(players.get(playerTurn-1).getOwnedProperty().get(propListPos).getColourNum())){
														for(int i=0; i < numHouses; i++){
															players.get(playerTurn-1).getOwnedProperty().get(propListPos).increaseRentLevel();
															players.get(playerTurn-1).decreaseBalance(players.get(playerTurn-1).getOwnedProperty().get(propListPos).getHousePrice());
															ui.display();
														}
														if(numHouses==5){
															ui.displayString("You have built a hotel for �" + 5*players.get(playerTurn-1).getOwnedProperty().get(propListPos).getHousePrice() + ".");
														}
														else if(numHouses<5 && numHouses+players.get(playerTurn-1).getOwnedProperty().get(propListPos).getRentLevel()==5){
															ui.displayString("You have built "+ numHouses +" houses at �" + players.get(playerTurn-1).getOwnedProperty().get(propListPos).getHousePrice() + " each. /nYou now own a hotel.");
														}
														else{
															ui.displayString("You have built "+ numHouses +" houses at �" + players.get(playerTurn-1).getOwnedProperty().get(propListPos).getHousePrice() + " each. ");
														}
													}
													else{
														ui.displayString("You must own all Properties of this color to build here.");
													}
												}
												else{
													ui.displayString("You can only build 4 houses or one Hotel on a property.");
												}
											}
											else{
												ui.displayString("You cannot build houses on a mortgaged Property.");
											}
										}
										else{
											ui.displayString("You dont own this Property. You cannot build on it!");
										}
									}
									else{
										ui.displayString("You can only build on Property squares.");
									}
								}
								else{
									ui.displayString("To Build house/Hotels enter : Build <short property name> <number of house/hotels>.\n");
								}
							}
						
							//Demolish Command
							else if(splitCommand[0].equalsIgnoreCase("Demolish")){
							
								if(splitCommand.length ==3){
									int propListPos = players.get(playerTurn-1).findProperty(splitCommand[1]);
								
									if(players.get(playerTurn-1).getOwnedProperty().get(propListPos).getType() == SquareInfo.TYPE_SITE){
										if(propListPos != -1){
										
											int numHouses = Integer.parseInt(splitCommand[2]);
											if(players.get(playerTurn-1).getOwnedProperty().get(propListPos).getRentLevel() - numHouses >= 0){
												for(int i=0; i < numHouses; i++){
												
													players.get(playerTurn-1).getOwnedProperty().get(propListPos).decreaseRentLevel();
													players.get(playerTurn-1).IncreaseBalance(players.get(playerTurn-1).getOwnedProperty().get(propListPos).getHousePrice()/2);
													ui.display();
												}
												ui.displayString("You have demolished "+numHouses+"houses, and the bank has returned "+ players.get(playerTurn-1).getOwnedProperty().get(propListPos).getHousePrice()/2 + " to your balance.");
											}
											else{
												ui.displayString("You cannot demolish more houses/Hotels than you own.");
											}
										}
										else{
											ui.displayString("You dont own this Property. You cannot build on it!");
										}
									}
									else{
										ui.displayString("Do you see any houses here? try again.");
									}
								}
								else{
									ui.displayString("To Demolish house/Hotels enter : Demolish <short property name> <number of house/hotels>.\n");
								}
							
							}
						
							//Mortgage Command
							else if(splitCommand[0].equalsIgnoreCase("Mortgage")){
							
								if(splitCommand.length == 2){
									int propListPos = players.get(playerTurn-1).findProperty(splitCommand[1]);
								
									if(propListPos != -1){
										if(! players.get(playerTurn-1).getOwnedProperty().get(propListPos).checkIfMortgaged()){
											if(players.get(playerTurn-1).getOwnedProperty().get(propListPos).getRentLevel() == 0){
											
												players.get(playerTurn-1).mortgage(propListPos);
												int mortgageValue = players.get(playerTurn-1).getOwnedProperty().get(propListPos).getMortgageValue();
												String mortgageName =  players.get(playerTurn-1).getOwnedProperty().get(propListPos).getSquareName();
												players.get(playerTurn-1).IncreaseBalance(mortgageValue);
											
												ui.displayString(mortgageName + " has been mortgaged for �" + mortgageValue);
											}
											else{
												ui.displayString("You cannot mortgage a Property with houses on it.");
												ui.displayString("You must first demolish the houses.");
											}	
										}
										else{
											ui.displayString(" This Property is already Mortgaged! ");
										}
									}
									else{
										ui.displayString("You dont own that Property so you cannot mortgage it.");
									}
								}
								else{
									ui.displayString("To Mortgage a property type: Mortgage <short property name>.\n");
								}
							
							}
							//Redeem Command
							else if(splitCommand[0].equalsIgnoreCase("Redeem")){
							
								if(splitCommand.length == 2){
									int propListPos = players.get(playerTurn-1).findProperty(splitCommand[1]);
									double mortValue = players.get(playerTurn-1).getOwnedProperty().get(propListPos).getMortgageValue();
									double payment =  (mortValue/100.0)*110.0;
									int roundedPayment = (int) payment;
								
									System.out.println(players.get(playerTurn-1).getOwnedProperty().get(propListPos).getMortgageValue());
									System.out.println(payment );
								
								
								
								
									if(propListPos != -1){
										if(players.get(playerTurn-1).getOwnedProperty().get(propListPos).checkIfMortgaged()){
											if(players.get(playerTurn-1).getBalance() > roundedPayment){
											
												players.get(playerTurn-1).decreaseBalance(roundedPayment);
												players.get(playerTurn-1).redeem(propListPos);
											
												String RedeemName =  players.get(playerTurn-1).getOwnedProperty().get(propListPos).getSquareName();
												ui.displayString("You payed �" + roundedPayment + " to Redeem " + RedeemName);
											
											}
											else{
												ui.displayString("You don't have enough money to redeem this property");
											}
										}
										else{
											ui.displayString("This Property is not Mortgaged!");
										}	
									}
									else{
										ui.displayString("You dont own that Property so you cannot Redeem it.");
									}
								}
								else{
									ui.displayString("To redeem a property type: Redeem <short property name>");
								}
							}
						
							//Bankrupt command
							else if(command.equalsIgnoreCase("Bankrupt") || command.equalsIgnoreCase("Quit")){
								players.get(playerTurn-1).resetProperty();
								players.get(playerTurn-1).removePlayer();
								players.get(playerTurn-1).setPlayerScore(scores);
								scores--;
							
								ui.displayString("You have declared Bankruptcy or decided to quit the game.");
								ui.displayString("Your assets have been returned to the bank");
								ui.displayString("You finished in position: " + players.get(playerTurn-1).getPlayerScore());
							
							
								ui.displayString("Enter 'okay' to let the rest of the players finish the game. Better luck next time.");
								if(scores==1){
									gameOver=true;
								}
								else{
									ui.getCommand();
								}
							}
						
							else{ 
								errorMessage();
							}	
						
							if(inDebt){
								while(! DebtsOwed.isEmpty()){
								
									players.get(playerTurn-1).decreaseBalance(DebtsOwed.get(0).getAmountOwed());
								
									for(int j = 0; j < players.size(); j++){
										 if(players.get(j).checkProperty(currentSquare.getSquareName())){
										 	players.get(j).IncreaseBalance(DebtsOwed.get(0).getAmountOwed());
										 	break;
									 	}	 
									}
									ui.displayString("You paid "+ DebtsOwed.get(0).getCreditor().getName()+ " �" + DebtsOwed.get(0).getAmountOwed() + " in rent.");
									DebtsOwed.remove(0);   
								}
								inDebt=false;
							}
						}
						
						}
					
						dice.reset();
						ui.clearInfo();
						
					}
				
				
				}
			
			}
			declareWinner();
		}
		
	
	public void declareWinner(){
		for(int i=0; i<num_of_players; i++){
			if(players.get(i).getStatus()){
				ui.displayString("Congratulations! " +players.get(i).getName()+" is the winner!");
				ui.displayString("Your total assests are worth: �" +players.get(i).calculateTotalAssets());
			}
		}
	}
	
	//Display a list of usable commands
	public void help(){
		
		String[] commandList =  {"help", "roll", "buy" , "property" , "balance" , "done" ,"quit", "mortgage", "demolish", "redeem", "build", "Bankrupt"};
		
		ui.displayString("LIST OF COMMANDS: ");
		
		for(int i=0; i < commandList.length; i++){
			ui.displayString(commandList[i]);
		}
	}
	
	//Method to Move the Player
	public void advancePlayer(int distance){
		for(int i = 1; i <= distance; i++){
			
			if(players.get(playerTurn-1).moveToken(1) == true){
				
				ui.displayString("You Passed Go \n Collect �200" );
				players.get(playerTurn-1).IncreaseBalance(200);
			}
			
			ui.display();
			try{
				Thread.sleep(100);	
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	//Method to Roll the Dice
	public int roll(){
			
	    if (players.get(playerTurn -1).getBalance()>=0){	
			dice.rollDice();
			
			if(dice.doubles()==true){
				ui.displayString("You Rolled Doubles!");	
			}
			
			ui.displayString(dice.displayTotal());
			
			if(dice.getDoubleCount()>2){
				ui.displayString("You have rolled three doubles in a row. Go to Jail.");
				players.get(playerTurn -1).goToJail();
			}
			else{
				advancePlayer(dice.getTotal());
				displaySquareInfo();
			}
			
	    }
	
		else {
			if (players.get(playerTurn-1).calculateTotalAssets()<0) {
				ui.displayString("You have a negative balance.");
				ui.displayString("You must demolish or mortgage before you roll.");
				ui.getCommand();
		    }
			else {
				ui.displayString("You have no remaining assets. You must declare banruptcy");
				dice.reset();
			}
		}	
	
		return dice.getTotal();
	}
	
	
	//Buy Property
	public void buy(){
		
		Square purchase = squares.get(players.get(playerTurn-1).getPosition());
		
		if(purchase.buyable==true){
			if(purchase.getOwned() == false){
				
				players.get(playerTurn-1).addProperty(purchase);
				players.get(playerTurn-1).decreaseBalance(purchase.getPrice());
				squares.get(players.get(playerTurn-1).getPosition()).setOwned(true);
				calculateRents(purchase);
				
				ui.displayString("You have Successfully bought " + purchase.getSquareName() + " for �" + purchase.getPrice());
			}
			else if ( players.get(playerTurn-1).checkProperty(purchase.getSquareName())){
				ui.displayString("You Already Own This Property");
			}
			else{
				ui.displayString("Another Player Already Owns This Property");
			}
		}
		else{
			ui.displayString("This is not a buyable space");
		}
		 
	}
	
	//Works out rent multipliers for Station and Utilities.
	public void calculateRents(Square purchase){
		
		if(purchase.getType() == SquareInfo.TYPE_UTILITY){
			ArrayList<Integer> utilitiesIndex = players.get(playerTurn - 1).utilityCheck();
			
			
			if(utilitiesIndex.size() == 2){
				players.get(playerTurn - 1).getOwnedProperty().get(utilitiesIndex.get(0)).increaseRentLevel();
				players.get(playerTurn - 1).getOwnedProperty().get(utilitiesIndex.get(1)).increaseRentLevel();
			}
			
		}
		else if(purchase.getType() == SquareInfo.TYPE_STATION){
			ArrayList<Integer> stationIndex = players.get(playerTurn - 1).findStationIndex();
			
			
			for(int i = 1; i < players.get(playerTurn - 1).stationCount(); i++){
				for(int j=0; j<stationIndex.size(); j++){
					players.get(playerTurn - 1).getOwnedProperty().get(stationIndex.get(j)).increaseRentLevel();
				}
			}
		}
		
	}
	
	
	public void commChest(){
		Card pickedCard = CommunityChestDeck.draw();
		ui.displayString("Drawing a Community Chest card : ");
		ui.displayString(pickedCard.getCardMessage());
		
		try{
			wait(100);
		}catch(Exception e){}
	
		switch(pickedCard.getCardType()){
		//Go back to
		case 0: players.get(playerTurn-1).setPosition(1);
				ui.display();
				
				currentSquare = squares.get(players.get(playerTurn-1).getPosition());
				displaySquareInfo();
				break;
			
		//Advance to
		case 1:	int distance = 40 - players.get(playerTurn-1).getPosition();
				advancePlayer(distance);
				ui.display();
				currentSquare = squares.get(players.get(playerTurn-1).getPosition());
				displaySquareInfo();
				break;
			
		//Pay fine/fee
		case 2: int payment = pickedCard.getFigure();
				players.get(playerTurn-1).decreaseBalance(payment);
				break;
	
		//Collect Money
		case 3: int collectionAmount = pickedCard.getFigure();
				players.get(playerTurn-1).IncreaseBalance(collectionAmount);
				break;
			
		// Option Pay fine or take a chance
		case 4: boolean choiceMade = false;
				ui.displayString("Enter <pay> to pay the fine or enter <chance> to take a chance");
			
				while(choiceMade == false){
				
					String choice = ui.getCommand();
					if(choice.equalsIgnoreCase("pay")){
						ui.displayString("You payed the fine. �10 was removed from your balance");
						players.get(playerTurn-1).decreaseBalance(10);
						choiceMade = true;
					}
					else if(choice.equalsIgnoreCase("chance")){
						choiceMade = true;
						isRisky = true;
					}
					else{
						ui.displayString("Not a suitable input, try again!");
					}
				
				}
		// Get out of jail free card		
		case 5: players.get(playerTurn-1).acquireJailCard();
				break;
			
		//Go to Jail
		case 6: players.get(playerTurn -1).goToJail();
				ui.display();
				currentSquare = squares.get(players.get(playerTurn-1).getPosition());
				break;
				
		//Building Repairs
		case 7: // Don't need for Community chest
				break;
			
		default: System.out.print("Invalid card type");
	
		}
		if(pickedCard.getCardType() != 4){
			CommunityChestDeck.addToBottom(pickedCard);
		}
	}
	
	//Method for error messages
	public void errorMessage(){
		ui.displayString("Please enter a valid command. Here is a list of your options: ");
		help();
	}
	
	//Display information on the square you have landed on
	public void displaySquareInfo(){
		ui.displayString("You have landed on "+ squares.get(players.get(playerTurn-1).getPosition()).getSquareName());
		
	}
	
	
		public static void main (String args[]){			
		Monopoly game = new Monopoly();
		game.getNumOfPlayers();
		game.getPlayerNames();
		game.setPlayers();
		game.displayPlayers();
		game.setPlayerTurns();
		game.setPlayerColors();
		game.displayPlayers();
		game.playGame();
		return;
	}
		
}
