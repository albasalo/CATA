package br.usp.cata.tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.After;
import org.junit.Before;


public abstract class CataTestCase extends TestCase
{
	
	public CataTestCase(final String name)
	{
	    super(name);
	}
	
	@Before
	protected void setUp() throws Exception {
	    super.setUp();
	}
	
	@After
	protected void tearDown() throws Exception {
	    super.tearDown();
	}
	
	public final Test getRuntimeSuite() {
	    return new TestSuite(getClass());
	}

}
