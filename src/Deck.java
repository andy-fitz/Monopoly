import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	private ArrayList<Card> cards = new ArrayList<Card>();

	
	Deck(String deckType){
		setUpDeck(deckType);
		shuffle();
	}
	
	public void shuffle(){
		 Collections.shuffle(cards);
	}
	
	public Card draw(){
		Card top = cards.get(0);
		cards.remove(0);
		return top;
	}
	
	public void addToBottom(Card used){
		cards.add(used);
	}
	
	public void setUpDeck(String deckType){
		
		if( deckType.equals("Community Chest")){
			 for(int i=0; i<16; i++){
				 cards.add(new Community_Chest_Card()); 
			 }
		}
		else if( deckType.equals("Chance")){
			for(int i=0; i<16; i++){
				cards.add(new Chance_Card());
			}
		}
		
	}
	
}
