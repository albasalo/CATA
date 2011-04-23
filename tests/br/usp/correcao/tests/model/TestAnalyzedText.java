package br.usp.correcao.tests.model;

import java.io.InputStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.usp.correcao.component.Tree;
import br.usp.correcao.model.AnalyzedLine;
import br.usp.correcao.model.AnalyzedText;
import br.usp.correcao.tests.CorrecaoTestCase;

public class TestAnalyzedText extends CorrecaoTestCase {
	
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
		InputStream is = TestAnalyzedText.class.getResourceAsStream(
				"/br/usp/correcao/tests/resources/Test1.txt");
		analyzedText = new AnalyzedText(is);
		ArrayList<AnalyzedLine> text = analyzedText.getAnalyzedText();
		assertTrue(!text.isEmpty());
		assertEquals("Test 1:", text.get(0).getAnalyzedLine().get(0).getText());
		assertEquals("Test ", text.get(1).getAnalyzedLine().get(0).getText());
		assertEquals("cluster", text.get(1).getAnalyzedLine().get(1).getText());
		assertEquals(".", text.get(1).getAnalyzedLine().get(2).getText());
		assertEquals("Test ", text.get(2).getAnalyzedLine().get(0).getText());
		assertEquals("core", text.get(2).getAnalyzedLine().get(1).getText());
		assertEquals(".", text.get(2).getAnalyzedLine().get(2).getText());
	}

}
