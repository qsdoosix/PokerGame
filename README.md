
Environment:
 - java 10.0.2 (java 1.88 compatibility mode)
 - maven 3.5.4
 
Setup:
 - mvn clean test

Poker Game Application
The application is contained within ca.carleton.sqa.poker.Main and can be executed in two modes: interactive or file based.  Follow the instructions during execution to understand the expected input format.

  Interactive Mode:
  Two alternatives for execution:
   - run ca.carleton.sqa.poker.Main from within your ide with no parameters
   - run the following command line command: mvn compile -Pplay-samplegame

  File Based Mode:
  Two alternatives for execution:
   - run ca.carleton.sqa.poker.Main from within your ide with filename as parameter
   - run the following command line command to run the game through all lines of samplegame_decks.txt: mvn compile -Pplay-interactive

Noteworthy Tests:
StrategyTest and ScoreTest run through all of the decks and expected hands in Strategy-*.txt and Score-*.txt respectively.  Both are formatted to be the cards for the input deck followed by (after the comma) the expected winning hand.  Where Score focuses on the comparison logic of hands between players, Strategy focuses on the exchange logic and only involves a single player.

The rest of the Tests are simple unit tests.

Other Notes:
There are a number of FIXME comments throughout the code where known compromises in behavior or design were taken.
