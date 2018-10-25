package ca.carleton.sqa.poker;

public enum Suit {
	CLUB("C", 1),
	DIAMOND("D", 2),
	HEART("H", 3),
	SPADE("S", 4);
	
	private final String value;
	private int rank;
	
	private Suit(String value, int rank) {
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
	
	public static Suit fromString(String string) {
		for (Suit suit: Suit.values()) {
			if (suit.value.equalsIgnoreCase(string)) return suit;
		}
		throw new IllegalArgumentException("Suit not recognized: "+string);
	}
}
