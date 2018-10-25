package ca.carleton.sqa.poker.detectors;

import java.util.HashSet;
import java.util.Set;

import ca.carleton.sqa.poker.Card;
import ca.carleton.sqa.poker.Suit;

public class FlushDetector extends AbstractPokerHandDetector {
	
	public FlushDetector(Set<Card> cards) {
		super(cards);
	}
	
	public boolean isFlush() {
		return hasFlushForXNumberOfCards(5);
	}

	public boolean hasFourCardFlush() {
		return hasFlushForXNumberOfCards(4);
	}
	
	public boolean hasThreeCardFlush() {
		return hasFlushForXNumberOfCards(3);
	}
	
	private boolean hasFlushForXNumberOfCards(int x) {
		if (countCardsForSuit(Suit.CLUB) == x)
			return true;
		if (countCardsForSuit(Suit.DIAMOND) == x)
			return true;
		if (countCardsForSuit(Suit.HEART) == x)
			return true;
		if (countCardsForSuit(Suit.SPADE) == x)
			return true;
		return false;
	}
	
	private Suit getThreeCardFlushSuit() {
		Suit threeCardFlushSuit = null;
		if (countCardsForSuit(Suit.CLUB) == 3)
			threeCardFlushSuit = Suit.CLUB;
		if (countCardsForSuit(Suit.DIAMOND) == 3)
			threeCardFlushSuit = Suit.DIAMOND;
		if (countCardsForSuit(Suit.HEART) == 3)
			threeCardFlushSuit = Suit.HEART;
		if (countCardsForSuit(Suit.SPADE) == 3)
			threeCardFlushSuit = Suit.SPADE;
		return threeCardFlushSuit;
	}

	public long countCardsForSuit(Suit suit) {
		return cards.stream().filter(card -> card.getSuit().equals(suit)).count();
	}

	public Set<Card> getTwoCardsAwayFromFlush() {
		Suit threeCardFlushSuit = getThreeCardFlushSuit();
		Set<Card> twoCards = new HashSet<Card>();
		for (Card card: this.cards) {
			if (!card.getSuit().equals(threeCardFlushSuit)) twoCards.add(card);
		}
		assert twoCards.size() == 2;
		return twoCards;
	}

}
