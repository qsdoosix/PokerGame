package ca.carleton.sqa.poker;

import java.util.HashSet;
import java.util.Set;

public class SpaceDelimitedStringDeck implements DeckOfCards {
	private String[] deck;
	private int indexOfNextCard = 0;

	/**
	 * Creates a deck from a space delimited string.  For example, "C5 D6 HA"
	 * would return 5 of clubs as first card, 6 of diamonds as the next, etc...
	 * 
	 * @param deckConfiguration
	 */
	public SpaceDelimitedStringDeck(String deckConfiguration) {
		this.deck = deckConfiguration.split(" ");
	}

	public SpaceDelimitedStringDeck(String... strings) {
		this.deck = strings;
	}
	
	public Card nextCard() {
		Card card = new Card(deck[indexOfNextCard]);
		indexOfNextCard++;
		return card;
	}

	public Set<Card> nextCards(int numberOfCards) {
		Set<Card> cards = new HashSet<Card>();
		for (int i = 0; i < numberOfCards; i++) {
			cards.add(nextCard());
		}
		return cards;
	}

}
