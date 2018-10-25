package ca.carleton.sqa.poker;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import ca.carleton.sqa.poker.detectors.PokerHandDetector;

public class PokerHand implements Serializable {
	private static final long serialVersionUID = 3625306992552410886L;

	public static final int POKER_HAND_SIZE = 5;

	private Set<Card> cards;
	
	private PokerHandDetector detector;
	
	public PokerHand(String hand) {
		this(Arrays.stream(hand.split(" ")).map(cardText -> new Card(cardText)).collect(Collectors.toSet()));
	}

	public PokerHand(Set<Card> cards) {
		if (cards == null || cards.size() != POKER_HAND_SIZE)
			throw new IllegalStateException("A Poker Hand must have " + POKER_HAND_SIZE + " cards");
		this.cards = new HashSet<Card>(cards);
		this.detector = new PokerHandDetector(inspectCards());
	}

	public PokerHand(PokerHand simpleHand) {
		this(new HashSet<Card>(simpleHand.cards));
	}

	/**
	 * @return copy of the cards, not a reference to the actual cards
	 */
	public Set<Card> inspectCards() {
		return new HashSet<Card>(cards);
	}

	public void exchange(Card cardToExchange, Card card) {
		this.cards.remove(cardToExchange);
		this.cards.add(card);
	}

	public void exchange(Set<Card> cardsToExchange, Set<Card> cards) {
		this.cards.removeAll(cardsToExchange);
		this.cards.addAll(cards);
	}

// FLUSH RELATED METHODS	

	public Card getCardWithSuit(Suit suit) {
		for (Card card : cards) {
			if (card.getSuit().equals(suit))
				return card;
		}
		throw new IllegalStateException(
				"This method should never be called when hand doesn't have a card with requested suit");
	}

	public boolean contains(Card card) {
		return this.cards.contains(card);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cards == null) ? 0 : cards.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PokerHand))
			return false;
		PokerHand other = (PokerHand) obj;
		if (cards == null) {
			if (other.cards != null)
				return false;
		} else if (!cards.equals(other.cards))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return cards.stream().map(card -> (card.getSuit().toString() + card.getSymbol().toString()))
				.collect(Collectors.joining(" "));
	}

	protected void replaceCard(Card cardToReplace, Card newCard) {
		cards.remove(cardToReplace);
		cards.add(newCard);
	}

	public PokerHandType getType() {
		if (detector.isRoyalFlush())
			return PokerHandType.ROYAL_FLUSH;
		else if (detector.isStraightFlush())
			return PokerHandType.STRAIGHT_FLUSH;
		else if (detector.isFourOfAKind())
			return PokerHandType.FOUR_OF_A_KIND;
		else if (detector.isFullHouse())
			return PokerHandType.FULL_HOUSE;
		else if (detector.isFlush())
			return PokerHandType.FLUSH;
		else if (detector.isStraight())
			return PokerHandType.STRAIGHT;
		else if (detector.isThreeOfAKind())
			return PokerHandType.THREE_OF_A_KIND;
		else if (detector.isTwoPair())
			return PokerHandType.TWO_PAIR;
		else if (detector.isPair())
			return PokerHandType.PAIR;
		else
			return PokerHandType.HIGH_CARD;
	}

	/**
	 * This is typically used during flush evaluations when all suits are known to be the same
	 * @return the suite of any card of the hand
	 */
	public Suit getSuitOfACard() {
		return cards.iterator().next().getSuit();
	}

	public Card getHighestCard() {
		return detector.getHighestCard();
	}

	public Symbol getFourOfAKindSymbol() {
		return detector.getFourOfAKindSymbol();
	}

	public Symbol getThreeOfAKindSymbol() {
		return detector.getThreeOfAKindSymbol();
	}

	public boolean isOneAwayFromStraight() {
		return detector.isOneAwayFromStraight();
	}

	public boolean isTwoAwayFromStraight() {
		return detector.isTwoAwayFromStraight();
	}

	public Card getCardOneAwayFromStraight() {
		return detector.getCardOneAwayFromStraight();
	}

	public boolean isOneAwayFromFlush() {
		return detector.hasFourCardFlush();
	}

	public boolean isTwoAwayFromFlush() {
		return detector.hasThreeCardFlush();
	}

	public long countCardsForSuit(Suit suit) {
		return detector.countCardsForSuit(suit);
	}

	public Card getHighestPairCard() {
		return detector.getHighestPairCard();
	}

	public boolean isStraightOrBetter() {
		return (getType().getRank() >= PokerHandType.STRAIGHT.getRank());
	}
	
	public boolean isFlush() {
		return getType().equals(PokerHandType.FLUSH);
	}
	
	public boolean isPair() {
		return getType().equals(PokerHandType.PAIR);
	}
	
	public boolean isHighCard() {
		return getType().equals(PokerHandType.HIGH_CARD);
	}

	public Card getCardOneAwayFromFlush() {
		if (countCardsForSuit(Suit.CLUB) == 1) {
			return getCardWithSuit(Suit.CLUB);
		} else if (countCardsForSuit(Suit.SPADE) == 1) {
			return getCardWithSuit(Suit.SPADE);
		} else if (countCardsForSuit(Suit.HEART) == 1) {
			return getCardWithSuit(Suit.HEART);
		} else {
			return getCardWithSuit(Suit.DIAMOND);
		}
	}

	public boolean isOneAwayFromRoyalFlush() {
		if (isOneAwayFromStraightFlush()) {
			Card cardAwayFromStraightFlush = getCardOneAwayFromStraightFlush();
			if ( (getHighestCard().getSymbol().equals(Symbol.ACE)
					&& !getHighestCard().equals(cardAwayFromStraightFlush))
				|| (getHighestCard().getSymbol().equals(Symbol.KING)
					&& !getHighestCard().equals(cardAwayFromStraightFlush)))
			{
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean isOneAwayFromStraightFlush() {
		// FIXME: This is close, but not correct for some situations
		if (isOneAwayFromFlush() && isOneAwayFromStraight())return true;
		else return false;
	}

	public Card getCardOneAwayFromStraightFlush() {
		// FIXME: This is close, but not correct for some scenarios
		if (isOneAwayFromFlush()) return getCardOneAwayFromFlush();
		if (isOneAwayFromStraight()) return getCardOneAwayFromStraight();
		else throw new IllegalStateException("This hand is not one away from straight flush");
	}

	public Card getCardOneAwayFromRoyalFlush() {
		return getCardOneAwayFromStraightFlush(); //FIXME: This may not work in all scenarios
	}

	public boolean isOneAwayFromFullHouse() {
		if (detector.isThreeOfAKind() || detector.isTwoPair()) return true;
		else return false;
	}

	public Card getCardOneAwayFromFullHouse() {
		Card foundCard = null;
		if (detector.isThreeOfAKind()) {
			foundCard = detector.getLowestCardNotInThreeOfAKind();
		} else if (detector.isTwoPair()) {
			foundCard = detector.getCardNotInTwoPair();
		}
		return foundCard;
	}

	public Set<Card> getTwoCardsAwayFromFlush() {
		return detector.getTwoCardsAwayFromFlush();
	}

	public Set<Card> getTwoCardsAwayFromStraight() {
		return detector.getTwoCardsAwayFromStraight();
	}

	public Set<Card> getCardsNotInPair() {
		return detector.getCardsNotInPair();
	}

	public Set<Card> getThreeLowestCards() {
		return detector.getThreeLowestCards();
	}

}
