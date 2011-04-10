package br.usp.correcao.tests;

import br.usp.correcao.tests.model.TestDictionaryTree;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for br.usp.correcao");
		//$JUnit-BEGIN$
		suite.addTestSuite(TestDictionaryTree.class);
		//$JUnit-END$
		return suite;
	}

}
