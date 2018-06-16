/*		 
*  		  Team Name:	Bankrupt
* 		    
* 			Authors:    Andrew Fitzgerald  12376456
* 		     	 		Ray Hamill		   15342516
* 				 		Catherine Johnson  14475132
* 		      
* 			  Class:	Utility extends Square
*
* 	    Description:    A class which represents a board Utility object.
* 
*/

public class Utility extends Square {
	
	private static int UtilityNumber = 0;
	private int UtilityPrice;
	private int mortgageValue = 75;
	
	
	Utility(int i){
		super(i);
		this.buyable=true;
		UtilityPrice = SquareInfo.UTILITY_PRICE;
		this.shortName = SquareInfo.UTILITY_SHORT_NAMES[UtilityNumber];
		rentLevel = 0;
		rentPrice = SquareInfo.UTILITY_RENTS[rentLevel];
		UtilityNumber++;
	}
	
	public int getPrice(){
		return UtilityPrice;
	}
	
	public int getMortgageValue(){
		return mortgageValue;
	}
	
	public String getColour(){
		return "Utility" ;
	}
	
	public void increaseRentLevel(){
		rentLevel++;
		rentPrice = SquareInfo.UTILITY_RENTS[rentLevel];
	}
	
	public void decreaseRentLevel(){
		rentLevel--;
		rentPrice = SquareInfo.UTILITY_RENTS[rentLevel];
	}
	
	public int getRentLevel(){
		return rentLevel;
	}

	public int getRentPrice(){
		return rentPrice;
	}
	
	public void resetSquare(){
		super.resetSquare();
		rentPrice =  SquareInfo.UTILITY_RENTS[rentLevel];
	}
	
	
	
}
