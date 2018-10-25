package ca.carleton.sqa.poker.detectors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ca.carleton.sqa.poker.Card;
import ca.carleton.sqa.poker.Symbol;

public class StraightDetector extends AbstractPokerHandDetector {
	
	public StraightDetector(Set<Card> cards) {
		super(cards);
	}
	
	public boolean isStraight() {
		List<Card> sequencedHand = putHandInSequence(cards);
		return cardsInSequence(sequencedHand);
	}

	public Card getCardOneAwayFromStraight() {
		
		//FIXME: Currently assumes 4 card straight...
		List<Card> sequencedHand = putHandInSequence(cards);
		if (cardsInSequence(sequencedHand.get(0), sequencedHand.get(1))) {
			return sequencedHand.get(4);
		} else if (cardsInSequence(sequencedHand.get(1), sequencedHand.get(2))) {
			return sequencedHand.get(0);
		} else
			throw new IllegalStateException("Expected this hand to have a straight of 4, but was not: " + toString());
	}

	public boolean isOneAwayFromStraight() {
		
		//FIXME: Currently just checks for 4 card straight
		List<Card> sequencedHand = putHandInSequence(cards);

		// Either the front of the list has the straight, or the back
		if (cardsInSequence(sequencedHand.get(0), sequencedHand.get(1))) {
			return cardsInSequence(sequencedHand.get(1), sequencedHand.get(2))
					&& cardsInSequence(sequencedHand.get(2), sequencedHand.get(3));
		} else if (cardsInSequence(sequencedHand.get(1), sequencedHand.get(2))) {
			return cardsInSequence(sequencedHand.get(2), sequencedHand.get(3))
					&& cardsInSequence(sequencedHand.get(3), sequencedHand.get(4));
		} else {
			return false;
		}
	}

	public boolean isTwoAwayFromStraight() {
		//FIXME: Currently just checks for 3 card straight
		List<Card> sequencedHand = putHandInSequence(cards);

		// Either the front of the list has the straight, middle, or the back
		if (cardsInSequence(sequencedHand.get(0), sequencedHand.get(1))) {
			return cardsInSequence(sequencedHand.get(1), sequencedHand.get(2));
		} else if (cardsInSequence(sequencedHand.get(1), sequencedHand.get(2))) {
			return cardsInSequence(sequencedHand.get(2), sequencedHand.get(3));
		} else if (cardsInSequence(sequencedHand.get(2), sequencedHand.get(3))) {
			return cardsInSequence(sequencedHand.get(3), sequencedHand.get(4));
		} else {
			return false;
		}
	}

	private boolean cardsInSequence(List<Card> cards) {
		for (int i = 0; i < cards.size() - 1; i++) {
			if (!cardsInSequence(cards.get(i), cards.get(i + 1)))
				return false;
		}
		return true;
	}

	private boolean cardsInSequence(Card card1, Card card2) {
		return Math.abs(getRankForCard(card1) - getRankForCard(card2)) == 1;
	}

	@Override
	public int getRankForCard(Card card) {
		if (card.getSymbol().equals(Symbol.ACE)) {
			// if other cards in the hand are 2, 3, 4 and 5, then this is a low ranking ace
			if ((countCardsForSymbol(Symbol.ACE) == 1)
					&& (countCardsForSymbol(Symbol.TWO) == 1)
					&& (countCardsForSymbol(Symbol.THREE) == 1)) {
				return Symbol.LOW_ACE_RANK;
			}
		}
		return card.getSymbol().getDefaultRank();
	}
	
	public long countCardsForSymbol(Symbol symbol) {
		return cards.stream().filter(card -> card.getSymbol().equals(symbol)).count();
	}

	public Set<Card> getTwoCardsAwayFromStraight() {
		Set<Card> twoCards = new HashSet<Card>();
		List<Card> sequencedHand = putHandInSequence(cards);

		// Either the front of the list has the straight, middle, or the back
		if (cardsInSequence(sequencedHand.get(0), sequencedHand.get(1))) {
			if (cardsInSequence(sequencedHand.get(1), sequencedHand.get(2))) {
				twoCards.add(sequencedHand.get(3));
				twoCards.add(sequencedHand.get(4));
				return twoCards;
			}
		} 
		
		if (cardsInSequence(sequencedHand.get(1), sequencedHand.get(2))) {
			if (cardsInSequence(sequencedHand.get(2), sequencedHand.get(3))) {
				twoCards.add(sequencedHand.get(0));
				twoCards.add(sequencedHand.get(4));
				return twoCards;
			}
		}

		if (cardsInSequence(sequencedHand.get(2), sequencedHand.get(3))) {
			if (cardsInSequence(sequencedHand.get(3), sequencedHand.get(4))) {
				twoCards.add(sequencedHand.get(0));
				twoCards.add(sequencedHand.get(1));
				return twoCards;
			}
		}
		
		//FIXME: Cover the case where the cards are not in sequence, but still straight candidates
		//For now throw exception as the code shouldn't even go down this stream
		throw new IllegalStateException("Trying to get two cards away from straight for unrecognized hand type");
		
	}

}
