package ca.carleton.sqa.poker;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PokerHandComparator implements Comparator<PokerHand> {

	public int compare(PokerHand hand1, PokerHand hand2) {
		int hand1Rank = hand1.getType().getRank();
		int hand2Rank = hand2.getType().getRank();
		if (hand1Rank == hand2Rank) {
			if (hand1.getType().equals(PokerHandType.ROYAL_FLUSH)) {
				return compareSuits(hand1, hand2);
			} else if (hand1.getType().equals(PokerHandType.STRAIGHT_FLUSH)) {
				int symbolComparisonResult = compareSymbols(hand1, hand2);
				if (symbolComparisonResult == 0) {
					return compareSuits(hand1, hand2);
				} else {
					return symbolComparisonResult;
				}
			} else if (hand1.getType().equals(PokerHandType.FOUR_OF_A_KIND)) {
				return compareFourOfAKindSymbols(hand1, hand2);
			} else if (hand1.getType().equals(PokerHandType.FULL_HOUSE)
					|| hand1.getType().equals(PokerHandType.THREE_OF_A_KIND)) {
				return compareThreeOfAKindSymbols(hand1, hand2);
			} else if (hand1.getType().equals(PokerHandType.FLUSH)
					|| hand1.getType().equals(PokerHandType.STRAIGHT)) {
				int hand1Score = hand1.inspectCards().stream().mapToInt(card->card.getSymbol().getDefaultRank()).sum();
				hand1Score += hand1.getHighestCard().getSuit().getRank();
				int hand2Score = hand2.inspectCards().stream().mapToInt(card->card.getSymbol().getDefaultRank()).sum();
				hand2Score += hand2.getHighestCard().getSuit().getRank();
				return hand2Score - hand1Score;
			} else if (hand1.getType().equals(PokerHandType.TWO_PAIR)
					|| hand1.getType().equals(PokerHandType.PAIR)) {
				int hand1SymbolRank = hand1.getHighestPairCard().getSymbol().getDefaultRank();
				int hand2SymbolRank = hand2.getHighestPairCard().getSymbol().getDefaultRank();
				if (hand1SymbolRank == hand2SymbolRank) {
					int hand1SuitRank = hand1.getHighestPairCard().getSuit().getRank();
					int hand2SuitRank = hand2.getHighestPairCard().getSuit().getRank();
					return hand2SuitRank - hand1SuitRank;
				} else return hand2SymbolRank - hand1SymbolRank;
			} else if (hand1.getType().equals(PokerHandType.HIGH_CARD)) {
				return compareSymbols(hand1, hand2);
			}
		}
		return hand2Rank - hand1Rank;
	}
	
	/**
	 * Assumes suits of cards in each hand are all the same.
	 */
	private int compareSuits(PokerHand hand1, PokerHand hand2) {
		return hand2.getSuitOfACard().getRank() - hand1.getSuitOfACard().getRank();
	}

	/**
	 * Compares each card of sorted hands from highest to lowest, indicating the hand with the highest symbol as the highest.
	 * If all symbols are the same for both hands, returns 0.
	 */
	private int compareSymbols(PokerHand hand1, PokerHand hand2) {
		List<Card> hand1Cards = hand1.inspectCards().stream().sorted().collect(Collectors.toList());
		List<Card> hand2Cards = hand2.inspectCards().stream().sorted().collect(Collectors.toList());
		
		for (int i=0;i<PokerHand.POKER_HAND_SIZE;i++) {
			int comparisonResult = hand1Cards.get(i).compareTo(hand2Cards.get(i));
			if (comparisonResult != 0) return comparisonResult;
		}
		return 0;
	}

	private int compareFourOfAKindSymbols(PokerHand hand1, PokerHand hand2) {
		return hand2.getFourOfAKindSymbol().getDefaultRank() - hand1.getFourOfAKindSymbol().getDefaultRank();
	}
	private int compareThreeOfAKindSymbols(PokerHand hand1, PokerHand hand2) {
		return hand2.getThreeOfAKindSymbol().getDefaultRank() - hand1.getThreeOfAKindSymbol().getDefaultRank();
	}
}
