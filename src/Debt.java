/*		 
*  		  Team Name:	Bankrupt
* 		    
* 			Authors:    Andrew Fitzgerald  12376456
* 		     	 		Ray Hamill		   15342516
* 				 		Catherine Johnson  14475132
* 		      
* 			  Class:	Debt
*
* 	    Description:    A class which represents a debt between two Players.
* 						Used in paying rent.
* 
*/

public class Debt {
	
	private Player creditor;
	private int debtOwed;
	
	Debt(int amount, Player creditor){
		this.creditor = creditor;
		this.debtOwed = amount;
	}
	
	public String displayDebt(){
		return "You owe " + creditor.getName() +  " �" + debtOwed + " in rent.";
	}
	
	public Player getCreditor(){
		return creditor;
	}

	public int getAmountOwed(){
		return debtOwed;
	}
}
