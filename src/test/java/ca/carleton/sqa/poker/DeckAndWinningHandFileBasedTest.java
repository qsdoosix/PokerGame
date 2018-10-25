package ca.carleton.sqa.poker;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.runners.Parameterized.Parameter;

import ca.carleton.sqa.poker.AutomatedPlayer;
import ca.carleton.sqa.poker.Dealer;

public class DeckAndWinningHandFileBasedTest {
	
	protected Dealer dealer;
	protected AutomatedPlayer automatedPlayer;


	@Parameter // first data value (0) is default
    public String deckString;

    @Parameter(1)
    public String expectedWinningHandString;

    public static void loadDataFromFile(Collection<String[]> testData, String filename) {
		Stream<String> testDataStream = null;
		try {
			testDataStream = Files.lines(Paths.get(ClassLoader.getSystemResource(filename).toURI()));
    		testDataStream.iterator().forEachRemaining(testline -> {
    			testData.add(testline.split(", "));
    		});
		} catch (IOException | URISyntaxException e) {
			System.out.println("Trouble reading file: "+filename);
			e.printStackTrace();
			fail(e.getMessage());
		} finally {
			if (testDataStream != null) testDataStream.close();
		}
    	
	}
	
	public static Collection<String[]> loadDataFromFiles(String[] filenames) {
    	Collection<String[]> testData = new ArrayList<String[]>();
		for (String filename: filenames) {
			loadDataFromFile(testData, filename);
		}
		return testData;
	}
	
    @Before
    public void setUp() {
		dealer = new Dealer();
		automatedPlayer = new AutomatedPlayer(dealer);
	}

}
