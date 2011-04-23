package br.usp.correcao.tests;

import br.usp.correcao.tests.model.TestAnalyzedLine;
import br.usp.correcao.tests.model.TestRulesTree;
import br.usp.correcao.tests.model.TestAnalyzedText;
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
