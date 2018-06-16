public class CardInfo {
	
	//Card types
	public static final int TYPE_GO_BACK_TO = 0;
	public static final int TYPE_ADVANCE = 1;
	public static final int TYPE_PAY = 2;
	public static final int TYPE_COLLECT = 3;
	public static final int TYPE_OPTION = 4;
	public static final int TYPE_GET_OUT_OF_JAIL_FREE = 5;
	public static final int TYPE_GO_TO_JAIL = 6;
	public static final int TYPE_REPAIRS = 7;
	
	//Card Display Messages
	public static final String[] COMMUNITY_CARD_MESSAGES = {
		"Advance to Go.",
		"Go back to Old Kent Road.",
		"Go to jail. Move directly to jail. Do not pass Go. Do not collect �200.",
		"Pay hospital �100.",
		"Doctor's fee. Pay �50.",
		"Pay your insurance premium �50.",
		"Bank error in your favour. Collect �200.",
		"Annuity matures. Collect �100.",
		"You inherit �100.",
		"From sale of stock you get �50.",
		"Receive interest on 7% preference shares: �25.",
		"Income tax refund. Collect �20.",
		"You have won second prize in a beauty contest. Collect �10.",
		"It is your birthday. Collect �10 from each player.",
		"Get out of jail free. This card may be kept until needed or sold.",
		"Pay a �10 fine or take a Chance."
	};
	
	public static final String[] CHANCE_CARD_MESSAGES = {
		"Advance to Go.",
		"Go to jail. Move directly to jail. Do not pass Go. Do not collect �200.",
		"Advance to Pall Mall. If you pass Go collect �200.",
		"Take a trip to Marylebone Station and if you pass Go collect �200.",
		"Advance to Trafalgar Square. If you pass Go collect �200.",
		"Advance to Mayfair.",
		"Go back three spaces.",
		"Make general repairs on all of your houses. For each house pay �25. For each hotel pay �100.",
		"You are assessed for street repairs: �40 per house, �115 per hotel.",
		"Pay school fees of �150.",
		"Drunk in charge fine �20.",
		"Speeding fine �15.",
		"Your building loan matures. Receive �150.",
		"You have won a crossword competition. Collect �100.",
		"Bank pays you dividend of �50.",
		"Get out of jail free. This card may be kept until needed or sold"
	};
	
	//Community Chest Deck card types.
	public static final int[] COMMUNITY_CARD_TYPES = {
		TYPE_ADVANCE, TYPE_GO_BACK_TO, TYPE_GO_TO_JAIL, TYPE_PAY, TYPE_PAY, TYPE_PAY,
		TYPE_COLLECT, TYPE_COLLECT, TYPE_COLLECT, TYPE_COLLECT, TYPE_COLLECT, TYPE_COLLECT,
		TYPE_COLLECT, TYPE_COLLECT, TYPE_GET_OUT_OF_JAIL_FREE , TYPE_OPTION
		
	};
	//Chance Deck card types.
	public static final int[] CHANCE_CARD_TYPES = {
		TYPE_ADVANCE, TYPE_GO_TO_JAIL, TYPE_ADVANCE, TYPE_ADVANCE, TYPE_ADVANCE, TYPE_ADVANCE,
		TYPE_GO_BACK_TO, TYPE_REPAIRS, TYPE_REPAIRS, TYPE_PAY, TYPE_PAY, TYPE_PAY, TYPE_COLLECT,
		TYPE_COLLECT, TYPE_COLLECT, TYPE_GET_OUT_OF_JAIL_FREE
	};
	
	
	
}
