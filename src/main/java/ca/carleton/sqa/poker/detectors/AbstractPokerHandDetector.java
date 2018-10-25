package ca.carleton.sqa.poker.detectors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import ca.carleton.sqa.poker.Card;
import ca.carleton.sqa.poker.CardComparator;

public abstract class AbstractPokerHandDetector {
	protected List<Card> cards;
	
	public AbstractPokerHandDetector(Set<Card> cards) {
		this.cards = new ArrayList<Card>(cards);
	}

	protected List<Card> putHandInSequence(List<Card> hand) {
		List<Card> sequencedHand = new ArrayList<Card>(hand);
		Collections.sort(sequencedHand, new CardComparator(this));
		return sequencedHand;
	}
	
	public int getRankForCard(Card card) {return card.getSymbol().getDefaultRank();}

}
