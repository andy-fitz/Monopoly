/*		  Team Name:	Bankrupt
* 		      
* 			Authors:    Andrew Fitzgerald  12376456
* 		     	 		Ray Hamill		   15342516
* 				 		Catherine Johnson  14475132
* 
* 			  Class:	Player.java
* 
* 	    Description:    A class which to represent a Player in a Monopoly game.
*						It handles the movement of the player.
*/

import java.awt.Color;
import java.util.ArrayList;

public class Player implements Comparable<Player>{
	
	private int playerScore;
	private Color tokenColor;
	private String tokenColorName;
	private boolean inGame;
	private int position = 0;
	private String name;
	private int balance;
	private int turnRollValue;
	private ArrayList<Square> ownedProperty = new ArrayList<Square>();
	private int jailCardCount = 0;
	private boolean inJail;
	private int jailTurnCount;
	
	
	Player(){
		this.balance = 1500;
		inJail=false;
		inGame=true;
		return;
	}
	
	public int compareTo(Player that){
		
		return that.turnRollValue - this.turnRollValue ;
	}
	
	public boolean moveToken (int squares){
		boolean passedGo= false;
		position = position + squares;
		
		if(position >= 40){
			position = position %40;
			passedGo=true;
		}
		return passedGo;
	}
	
	public boolean checkProperty(String site){
		
		if(!ownedProperty.isEmpty()){
			for(int i=0; i<ownedProperty.size(); i++){
				if(ownedProperty.get(i).getSquareName().equalsIgnoreCase(site)){
					return true;
				}
			}
		}
		return false;
	}
	
	public int findProperty(String shortName){
		if(!ownedProperty.isEmpty()){
			for(int i=0; i<ownedProperty.size(); i++){
				
				if(ownedProperty.get(i).getShortName().equalsIgnoreCase(shortName)){
					return i;
				}
			}
		}
		return -1;
	}
	
	public boolean checkForGroup(int colour){
		
		int count = 0;
		
		if(!ownedProperty.isEmpty()){
			for(int i=0; i<ownedProperty.size(); i++){
				if(ownedProperty.get(i).getColourNum() == colour){
					count ++;
				}
			}
		}
		
		if( count == SquareInfo.NUM_IN_GROUP[colour])
		{
			return true;
		}
		else{
			return false;
		}	
	}
	
	public ArrayList<Integer> utilityCheck(){
		
		
		ArrayList<Integer> positions = new ArrayList<Integer>();
		
		if(!ownedProperty.isEmpty()){
			for(int i=0; i<ownedProperty.size(); i++){
				if(ownedProperty.get(i).getType() == SquareInfo.TYPE_UTILITY){
					positions.add(i);
				}
			}
		}
		
		return positions;
		
		
	}
	
	public int stationCount(){
		
		int count = 0;
		if(!ownedProperty.isEmpty()){
			for(int i=0; i<ownedProperty.size(); i++){
				if(ownedProperty.get(i).getType() == SquareInfo.TYPE_STATION){
					count ++;
				}
			}
		}
		return count;
	}
	
	public ArrayList<Integer> findStationIndex(){
		
		ArrayList<Integer> positions = new ArrayList<Integer>();
		
		if(!ownedProperty.isEmpty()){
			for(int i=0; i<ownedProperty.size(); i++){
				if(ownedProperty.get(i).getType() == SquareInfo.TYPE_STATION){
					positions.add(i);
				}
			}
		}
		return positions;
	}

	public int calculateTotalAssets(){
		int assets = balance;
		for(int i = 0; i < ownedProperty.size(); i++ ){
			assets = assets + ownedProperty.get(i).getPrice();
		}
		return assets;
	}
	
	public int getPosition(){
		return position;
	}
	
	public void setPosition(int newPos){
		position = newPos;
	}
	
	public String getName(){
		return name;
	}
	
	public int getBalance(){
		return balance;
	}
	
	public void decreaseBalance(int amount){
		if(balance > 0){
			balance = balance - amount;
		}
	}
	
	public void IncreaseBalance(int amount){
		balance = balance + amount;
	}
	
	public void setName(String inputName){
		this.name = inputName;
	}
	
	public int getJailCardCount(){
		return jailCardCount;
	}
	
	public void acquireJailCard(){
		jailCardCount++;
	}
	
	public void useJailCard(){
		jailCardCount--;
	}
	
	public void setTurnRollValue(int RollValue){
		turnRollValue = RollValue;
	}
	
	public int getTurnRollValue(){
		return turnRollValue ;
	}
	
	public ArrayList<Square> getOwnedProperty(){
		return ownedProperty;
	}
	
	public void addProperty(Square newProperty){
		ownedProperty.add(newProperty);
	}
	
	public void mortgage(int listPos){
		ownedProperty.get(listPos).mortgage();
	}
	
	public void redeem(int listPos){
		ownedProperty.get(listPos).redeem();
	}
	
	public void resetProperty(){
		
		for(int i= 0; i < ownedProperty.size(); i++){
			ownedProperty.get(i).resetSquare();
		}
		ownedProperty = new ArrayList<Square>();
		
	}
	
	public void removePlayer(){
		inGame = false;
	}
	
	public boolean getStatus(){
		return inGame;
	}
	
	public Color getPlayerColor(){
		return tokenColor;
	}
	
	public String getPlayerColorName(){
		return tokenColorName;
	}
	
	public void setPlayerColor(Color playerColor, String colorName){
		tokenColor = playerColor;
		tokenColorName = colorName;
	}
	
	public void setPlayerScore(int score){
		playerScore = score;
	}
	
	public int getPlayerScore(){
		return playerScore;
	}
	
	public boolean getJailStatus(){
		return inJail;
	}
	
	public void getOutOfJail(){
		inJail=false;
		jailTurnCount=0;
	}
	
	public int getJailTurnCount(){
		return jailTurnCount;
	}
	
	public void increaseJailTurnCount(){
		jailTurnCount++;
	}
	
	public void goToJail(){
		position=10;
		inJail=true;
	}
	
	// Returned ArrayList = {housCount, hotelCount}
	public ArrayList<Integer> countBuildings(){
		ArrayList<Integer> buildingCounts = new ArrayList<Integer>();
		int houseCount = 0;
		int hotelCount = 0;
		
		for(int i= 0; i < ownedProperty.size(); i++){
			if(ownedProperty.get(i).getType() == SquareInfo.TYPE_SITE){
				if(ownedProperty.get(i).getRentLevel() < 5){
					houseCount = houseCount + ownedProperty.get(i).getRentLevel();
				}
				else{
					hotelCount++;
				}
			}
		}
		
		buildingCounts.add(houseCount);
		buildingCounts.add(hotelCount);
		return buildingCounts;
	}
}
