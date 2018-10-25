package ca.carleton.sqa.poker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ca.carleton.sqa.poker.Suit;

public class SuitTest {
	
	@Test
	public void testFromString() {
		String club = "C";
		String diamond = "D";
		String heart = "H";
		String spade = "S";

		assertEquals(Suit.CLUB, Suit.fromString(club));
		assertEquals(Suit.DIAMOND, Suit.fromString(diamond));
		assertEquals(Suit.HEART, Suit.fromString(heart));
		assertEquals(Suit.SPADE, Suit.fromString(spade));
	}
	
	@Test
	public void testRank() {
		assertTrue(Suit.CLUB.getRank() < Suit.DIAMOND.getRank());
		assertTrue(Suit.DIAMOND.getRank() < Suit.HEART.getRank());
		assertTrue(Suit.HEART.getRank() < Suit.SPADE.getRank());
		assertFalse(Suit.SPADE.getRank() < Suit.CLUB.getRank());
	}
}
