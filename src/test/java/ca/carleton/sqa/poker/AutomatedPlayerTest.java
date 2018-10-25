package ca.carleton.sqa.poker;

import static org.junit.Assert.fail;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ca.carleton.sqa.poker.AutomatedPlayer;
import ca.carleton.sqa.poker.Card;
import ca.carleton.sqa.poker.Dealer;
import ca.carleton.sqa.poker.DeckOfCards;
import ca.carleton.sqa.poker.PokerHand;
import junit.framework.AssertionFailedError;

public class AutomatedPlayerTest {
	private AutomatedPlayer automatedPlayer;
	private Dealer dealer;
	private PokerHand simpleHandShuffled = new PokerHand("SA DQ SK HJ C5");
	private PokerHand simpleHand = new PokerHand("SK C5 DQ HJ SA");
	private Card cardNotInSimpleHand = new Card("CQ");
	private Card cardInSimpleHand = new Card("SA");

	@Before
	public void setUp() {
		dealer = new Dealer();
		dealer.assignDeck(new DeckOfCards() {
			public Card nextCard() {
				return cardNotInSimpleHand;
			}

			public Set<Card> nextCards(int numberOfCards) {
				throw new UnsupportedOperationException("Unnexpected Call");
			}
		});
		automatedPlayer = new AutomatedPlayer(dealer);
	}

	@Test
	public void testGetHand() {
		automatedPlayer.dealHand(simpleHand);
		assertHandsEqual(simpleHand, automatedPlayer.inspectHand());
	}

	@Test
	public void testGetHand_OrderUnimportant() {
		automatedPlayer.dealHand(simpleHand);
		assertHandsEqual(simpleHandShuffled, automatedPlayer.inspectHand());
	}

	@Test
	public void testGetHand_CardNotInHand() {
		PokerHand hand = new PokerHand(simpleHand);
		hand.replaceCard(cardInSimpleHand, cardNotInSimpleHand);
		automatedPlayer.dealHand(hand);
		try {
			assertHandsEqual(simpleHand, automatedPlayer.inspectHand());
			fail("The comparison of the original hand with that of the player was expected to fail since they differ.");
		} catch (AssertionFailedError e) {
			//exception expected -- the assertion should fail.
		}
	}

	private boolean assertHandsEqual(PokerHand expectedHand, PokerHand actualHand) {
		if (expectedHand != null && actualHand != null) {
			if (expectedHand.equals(actualHand)) {
				if (actualHand.equals(expectedHand)) {
					return true;
				} else {
					// ASSUMPTION: Given detailed error handling is not required for this assignment
					// this will suffice for our needs.
					throw new AssertionFailedError(String.format(
							"The given poker hands were unnexpectedly not equal.  Expected (%s) Actual (%s)",
							expectedHand, actualHand));
				}
			} else {
				throw new AssertionFailedError(
						String.format("The given poker hands were unnexpectedly not equal.  Expected (%s) Actual (%s)",
								expectedHand, actualHand));
			}
		} else if (expectedHand == null && actualHand == null) {
			return true;
		} else {
			return false;
		}
	}

}
