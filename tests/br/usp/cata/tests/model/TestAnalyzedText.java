package br.usp.cata.tests.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.usp.cata.component.Tree;
import br.usp.cata.model.AnalyzedText;
import br.usp.cata.tests.CataTestCase;

public class TestAnalyzedText extends CataTestCase {
	
	Tree tree;
	AnalyzedText analyzedText;

	public TestAnalyzedText(String name) {
		super(name);
	}
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		tree = new Tree();
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	public void testTextTest1() {
		//TODO
		assert(true);
	}

}
