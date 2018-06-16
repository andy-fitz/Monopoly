public class Community_Chest_Card extends Card {
	
	private static int ChestCardNumber = 0;
	
	Community_Chest_Card(){
		super();
		name = "Community Chest";
		message = CardInfo.COMMUNITY_CARD_MESSAGES[ChestCardNumber];
		type = CardInfo.COMMUNITY_CARD_TYPES[ChestCardNumber];
		ChestCardNumber++;
	}
	
}
