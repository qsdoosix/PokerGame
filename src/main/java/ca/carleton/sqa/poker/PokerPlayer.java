package ca.carleton.sqa.poker;

import java.util.Set;

public class PokerPlayer {
	private Dealer dealer;

	protected PokerHand pokerHand;
	
	public PokerPlayer(Dealer dealer) {
		this.dealer = dealer;
		dealer.playerJoined(this);
	}

	public void dealHand(PokerHand hand) {
		pokerHand = hand;
	}
	
	protected Card exchangeCardWithDealer(Card cardToExchange) {
		return dealer.exchange(cardToExchange);
	}

	protected Set<Card> exchangeCardsWithDealer(Set<Card> cardsToExchange) {
		return dealer.exchange(cardsToExchange);
	}

	/**
	 * @return copy of the poker hand, not a reference to the player's actual hand
	 */
	protected PokerHand inspectHand() {
		return new PokerHand(pokerHand);
	}
	
	public void exchangeCards() {}

}
