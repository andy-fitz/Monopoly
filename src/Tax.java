/*		 
*  		  Team Name:	Bankrupt
* 		    
* 			Authors:    Andrew Fitzgerald  12376456
* 		     	 		Ray Hamill		   15342516
* 				 		Catherine Johnson  14475132
* 		      
* 			  Class:	Tax extends Square
*
* 	    Description:    A class which represents a Tax property Square.
* 
*/

public class Tax extends Square {
	
	private static int taxNumber = 0;
	private int taxAmount;

	Tax(int i){
		super(i);
		taxAmount = SquareInfo.TAX_AMOUNTS[taxNumber];
		taxNumber++;
	}
	
	public int getTaxAmount(){
		return taxAmount;
	}
}
