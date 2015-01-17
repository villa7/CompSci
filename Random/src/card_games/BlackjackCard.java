package card_games;

public class BlackjackCard {
	
	public static final int SPADES = 0, //numericalify the suits
							HEARTS = 1,
							DIAMONDS = 2,
							CLUBS = 3;
	
	public static final int ACE = 1, //numericalify the non-numerical cards
							JACK = 11,
							QUEEN = 12,
							KING = 13;
	
	private int suit;
	private int value;
	
	public BlackjackCard(int value, int suit) {
		//Init the card with a suit and value
		this.suit = suit;
		this.value = value;
	}
	
	public int getSuit() {
		return suit;
	}
	
	public int getValue() {
		return value;
	}
	
	public String valueToString() {
		switch(value) {
			case 1: return "Ace";
			case 2: return "2";
			case 3: return "3";
			case 4: return "4";
			case 5: return "5";
			case 6: return "6";
			case 7: return "7";
			case 8: return "8";
			case 9: return "9";
			case 10: return "10";
			case 11: return "Jack";
			case 12: return "Queen";
			case 13: return "King";
			default: return "valueToString error";
		}
	}
	
	public String suitToString() {
		switch(suit) {
		case SPADES: return "Spades";
		case HEARTS: return "Hearts";
		case DIAMONDS: return "Diamonds";
		case CLUBS: return "Clubs";
		default: return "suitToString error";
		}
	}
	
	public String cardToString() {
		return valueToString() + " of " + suitToString();
	}

}
