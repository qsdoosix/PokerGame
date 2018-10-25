package ca.carleton.sqa.poker.detectors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ca.carleton.sqa.poker.Card;
import ca.carleton.sqa.poker.Symbol;

public class MatchingSymbolDetector extends AbstractPokerHandDetector {
	
	private Symbol fourOfAKindSymbol;
	private Symbol threeOfAKindSymbol;
	private Symbol pairSymbol;
	private Symbol secondPairSymbol;
	
	public MatchingSymbolDetector(Set<Card> cards) {
		super(cards);
		
		List<Card> sequencedHand = putHandInSequence(this.cards);
		
		if (symbolCountMatches(sequencedHand, 4)) {
			fourOfAKindSymbol = sequencedHand.get(2).getSymbol();
		} else if (symbolCountMatches(sequencedHand, 3)) {
			threeOfAKindSymbol = sequencedHand.get(2).getSymbol();
		}
		
		pairSymbol = findPairSymbol(sequencedHand);
		secondPairSymbol = findPairSymbol(sequencedHand);

	}
	
	/**
	 * Finds the pair, after all four of a kind and three of a kinds have already
	 * been found (and optionally pair has been found if looking for second one).
	 */
	private Symbol findPairSymbol(List<Card> sequencedHand) {
		for (int i = 0; i < (sequencedHand.size() - 1); i++) {
			if (!sequencedHand.get(i).getSymbol().equals(fourOfAKindSymbol)
					&& !sequencedHand.get(i).getSymbol().equals(threeOfAKindSymbol)
					&& !sequencedHand.get(i).getSymbol().equals(pairSymbol)
					&& sequencedHand.get(i).getSymbol().equals(sequencedHand.get(i + 1).getSymbol())) {
				return sequencedHand.get(i).getSymbol();
			}
		}
		return null; // no pair found
	}

	private boolean symbolCountMatches(List<Card> sequencedHand, int count) {
		int sameCardCount = 1;
		for (int i = 0; i < (sequencedHand.size() - 1); i++) {
			if (sequencedHand.get(i).getSymbol().equals(sequencedHand.get(i + 1).getSymbol())) {
				sameCardCount++;
			} else {
				if (sameCardCount == count) return true;
				sameCardCount = 1;
			}
		}
		return sameCardCount == count;
	}

	public Symbol getFourOfAKindSymbol() {
		return fourOfAKindSymbol;
	}
	
	public Symbol getPairSymbol() {
		return pairSymbol;
	}
	
	public Symbol getThreeOfAKindSymbol() {
		return threeOfAKindSymbol;
	}

	public boolean isFourOfAKind() {
		return (fourOfAKindSymbol != null);
	}
	
	public boolean hasThreeOfAKind() {
		return (threeOfAKindSymbol != null);
	}
	
	public boolean hasPair() {
		return (pairSymbol != null);
	}

	public boolean isPair() {
		return hasPair();
	}

	public boolean isTwoPair() {
		return ((pairSymbol != null) && (secondPairSymbol != null));
	}
	public boolean isThreeOfAKind() {
		return hasThreeOfAKind() && !hasPair();
	}

	public boolean isFullHouse() {
		return hasThreeOfAKind() && hasPair();
	}

	public Card getHighestPairCard() {
		if ((secondPairSymbol == null)
		|| pairSymbol.getDefaultRank() > secondPairSymbol.getDefaultRank()) {
			return getCardWithSymbol(pairSymbol);
		} else return getCardWithSymbol(secondPairSymbol);
	}
	
	public Card getCardWithSymbol(Symbol symbol) {
		for (Card card : cards) {
			if (card.getSymbol().equals(symbol))
				return card;
		}
		throw new IllegalStateException(
				"This method should never be called when hand doesn't have a card with requested symbol");
	}

	public Card getCardNotInTwoPair() {
		Card foundCard = null;
		for (Card card: this.cards) {
			if (!card.getSymbol().equals(pairSymbol)
					&& !card.getSymbol().equals(secondPairSymbol)) {
				foundCard = card;
			}
		}
		return foundCard;
	}

	public Card getLowestCardNotInThreeOfAKind() {
		Card lowestCard = null;
		for (Card card: this.cards) {
			if (!card.getSymbol().equals(threeOfAKindSymbol)) {
				if (lowestCard == null) lowestCard = card;
				else if (lowestCard.getSymbol().getDefaultRank() > card.getSymbol().getDefaultRank()) {
					lowestCard = card;
				}
			}
		}
		return lowestCard;
	}

	public Set<Card> getCardsNotInPair() {
		Set<Card> cardsNotInPair = new HashSet<Card>();
		for (Card card: this.cards) {
			if (!card.getSymbol().equals(pairSymbol)) cardsNotInPair.add(card);
		}
		assert cardsNotInPair.size() == 3;
		return cardsNotInPair;
	}
	
	//FIXME: Too many methods here -- this class could use some refactoring.
}
