package ca.carleton.sqa.poker;

import java.util.Set;

public interface DeckOfCards {
	public Card nextCard();
	
	public Set<Card> nextCards(int numberOfCards);
}
