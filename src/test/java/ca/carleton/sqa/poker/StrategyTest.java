package ca.carleton.sqa.poker;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ca.carleton.sqa.poker.PokerHand;
import ca.carleton.sqa.poker.SpaceDelimitedStringDeck;

@RunWith(Parameterized.class)
public class StrategyTest extends DeckAndWinningHandFileBasedTest {

	@Parameters(name="{index}: after exchange {1} from deck {0}")
    public static Collection<String[]> data() {
    	return loadDataFromFiles(new String[]{
    		"Strategy-1.txt", // StraightOrBetter
    		"Strategy-2.1.txt", // OneAwayFromRoyalFlush
    		"Strategy-2.2.txt", // OneAwayFromStraightFlush
    		"Strategy-2.4.txt", // OneAwayFromFlush
    		"Strategy-2.5.1.txt", // OneAwayFromStraightWithAce
    		"Strategy-2.5.2.txt", // OneAwayFromStraightWithoutAce
    		"Strategy-3.txt", // ThreeSameSuit
    		"Strategy-4.txt", // ThreeSameRank
    		"Strategy-5.1.txt", // ThreeInSequenceWithAce
    		"Strategy-5.2.txt", // ThreeInSequenceWithoutAce
    		"Strategy-6.txt", // TwoDistinctPairs
    		"Strategy-7.txt", // SinglePair
    		"Strategy-8.txt" // TwoHighest
    		});
    }
    
	@Test
	public void test() {
		dealer.assignDeck(new SpaceDelimitedStringDeck(deckString));
		dealer.dealInitialPokerHands();
		automatedPlayer.exchangeCards();
		assertEquals(new PokerHand(expectedWinningHandString), automatedPlayer.inspectHand());
	}
	
}
