public class Chance_Card extends Card {

	private static int ChanceCardNumber = 0;
	
	Chance_Card(){
		super();
		name = "Chance";
		message = CardInfo.CHANCE_CARD_MESSAGES[ChanceCardNumber];
		type = CardInfo.CHANCE_CARD_TYPES[ChanceCardNumber];
		ChanceCardNumber++;
	}
}
