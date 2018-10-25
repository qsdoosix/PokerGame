package ca.carleton.sqa.poker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {

	private static Stream<String> deckOfCardStream;
	
	public static void main(String[] args) {
		if (args == null || args.length != 1) {
			System.out.println();
			System.out.println("_____________________________________________________________________________");
			System.out.println("Entering Interactive Mode.  You can also pass in a file as the first argument");
			System.out.println("if you'd like.");
			System.out.println("Each line of the file represents a game, with cards delimited by spaces.");
			System.out.println("The first 5 cards are for AIP, next 5 for the player, then cards to exchange.");
			System.out.println();
			System.out.println("Please enter set of cards (eg. S2 S9 SA DK S5 C5 S10 DJ D7 H8 S3)");
			System.out.println("Ctrl-C to Quit.");
			System.out.print("> ");
			
			deckOfCardStream = new BufferedReader(new InputStreamReader(System.in)).lines();
		} else {
			String filename = args[0];
			
			try {
				deckOfCardStream = Files.lines(Paths.get(filename));
			} catch (IOException e) {
				System.out.println("Trouble reading file: "+filename);
				e.printStackTrace();
			}

		}

		deckOfCardStream.iterator().forEachRemaining(gameDeckConfiguration -> {
			DeckOfCards deck = new SpaceDelimitedStringDeck(gameDeckConfiguration);
			Dealer dealer = new Dealer();
			dealer.assignDeck(deck);
			AutomatedPlayer automatedPlayer = new AutomatedPlayer(dealer);
			SimplePlayer simplePlayer = new SimplePlayer(dealer);

			dealer.dealInitialPokerHands();
			System.out.println();
			System.out.println("Hand To Beat:\t\t\t\t"+simplePlayer.inspectHand());
			System.out.println("Initial AIP Cards:\t\t\t"+automatedPlayer.inspectHand());
			dealer.allowPlayersToExchange();
			System.out.println("AIP exchanged "+automatedPlayer.getDiscardedCards()+" for "+automatedPlayer.getReceivedCards());
			System.out.println("Final AIP Cards:\t\t\t"+automatedPlayer.inspectHand());
			PokerPlayer winningPlayer = dealer.determineWinner();
			System.out.println("The winner with a "+winningPlayer.inspectHand().getType()+" is: "+ ((winningPlayer instanceof AutomatedPlayer) ? "AIP!": "opponent!"));
			System.out.println();
			System.out.print("> ");
		});
	}
}
