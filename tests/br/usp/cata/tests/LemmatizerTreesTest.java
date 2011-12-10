package br.usp.cata.tests;

import org.junit.Before;
import org.junit.Test;

import br.usp.cata.component.lemmatizer.LemmatizerTrees;


public class LemmatizerTreesTest extends CataTestCase {
	
	public LemmatizerTreesTest(String name) {
		super(name);
	}
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		new LemmatizerTrees();
	}

	@Test
	public void testTrees() {
		assertNotNull(LemmatizerTrees.getAccentsRoot());
		assertNotNull(LemmatizerTrees.getExpressionsRoot());
		assertNotNull(LemmatizerTrees.getSuffixesRoot());
	}
	
}
