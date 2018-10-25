package ca.carleton.sqa.poker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ca.carleton.sqa.poker.Card;
import ca.carleton.sqa.poker.Suit;
import ca.carleton.sqa.poker.Symbol;

public class CardTest {

	@Test
	public void testCard() {
		Card card = new Card("D10");
		assertEquals(Suit.DIAMOND, card.getSuit());
		assertEquals(Symbol.TEN, card.getSymbol());

		card = new Card("H2");
		assertEquals(Suit.HEART, card.getSuit());
		assertEquals(Symbol.TWO, card.getSymbol());

		card = new Card("SA");
		assertEquals(Suit.SPADE, card.getSuit());
		assertEquals(Symbol.ACE, card.getSymbol());

		card = new Card("CK");
		assertEquals(Suit.CLUB, card.getSuit());
		assertEquals(Symbol.KING, card.getSymbol());
	}
	
}
