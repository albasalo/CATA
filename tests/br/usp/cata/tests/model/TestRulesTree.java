package br.usp.cata.tests.model;

import java.util.Iterator;

import org.arabidopsis.ahocorasick.SearchResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.usp.cata.model.Rule;
import br.usp.cata.model.RulesTree;
import br.usp.cata.tests.CorrecaoTestCase;


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
		Iterator<?> searcher = tree.search(" cluster ".getBytes());
		assertTrue(searcher != null && searcher.hasNext());
        SearchResult result = (SearchResult) searcher.next();
        Rule brokenRule = (Rule) (result.getOutputs().iterator().next());
        assertEquals("cluster", brokenRule.getPattern());
	}

}
