package br.usp.correcao.tests.model;

import java.io.InputStream;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.arabidopsis.ahocorasick.SearchResult;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.usp.correcao.model.Rule;
import br.usp.correcao.model.RulesTree;
import br.usp.correcao.tests.CorrecaoTestCase;


public class TestRulesTree extends CorrecaoTestCase {
	
	RulesTree tree;

	public TestRulesTree(String name) {
		super(name);
	}
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		tree = new RulesTree();
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	public void testSearchEmpty() {
		Iterator<?> searcher = tree.search("".getBytes());
		assertTrue(searcher != null && !searcher.hasNext());
	}
	
	@Test
	public void testSearchNoErrors() {
		Iterator<?> searcher = tree.search("No error.".getBytes());
		assertTrue(searcher != null && !searcher.hasNext());
	}

	@Test
	public void testSearchWordCluster1() {
		Iterator<?> searcher = tree.search("cluster".getBytes());
		assertTrue(searcher != null && searcher.hasNext());
        SearchResult result = (SearchResult) searcher.next();
        Rule brokenRule = (Rule) (result.getOutputs().iterator().next());
        assertEquals("cluster", brokenRule.getPattern());
	}
	
	@Test
	public void testSearchAllPatterns() throws Exception {
		InputStream is = RulesTree.class.getResourceAsStream("/br/usp/correcao/resources/rules.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(is);			
		doc.getDocumentElement().normalize();
		
		NodeList nodeList = doc.getElementsByTagName("Rule");
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element ruleElement = (Element) nodeList.item(i);
			NodeList propertyNodes = ruleElement.getElementsByTagName("Pattern");
			String pattern = propertyNodes.item(0).getFirstChild().getNodeValue();
			
			Iterator<?> searcher = tree.search(pattern.toLowerCase().getBytes());
			assertTrue(searcher != null && searcher.hasNext());
			
	        SearchResult result = (SearchResult) searcher.next();
	        Rule brokenRule = (Rule) (result.getOutputs().iterator().next());
	        assertEquals(pattern, brokenRule.getPattern());
		}
	}

}
