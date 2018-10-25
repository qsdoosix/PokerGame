package ca.carleton.sqa.poker;

public enum PokerHandType {
	HIGH_CARD("HIGH_CARD", 1),
	PAIR("PAIR", 2),
	TWO_PAIR("TWO_PAIR", 3),
	THREE_OF_A_KIND("THREE_OF_A_KIND", 4),
	STRAIGHT("STRAIGHT", 5),
	FLUSH("FLUSH", 6),
	FULL_HOUSE("FULL_HOUSE", 7),
	FOUR_OF_A_KIND("FOUR_OF_A_KIND", 8),
	STRAIGHT_FLUSH("STRAIGHT_FLUSH", 9),
	ROYAL_FLUSH("ROYAL_FLUSH", 10);
	
	private final String value;
	private int rank;
	
	private PokerHandType(String value, int rank) {
		this.value = value;
		this.rank = rank;
	}

	public String getValue() {
		return this.value;
	}
	
	public int getRank() {
		return this.rank;
	}
	
	@Override
	public String toString() {
		return this.value;
	}

}
