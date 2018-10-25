package ca.carleton.sqa.poker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dealer {
	
	private List<PokerPlayer> players = new ArrayList<PokerPlayer>();
	private DeckOfCards deck;
	private Set<Card> discardPile = new HashSet<Card>(); // ASSUMPTION: There was nothing in the given rules about what the dealer does with the discards, so I'll just maintain an independent discard pile for now.

	public Card exchange(Card cardToExchange) {
		discardPile.add(cardToExchange);
		return deck.nextCard(); 
	}
	
	public Set<Card> exchange(Set<Card> cardsToExchange) {
		discardPile.addAll(cardsToExchange);
		return deck.nextCards(cardsToExchange.size()); 
	}
	
	public void assignDeck(DeckOfCards newDeck) {
		this.deck = newDeck;
	}

	public void playerJoined(PokerPlayer newPlayer) {
		players.add(newPlayer);
	}
	
	private DeckOfCards getDeck() {
		if (deck == null) throw new IllegalStateException("Deck not yet assigned for the dealer to deal...");
		return this.deck;
	}

	public void dealInitialPokerHands() {
		for (PokerPlayer player: players) {
			PokerHand hand = new PokerHand(getDeck().nextCards(PokerHand.POKER_HAND_SIZE));
			player.dealHand(hand);
		}
	}

	public void allowPlayersToExchange() {
		for (PokerPlayer player: players) {
			player.exchangeCards();
		}
	}

	public PokerPlayer determineWinner() {
		if (players == null || players.size() != 2) throw new IllegalStateException("There is only expected to be exactly 2 poker players");
		PokerPlayer player1 = players.get(0);
		PokerPlayer player2 = players.get(1);
		if (new PokerHandComparator().compare(player1.inspectHand(), player2.inspectHand()) < 0) return player1;
		else return player2;
	}

}
