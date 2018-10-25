package ca.carleton.sqa.poker.detectors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ca.carleton.sqa.poker.Card;
import ca.carleton.sqa.poker.Symbol;

public class HighCardDetector extends AbstractPokerHandDetector {
	
	public HighCardDetector(Set<Card> cards) {
		super(cards);
	}
	
	public boolean containsCardSymbol(Symbol symbol) {
		return cards.stream().anyMatch(card -> card.getSymbol().equals(symbol));
	}

	public Card getHighestCard() {
		return putHandInSequence(this.cards).get(0);
	}

	public Set<Card> getThreeLowestCards() {
		Set<Card> threeLowestCards = new HashSet<Card>();
		List<Card> sequencedHand = putHandInSequence(this.cards);
		threeLowestCards.add(sequencedHand.get(2));
		threeLowestCards.add(sequencedHand.get(3));
		threeLowestCards.add(sequencedHand.get(4));
		return threeLowestCards;
	}
}
