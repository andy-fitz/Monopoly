/*		 
*  		  Team Name:	Bankrupt
* 		    
* 			Authors:    Andrew Fitzgerald  12376456
* 		     	 		Ray Hamill		   15342516
* 				 		Catherine Johnson  14475132
* 		      
* 			  Class:	Station extends Square
*
* 	    Description:    A class which represents a board Station object.
* 
*/

public class Station extends Square {

	private static int stationNumber = 0;
	private int stationPrice = SquareInfo.STATION_PRICE;
	private int mortgageValue = 100;
	
	
	
	Station(int i){
		super(i);
		this.buyable=true;
		this.shortName = SquareInfo.STATION_SHORT_NAMES[stationNumber];
		rentLevel = 0;
		rentPrice = SquareInfo.STATION_RENTS[rentLevel];
		stationNumber++;
	}
	
	public int getPrice(){
		return stationPrice;
	}
	
	public int getMortgageValue(){
		return mortgageValue;
	}
	
	public String getColour(){
		return "Station" ;
	}
	
	public void increaseRentLevel(){
		rentLevel++;
		rentPrice = SquareInfo.STATION_RENTS[rentLevel];
	}
	
	public void decreaseRentLevel(){
		rentLevel--;
		rentPrice = SquareInfo.STATION_RENTS[rentLevel];
	}
	
	public int getRentLevel(){
		return rentLevel;
	}

	public int getRentPrice(){
		return rentPrice;
	}
	
	public void resetSquare(){
		super.resetSquare();
		rentPrice =  SquareInfo.STATION_RENTS[rentLevel];
	}
}
