package ca.carleton.sqa.poker;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ca.carleton.sqa.poker.AutomatedPlayer;
import ca.carleton.sqa.poker.Dealer;
import ca.carleton.sqa.poker.SpaceDelimitedStringDeck;

public class AutomatedPlayerCardExchangeTest {
	private AutomatedPlayer automatedPlayer;
	private Dealer dealer;

	@Before
	public void setUp() {
		dealer = new Dealer();
		automatedPlayer = new AutomatedPlayer(dealer);
	}
	
	@Test
	public void testExchangeCards_OneAwayFromFlushThenFlush() {
		SpaceDelimitedStringDeck deck = new SpaceDelimitedStringDeck("S2 S9 SA DK S5 S6");
		dealer.assignDeck(deck);
		dealer.dealInitialPokerHands();
		assertFalse(automatedPlayer.inspectHand().isFlush());
		automatedPlayer.exchangeCards();
		assertTrue(automatedPlayer.inspectHand().isFlush());
	}
}
