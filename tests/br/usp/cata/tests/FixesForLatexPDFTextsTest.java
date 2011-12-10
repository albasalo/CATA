package br.usp.cata.tests;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import br.usp.cata.component.FixesForLatexPDFTexts;


public class FixesForLatexPDFTextsTest extends CataTestCase {

	private Pattern pattern;
	private Map<String, String> tokens;
	
	public FixesForLatexPDFTextsTest(String name) {
		super(name);
	}
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		new FixesForLatexPDFTexts();
		pattern = FixesForLatexPDFTexts.getPattern();
		tokens = FixesForLatexPDFTexts.getTokens();
	}

	@Test
	public void testPatternsAcute() {
		Matcher matcher = pattern.matcher("pra´tica");		
		StringBuffer sb = new StringBuffer();
		while(matcher.find()) {
		    matcher.appendReplacement(sb, tokens.get(matcher.group(1)));
		}
		matcher.appendTail(sb);
		assertEquals("prática", sb.toString());
		
		matcher = pattern.matcher("me´todo");		
		sb = new StringBuffer();
		while(matcher.find()) {
		    matcher.appendReplacement(sb, tokens.get(matcher.group(1)));
		}
		matcher.appendTail(sb);
		assertEquals("método", sb.toString());
		
		matcher = pattern.matcher("proto´tipo");		
		sb = new StringBuffer();
		while(matcher.find()) {
		    matcher.appendReplacement(sb, tokens.get(matcher.group(1)));
		}
		matcher.appendTail(sb);
		assertEquals("protótipo", sb.toString());
	}
	
	@Test
	public void testPatternsTilde() {
		Matcher matcher = pattern.matcher("computaça˜o");		
		StringBuffer sb = new StringBuffer();
		while(matcher.find()) {
		    matcher.appendReplacement(sb, tokens.get(matcher.group(1)));
		}
		matcher.appendTail(sb);
		assertEquals("computação", sb.toString());
		
		matcher = pattern.matcher("propo˜e");		
		sb = new StringBuffer();
		while(matcher.find()) {
		    matcher.appendReplacement(sb, tokens.get(matcher.group(1)));
		}
		matcher.appendTail(sb);
		assertEquals("propõe", sb.toString());
	}
	
	@Test
	public void testPatternsCedilla() {
		Matcher matcher = pattern.matcher("computac¸ão");		
		StringBuffer sb = new StringBuffer();
		while(matcher.find()) {
		    matcher.appendReplacement(sb, tokens.get(matcher.group(1)));
		}
		matcher.appendTail(sb);
		assertEquals("computação", sb.toString());
	}
	
	@Test
	public void testManyPatterns() {
		Matcher matcher = pattern.matcher("computac¸a˜o");		
		StringBuffer sb = new StringBuffer();
		while(matcher.find()) {
		    matcher.appendReplacement(sb, tokens.get(matcher.group(1)));
		}
		matcher.appendTail(sb);
		assertEquals("computação", sb.toString());
	}
	
}
