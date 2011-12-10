package br.usp.cata.tests;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.usp.cata.model.Languages;
import br.usp.cata.util.TextAnalyzer;


public class TextAnalyzerTest extends CataTestCase {

	private TextAnalyzer textAnalyzer;
	private ArrayList<String> text;
	
	public TextAnalyzerTest(String name) {
		super(name);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		text = new ArrayList<String>();
	}
	
	@Test
	public void testAnalyzerEnTokenizer() {
		text.add("The boys are doing their homework");
		
		textAnalyzer = new TextAnalyzer(text, Languages.ENGLISH, null);
		
		assertEquals(" the boys are doing their homework ", new String(textAnalyzer.getTokenizedTextBytes()));
	}
	
	@Test
	public void testAnalyzerEnLemmatizer() {
		text.add("The boys are doing their homework");
		
		textAnalyzer = new TextAnalyzer(text, Languages.ENGLISH, null);
		
		assertEquals(" the boy be do they homework ", new String(textAnalyzer.getLemmatizedTextBytes()));
	}
}
