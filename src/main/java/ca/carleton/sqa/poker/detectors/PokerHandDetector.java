package ca.carleton.sqa.poker.detectors;

import java.util.Set;

import ca.carleton.sqa.poker.Card;
import ca.carleton.sqa.poker.Suit;
import ca.carleton.sqa.poker.Symbol;

public class PokerHandDetector {
	
	private FlushDetector flushDetector;
	private StraightDetector straightDetector;
	private MatchingSymbolDetector matchingSymbolDetector;
	private HighCardDetector highCardDetector;
	
	public PokerHandDetector(Set<Card> cards) {
		this.flushDetector = new FlushDetector(cards);
		this.straightDetector = new StraightDetector(cards);
		this.matchingSymbolDetector = new MatchingSymbolDetector(cards);
		this.highCardDetector = new HighCardDetector(cards);
	}

	public boolean isFlush() {
		return flushDetector.isFlush();
	}

	public boolean isPair() {
		return matchingSymbolDetector.isPair();
	}

	public boolean isTwoPair() {
		return matchingSymbolDetector.isTwoPair();
	}

	public boolean isStraight() {
		return straightDetector.isStraight();
	}

	public boolean isFourOfAKind() {
		return matchingSymbolDetector.isFourOfAKind();
	}

	public boolean isThreeOfAKind() {
		return matchingSymbolDetector.isThreeOfAKind();
	}

	public boolean hasFourCardFlush() {
		return flushDetector.hasFourCardFlush();
	}

	public boolean hasThreeCardFlush() {
		return flushDetector.hasThreeCardFlush();
	}

	public boolean isFullHouse() {
		return matchingSymbolDetector.isFullHouse();
	}

	public long countCardsForSuit(Suit suit) {
		return flushDetector.countCardsForSuit(suit);
	}
	
	public boolean isOneAwayFromStraight() {
		return straightDetector.isOneAwayFromStraight();
	}
	
	public boolean isTwoAwayFromStraight() {
		return straightDetector.isTwoAwayFromStraight();
	}
	
	public Card getCardOneAwayFromStraight() {
		return straightDetector.getCardOneAwayFromStraight();
	}

	public Card getHighestCard() {
		return highCardDetector.getHighestCard();
	}

	public Symbol getFourOfAKindSymbol() {
		return matchingSymbolDetector.getFourOfAKindSymbol();
	}
	public Symbol getThreeOfAKindSymbol() {
		return matchingSymbolDetector.getThreeOfAKindSymbol();
	}

	public Card getHighestPairCard() {
		return matchingSymbolDetector.getHighestPairCard();
	}

	public boolean isRoyalFlush() {
		if (isStraightFlush() && highCardDetector.containsCardSymbol(Symbol.ACE)) return true;
		else return false;
	}

	public boolean isStraightFlush() {
		if (isStraight() && isFlush()) return true;
		else return false;
	}

	public Card getCardNotInTwoPair() {
		return matchingSymbolDetector.getCardNotInTwoPair();
	}

	public Card getLowestCardNotInThreeOfAKind() {
		return matchingSymbolDetector.getLowestCardNotInThreeOfAKind();
	}

	public Set<Card> getTwoCardsAwayFromFlush() {
		return flushDetector.getTwoCardsAwayFromFlush();
	}

	public Set<Card> getTwoCardsAwayFromStraight() {
		return straightDetector.getTwoCardsAwayFromStraight();
	}

	public Set<Card> getCardsNotInPair() {
		return matchingSymbolDetector.getCardsNotInPair();
	}

	public Set<Card> getThreeLowestCards() {
		return highCardDetector.getThreeLowestCards();
	}
	
	//FIXME: Too much delegation here -- from poker hand, to this, to underlying detector classes -- this class could use some refactoring.


}
