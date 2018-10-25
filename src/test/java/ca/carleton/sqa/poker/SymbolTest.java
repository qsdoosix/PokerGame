package ca.carleton.sqa.poker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ca.carleton.sqa.poker.Symbol;

public class SymbolTest {

	@Test
	public void testFromString() {
		String ace = "A";
		String two = "2";
		String three = "3";
		String four = "4";
		String five = "5";
		String six = "6";
		String seven = "7";
		String eight = "8";
		String nine = "9";
		String ten = "10";
		String jack = "J";
		String queen = "Q";
		String king = "K";

		assertEquals(Symbol.ACE, Symbol.fromString(ace));
		assertEquals(Symbol.TWO, Symbol.fromString(two));
		assertEquals(Symbol.THREE, Symbol.fromString(three));
		assertEquals(Symbol.FOUR, Symbol.fromString(four));
		assertEquals(Symbol.FIVE, Symbol.fromString(five));
		assertEquals(Symbol.SIX, Symbol.fromString(six));
		assertEquals(Symbol.SEVEN, Symbol.fromString(seven));
		assertEquals(Symbol.EIGHT, Symbol.fromString(eight));
		assertEquals(Symbol.NINE, Symbol.fromString(nine));
		assertEquals(Symbol.TEN, Symbol.fromString(ten));
		assertEquals(Symbol.JACK, Symbol.fromString(jack));
		assertEquals(Symbol.QUEEN, Symbol.fromString(queen));
		assertEquals(Symbol.KING, Symbol.fromString(king));
	}
	
	@Test
	public void testRank() {
		assertTrue(Symbol.ACE.getDefaultRank() > Symbol.KING.getDefaultRank());
		assertTrue(Symbol.KING.getDefaultRank() > Symbol.QUEEN.getDefaultRank());
		assertTrue(Symbol.QUEEN.getDefaultRank() > Symbol.JACK.getDefaultRank());
		assertTrue(Symbol.JACK.getDefaultRank() > Symbol.TEN.getDefaultRank());
		assertTrue(Symbol.TEN.getDefaultRank() > Symbol.NINE.getDefaultRank());
		assertTrue(Symbol.NINE.getDefaultRank() > Symbol.EIGHT.getDefaultRank());
		assertTrue(Symbol.EIGHT.getDefaultRank() > Symbol.SEVEN.getDefaultRank());
		assertTrue(Symbol.SEVEN.getDefaultRank() > Symbol.SIX.getDefaultRank());
		assertTrue(Symbol.SIX.getDefaultRank() > Symbol.FIVE.getDefaultRank());
		assertTrue(Symbol.FIVE.getDefaultRank() > Symbol.FOUR.getDefaultRank());
		assertTrue(Symbol.FOUR.getDefaultRank() > Symbol.THREE.getDefaultRank());
		assertTrue(Symbol.THREE.getDefaultRank() > Symbol.TWO.getDefaultRank());
		assertFalse(Symbol.TWO.getDefaultRank() > Symbol.ACE.getDefaultRank());
	}

}
