/*		 
/*		 
*  		  Team Name:	Bankrupt
* 		    
* 			Authors:    Andrew Fitzgerald  12376456
* 		     	 		Ray Hamill		   15342516
* 				 		Catherine Johnson  14475132
* 		      
* 			  Class:	Property extends Square
*
* 	    Description:    A class which represents a board Property object.
* 
*/


public class Property extends Square {
	
	private static int propertyNumber = 0;
	private int propertyIndex;
	private int groupColour;
	private String ColourName;
	private int sitePrice;
	
	private int mortgageValue;
	
	Property(int i){
		super(i);
		this.buyable=true;
		this.propertyIndex = propertyNumber;
		sitePrice = SquareInfo.SITE_PRICES[propertyNumber];
		this.rentLevel = 0;
		this.rentPrice = SquareInfo.SITE_RENTS[propertyNumber][rentLevel];
		groupColour = SquareInfo.SITE_COLOURS[propertyNumber];
		ColourName = SquareInfo.COLOUR_GROUP_NAME[groupColour];
		this.shortName = SquareInfo.SITE_SHORT_NAMES[propertyNumber];
		mortgageValue = SquareInfo.SITE_MORTGAGE_VALUE[propertyNumber];
		
		propertyNumber++;
	}
	
	public int getPrice(){
		return sitePrice;
	}
	public int getRentPrice(){
		return rentPrice;
	}
	
	public String getColour(){
		return ColourName;
	}
	
	public int getColourNum(){
		return groupColour;
	}
	//Called each time a house/ hotel is added
	public void increaseRentLevel(){
		rentLevel++;
		rentPrice = SquareInfo.SITE_RENTS[propertyIndex][rentLevel];
	}
		
	public void decreaseRentLevel(){
		rentLevel--;
		rentPrice = SquareInfo.SITE_RENTS[propertyIndex][rentLevel];
	}

	public int getRentLevel(){
		return rentLevel;
	}

	public int getMortgageValue(){
		return mortgageValue;
	}
	
	public void resetSquare(){
		super.resetSquare();
		rentPrice =  SquareInfo.SITE_RENTS[propertyIndex][rentLevel];
	}
		
}

