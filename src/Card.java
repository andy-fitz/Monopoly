import java.util.Scanner;
import java.util.ArrayList;

public class Card{
	
	protected String name;
	protected String message;
	protected int type;
	
	Card(){
		
	}
	
	public String getCardMessage(){
		return message;
	}
	
	public int getCardType(){
		return type;
	}
	
	//Returns amount to Pay/Collect 
	public int getFigure(){
		
		int amount = 0;
		
		Scanner moneyFinder = new Scanner(message);
		
		while(moneyFinder.hasNext()){
			String current = moneyFinder.next();
			
			if(current.charAt(0) == '�'){
				
				
				current = current.substring(1);
				current = current.replace(".", "");
			
				
				try{
					amount = Integer.parseInt(current);
				}catch(NumberFormatException nfe){
					System.out.println("Not an integer");
				}
			}
		}
		
		
		
		moneyFinder.close();
		return amount;
	}
	//Returns repair figures.
	public ArrayList<Integer> getTwoFigures(){
		ArrayList<Integer> figures = new ArrayList<Integer>();
		
		Scanner moneyFinder = new Scanner(message);
		
		while(moneyFinder.hasNext()){
			String current = moneyFinder.next();
			
			if(current.charAt(0) == '�'){
			
				current = current.substring(1);
				current = current.replace(".", "");	
				
				try{
					figures.add(Integer.parseInt(current));
				}catch(NumberFormatException nfe){
					System.out.println("Not an integer");
				}
			}
		}
		moneyFinder.close();
		return figures;
	}
	
	public boolean findWord(String word){
		
		Scanner wordFinder = new Scanner(message);
		
		while(wordFinder.hasNext()){
			String current = wordFinder.next();
			
			if(current.equals(word)){
				wordFinder.close();
				return true;
			}
		}
		
		wordFinder.close();
		return false;
	}
}
