package ca.carleton.sqa.poker;

import java.util.Comparator;

import ca.carleton.sqa.poker.detectors.AbstractPokerHandDetector;

public class CardComparator implements Comparator<Card> {
	
	private AbstractPokerHandDetector pokerHandDetector; // depends on the type of hand

	public CardComparator(AbstractPokerHandDetector pokerHandDetector) {
		this.pokerHandDetector = pokerHandDetector;
	}

	@Override
	public int compare(Card card1, Card card2) {
		return pokerHandDetector.getRankForCard(card2) - pokerHandDetector.getRankForCard(card1);
	}

}
