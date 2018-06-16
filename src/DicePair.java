/*		 
*  		  Team Name:	Bankrupt
* 		    
* 			Authors:    Andrew Fitzgerald  12376456
* 		     	 		Ray Hamill		   15342516
* 				 		Catherine Johnson  14475132
* 		      
* 			  Class:	DicePair
*		Inner Class:	Die
* 	    Description:    A class which sets up two die objects and 
* 						provides methods to roll the dice and return
* 						the dice face values.
* 						
* 						Dies are disabled if player has used their roll and
* 						they didn't get a double. Also disabled if they have rolled 
* 						3 doubles.
* 
*/

public class DicePair {
	
	private Die die1= new Die();
	private Die die2= new Die();
	private boolean enable;
	private int doubleCount;
	

	private class Die{
	
		private int faceValue;
		
		public Die(){
			faceValue = (int)(Math.random()*6)+1;
		}
		
		public void roll(){
			faceValue  = (int)(Math.random()*6)+1;
		}
		
		public int getfaceValue(){
			return faceValue;
		}
		
	}
	
	DicePair(){
		enable = true;
		doubleCount = 0;
	}
	

	public void reset(){
		enable = true;
		doubleCount = 0;
	}
	
	public int getTotal(){
		return die1.getfaceValue() + die2.getfaceValue();
	}
	
	public int getDieOne(){
		return die1.getfaceValue();
	}
	
	public int getDieTwo(){
		return die2.getfaceValue();
	}
	
	public boolean getEnable(){
		return enable;
	}
	
	public int turnOrderRoll(){
		die1.roll();
		die2.roll();
		return getTotal();
	}

	public void rollDice(){
		if(enable = true){
			die1.roll();
			die2.roll();
			enable = false;
			
			if (doubles()==true){
				doubleCount++;
				enable=true;
			}	
		}	
	}
	
	public boolean doubles(){
		if(die1.getfaceValue() == die2.getfaceValue())
			return true;
		else
			return false;
	}
	
	public void disableDice(){
		enable=false;
	}
	
	public String displayTotal(){
		
		String total = Integer.toString(getTotal());
		String die1String = Integer.toString(die1.getfaceValue());
		String die2String = Integer.toString(die2.getfaceValue());
		
		return "Die1: " + die1String + "\nDie2: " + die2String + "\nTotal: " + total;
	}
	
	public int getDoubleCount(){
		return doubleCount;
	}
}
