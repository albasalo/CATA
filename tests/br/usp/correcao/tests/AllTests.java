package br.usp.correcao.tests;

import br.usp.correcao.tests.model.DictionaryTreeTest;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for br.usp.correcao");
		//$JUnit-BEGIN$
		suite.addTestSuite(DictionaryTreeTest.class);
		//$JUnit-END$
		return suite;
	}

}
