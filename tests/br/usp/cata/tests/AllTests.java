package br.usp.cata.tests;

import br.usp.cata.tests.model.TestAnalyzedLine;
import br.usp.cata.tests.model.TestAnalyzedText;
import br.usp.cata.tests.model.TestRulesTree;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for br.usp.correcao");
		//$JUnit-BEGIN$
		suite.addTestSuite(TestAnalyzedLine.class);
		suite.addTestSuite(TestRulesTree.class);
		suite.addTestSuite(TestAnalyzedText.class);
		//$JUnit-END$
		return suite;
	}

}
