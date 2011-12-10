package br.usp.cata.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.usp.cata.component.lemmatizer.LemmatizerTrees;
import br.usp.cata.model.Position;
import br.usp.cata.util.lemmatizer.Lemmatizer;


public class LemmatizerTest extends CataTestCase {

	private Lemmatizer lemmatizer;
	private List<Byte> lemmatizedText;
	HashMap<Integer, Position> startsLemmatized;
	HashMap<Integer, Position> endsLemmatized;
	byte[] lemmatizedTextBytes;
	
	public LemmatizerTest(String name) {
		super(name);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		new LemmatizerTrees();
		
		lemmatizer = new Lemmatizer();
		lemmatizedText = new ArrayList<Byte>();
		startsLemmatized = new HashMap<Integer, Position>();
		endsLemmatized = new HashMap<Integer, Position>();
	}
	
	@Test
	public void testLemmatizerDebugar() {
		List<String> tokenizedText = Arrays.asList("eu", "debuguei", "o", "sistema", ".");
		
		HashMap<Integer, Position> startsList = new HashMap<Integer, Position>();
		HashMap<Integer, Position> endsList = new HashMap<Integer, Position>();
		Position foo = new Position(0, 0);
		for(int i = 0; i < 4; i++) {
			startsList.put(i, foo);
			endsList.put(i, foo);
		}
		
		lemmatizer.lemmatize(tokenizedText, 0, lemmatizedText, startsList, endsList, startsLemmatized, endsLemmatized);
		
		lemmatizedTextBytes = new byte[lemmatizedText.size()];
		for(int i = 0; i < lemmatizedText.size(); i++)
			lemmatizedTextBytes[i] = lemmatizedText.get(i);
		
		assertEquals(" eu debugar o sistema .", new String(lemmatizedTextBytes));
	}
	
	@Test
	public void testLemmatizerRandomico() {
		List<String> tokenizedText = Arrays.asList("os", "dados", "são", "randômicos", ".");
		
		HashMap<Integer, Position> startsList = new HashMap<Integer, Position>();
		HashMap<Integer, Position> endsList = new HashMap<Integer, Position>();
		Position foo = new Position(0, 0);
		for(int i = 0; i < 4; i++) {
			startsList.put(i, foo);
			endsList.put(i, foo);
		}
		
		lemmatizer.lemmatize(tokenizedText, 0, lemmatizedText, startsList, endsList, startsLemmatized, endsLemmatized);
		
		lemmatizedTextBytes = new byte[lemmatizedText.size()];
		for(int i = 0; i < lemmatizedText.size(); i++)
			lemmatizedTextBytes[i] = lemmatizedText.get(i);
		
		assertEquals(" o dado ser randomico .", new String(lemmatizedTextBytes));
	}
	
	@Test
	public void testLemmatizerMandatorio() {
		List<String> tokenizedText = Arrays.asList("este", "campo", "é", "mandatório", ".");
		
		HashMap<Integer, Position> startsList = new HashMap<Integer, Position>();
		HashMap<Integer, Position> endsList = new HashMap<Integer, Position>();
		Position foo = new Position(0, 0);
		for(int i = 0; i < 4; i++) {
			startsList.put(i, foo);
			endsList.put(i, foo);
		}
		
		lemmatizer.lemmatize(tokenizedText, 0, lemmatizedText, startsList, endsList, startsLemmatized, endsLemmatized);
		
		lemmatizedTextBytes = new byte[lemmatizedText.size()];
		for(int i = 0; i < lemmatizedText.size(); i++)
			lemmatizedTextBytes[i] = lemmatizedText.get(i);
		
		assertEquals(" este campo ser mandatorio .", new String(lemmatizedTextBytes));
	}
	
}
