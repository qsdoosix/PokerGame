package ca.carleton.sqa.poker;

public enum Symbol {
	TWO("2", 1),
	THREE("3", 2),
	FOUR("4", 3),
	FIVE("5", 4),
	SIX("6", 5),
	SEVEN("7", 6),
	EIGHT("8", 7),
	NINE("9", 8),
	TEN("10", 9),
	JACK("J", 10),
	QUEEN("Q", 11),
	KING("K", 12),
	ACE("A", 13);

	public static final int LOW_ACE_RANK = 0; // Where two is ranked 1 (must be less than two)
	
	private final String value;
	private final int defaultRank;
	
	private Symbol(String value, int rank) {
		this.value = value;
		this.defaultRank = rank;
	}

	public String getValue() {
		return this.value;
	}
	
	/**
	 * This is only the default rank of a symbol since the hand it's contained in
	 * may change it's rank.  For example, an Ace in a small straight has the lowest rank.
	 */
	public int getDefaultRank() {
		return this.defaultRank;
	}
	
	@Override
	public String toString() {
		return this.value;
	}

	public static Symbol fromString(String string) {
		for (Symbol symbol: Symbol.values()) {
			if (symbol.value.equalsIgnoreCase(string)) return symbol;
		}
		throw new IllegalArgumentException("Symbol not recognized: "+string);
	}
}
