package ca.carleton.sqa.poker;

import java.util.HashSet;
import java.util.Set;

public class AutomatedPlayer extends PokerPlayer {
	private Set<Card> discardedCards = new HashSet<Card>(); // last discarded
	private Set<Card> receivedCards = new HashSet<Card>(); // last received
	
	public AutomatedPlayer(Dealer dealer) {
		super(dealer);
	}

	@Override
	public void exchangeCards() {
		
		// No Cards Exchanged For
		if (pokerHand.isStraightOrBetter()) {
			return;
			
		// One Card Exchanged For:
		} else if (pokerHand.isOneAwayFromRoyalFlush()) {
			exchangeCard(pokerHand.getCardOneAwayFromRoyalFlush());
		} else if (pokerHand.isOneAwayFromStraightFlush()) {
			exchangeCard(pokerHand.getCardOneAwayFromStraightFlush());
		} else if (pokerHand.isOneAwayFromFullHouse()) {
			exchangeCard(pokerHand.getCardOneAwayFromFullHouse());
		} else if (pokerHand.isOneAwayFromFlush()) {
			exchangeCard(pokerHand.getCardOneAwayFromFlush());
		} else if (pokerHand.isOneAwayFromStraight()) {
			exchangeCard(pokerHand.getCardOneAwayFromStraight());
			
		// Two Cards Exchanged For:
		} else if (pokerHand.isTwoAwayFromFlush()) {
			exchangeCards(pokerHand.getTwoCardsAwayFromFlush());
		} else if (pokerHand.isTwoAwayFromStraight()) {
			exchangeCards(pokerHand.getTwoCardsAwayFromStraight());
			
		// Three Cards Exchanged For:
		} else if (pokerHand.isPair()) {
			exchangeCards(pokerHand.getCardsNotInPair());
		} else if (pokerHand.isHighCard()) {
			exchangeCards(pokerHand.getThreeLowestCards());
		}
	}

	private void exchangeCard(Card cardToExchange) {
		discardedCards.add(cardToExchange);
		Card receivedCard = exchangeCardWithDealer(cardToExchange);
		receivedCards.add(receivedCard);
		pokerHand.exchange(cardToExchange, receivedCard);
	}

	private void exchangeCards(Set<Card> cardsToExchange) {
		discardedCards.addAll(cardsToExchange);
		Set<Card> newCards = exchangeCardsWithDealer(cardsToExchange);
		receivedCards.addAll(newCards);
		pokerHand.exchange(cardsToExchange, newCards);
	}

	public Set<Card> getDiscardedCards() {
		return discardedCards;
	}

	public Set<Card> getReceivedCards() {
		return receivedCards;
	}

}
