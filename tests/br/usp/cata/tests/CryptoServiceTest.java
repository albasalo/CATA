package br.usp.cata.tests;

import org.junit.Test;

import br.usp.cata.service.CryptoService;


public class CryptoServiceTest extends CataTestCase {

	public CryptoServiceTest(String name) {
		super(name);
	}
	
	@Test
	public void testMD5() {
		assertEquals("e10adc3949ba59abbe56e057f20f883e", CryptoService.generateMd5("123456"));
	}
	
}
