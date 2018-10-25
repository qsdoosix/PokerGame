package ca.carleton.sqa.poker;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ca.carleton.sqa.poker.PokerHand;
import ca.carleton.sqa.poker.SimplePlayer;
import ca.carleton.sqa.poker.SpaceDelimitedStringDeck;


/**
 * 	Test how the dealer scores hands and determines the winner.
 *  Note: for the purposes of these tests, we don't need to
 *  exchange cards because we are just testing the dealer's ability
 *  to score hands.
 */
@RunWith(Parameterized.class)
public class ScoreTest extends DeckAndWinningHandFileBasedTest {

	protected SimplePlayer simplePlayer;

	@Parameters(name="{index}: winner {1} from deck {0}")
    public static Collection<String[]> data() {
    	return loadDataFromFiles(new String[]{
    		"Score-1.txt", // TwoRoyalFlushes
    		"Score-2.1.txt", // TwoStraightFlushesOnSuit
    		"Score-2.2.txt", // TwoStraightFlushesOnRank
    		"Score-3.txt", // TwoFourOfAKinds
    		"Score-4.1.txt", // TwoFullHouses
    		"Score-4.2.txt", // TwoThreeOfAKinds
    		"Score-5.1.txt", // TwoFlushesByRank
    		"Score-5.2.txt", // TwoFlushesBySuit
    		"Score-6.1.txt", // TwoStraightsByRank
    		"Score-6.2.txt", // TwoStraightsBySuit
    		"Score-7.1.txt", // TwoPairsByRank
    		"Score-7.2.txt", // TwoPairsBySuit
    		"Score-8.1.txt", // TwoSinglePairsByRank
    		"Score-8.2.txt", // TwoSinglePairsBySuit
    		"Score-9.txt", // TwoHighCards
    		"Score-10.txt" // TwoHandTypesDiffer
    		});
    }
    
    @Before
    public void setUp() {
    	super.setUp();
		simplePlayer = new SimplePlayer(dealer);
    }

    @Test
    public void test() {
		dealer.assignDeck(new SpaceDelimitedStringDeck(deckString));
		dealer.dealInitialPokerHands();
		assertEquals(new PokerHand(expectedWinningHandString), dealer.determineWinner().inspectHand());
    }
    
}
