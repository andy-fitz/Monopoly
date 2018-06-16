/*		 
*  		  Team Name:	Bankrupt
* 		    
* 			Authors:    Andrew Fitzgerald  12376456
* 		     	 		Ray Hamill		   15342516
* 				 		Catherine Johnson  14475132
* 		      
* 			  Class:	Square.java
*
* 	    Description:    A class which represents a board Square object.
* 
*/

public class Square{
	
	private boolean mortgaged = false;
	private int position;
	protected boolean buyable;
	private int squareType;
	private boolean owned;
	private String squareName;
	protected String shortName;
	protected int rentPrice;
	protected int rentLevel;		// 0- no houses, 1- one house,...., 5- Hotel

	Square(int i){
		position = i;
		squareType 	= SquareInfo.SQUARE_TYPES[i];
		squareName = SquareInfo.SQUARE_NAMES[i];
		buyable = false;
		owned = false;
	}
	
	public boolean getOwned(){
		return owned;
	}
	
	public void setOwned(boolean enable){
		owned = enable;;
	}
	
	public int getType(){
		return squareType;
	}
	
	public String getSquareName(){
		return squareName;
	}
	
	public int getPrice(){
		return 0;
	}
	
	public int getRentPrice(){
		return 0;
	}
	
	public String getColour(){
		return null;
	}
	
	public int getColourNum(){
		return -1;
	}
	
	public int getRentLevel(){
		return 0;
	}
	
	public String getShortName(){
		return shortName;
	}
	
	public boolean checkIfMortgaged(){
		return mortgaged;
	}
	
	public void mortgage(){
		mortgaged = true;
	}
	
	public void redeem(){
		mortgaged = false;
	}
	
	public int getMortgageValue(){
		return 0;
	}
	
	//Called each time a house/ hotel is added
	public void increaseRentLevel(){
		rentLevel++;
	}
	
	public void decreaseRentLevel(){
		rentLevel--;
	}
	
	public int getPosition(){
		return position;
	}
	
	public int getHousePrice(){
		if(position<10){
			return 50;
		}
		else if(position>10 && position<20){
			return 100;
		}
		else if(position>20 && position<30){
			return 150;
		}
		else{
			return 200;
		}
	}
	
	public void resetSquare(){
		mortgaged = false;
		owned = false;
		rentLevel = 0;
	}
	
	public int getTaxAmount(){
		return 0;
	}
	
	
	
	//Other Accessor methods to get private variables 
	
	
	
}
