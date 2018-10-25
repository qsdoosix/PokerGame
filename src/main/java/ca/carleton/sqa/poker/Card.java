package ca.carleton.sqa.poker;

import java.io.Serializable;

public class Card implements Comparable<Card>, Serializable {
	private static final long serialVersionUID = -1709505410585360799L;
	
	private Suit suit;
	private Symbol symbol;

	public Card(String string) {
		if (string == null || string.length() < 2 || string.length() > 3)
			throw new IllegalArgumentException("String definition of a card must be only 2 or 3 characters, a suit followed by a symbol (ie. DA or H10 for Ace of Diamonds and Ten of Hearts respectively), got: "+string);
		suit = Suit.fromString(string.substring(0, 1));
		symbol = Symbol.fromString(string.substring(1, string.length()));
	}

	@Override
	public String toString() {
		return suit.getValue() + symbol.getValue();
	}

	public Symbol getSymbol() {
		return this.symbol;
	}
	
	public Suit getSuit() {
		return this.suit;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) return false;
	    if (!(obj instanceof Card))
	        return false;
	    if (obj == this)
	        return true;
	    return ( this.getSuit() == ((Card) obj).getSuit() )
	    		&& ( this.getSymbol() == ((Card) obj).getSymbol() );
	}

	@Override
	public int hashCode() {
		return this.getSuit().hashCode() + this.getSymbol().hashCode();
	}

	@Override
	public int compareTo(Card o) {
		Card otherCard = (Card) o;
		return otherCard.getSymbol().compareTo(this.getSymbol());
	}
	
}
