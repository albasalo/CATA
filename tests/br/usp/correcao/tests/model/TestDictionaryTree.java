package br.usp.correcao.tests.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import br.usp.correcao.model.DictionaryEntry;
import br.usp.correcao.model.DictionaryTree;
import br.usp.correcao.tests.CorrecaoTestCase;

public class TestDictionaryTree extends CorrecaoTestCase {
	
	DictionaryTree tree;

	public TestDictionaryTree(String name) {
		super(name);
	}
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		tree = new DictionaryTree();
	}

	@Test
	public void testSearchEmpty() {
		Iterator<?> searcher = tree.search("");
		assert(searcher != null && !searcher.hasNext());
	}
	
	@Test
	public void testSearchAllErrors() {
		String dicFile = "/br/usp/correcao/resources/dictionary.txt";
		Pattern firstDelimiter = Pattern.compile("\t");
		Pattern secondDelimiter = Pattern.compile(";");
		
		InputStream is = TestDictionaryTree.class.getResourceAsStream(dicFile);
		Reader reader = new BufferedReader(new InputStreamReader(is));
		BufferedReader br = new BufferedReader(reader);
	    String line;
	    try {
	    	while ((line = br.readLine()) != null) {
	    		if(line.equals("<error>") || line.equals("<warning>"))
	    			continue;
				String[] entry = firstDelimiter.split(line);
				if(entry != null) {
					String[] errors = secondDelimiter.split(entry[0]);
					for(String err : errors) {
						Iterator<?> searcher = tree.search(err);
						assert(searcher != null && searcher.hasNext() &&
								((DictionaryEntry)searcher.next()).getError().equals(err));
					}
				}
	    	}	
	    	is.close();
	    } catch (IOException ioe) {
	    	//TODO
	    	ioe.printStackTrace();
	    }
	}
	
	@Test
	public void testSearchAllErrors2() {
		String dicFile = "/br/usp/correcao/resources/dictionary.txt";
		Pattern firstDelimiter = Pattern.compile("\t");
		Pattern secondDelimiter = Pattern.compile(";");
		
		InputStream is = TestDictionaryTree.class.getResourceAsStream(dicFile);
		Reader reader = new BufferedReader(new InputStreamReader(is));
		BufferedReader br = new BufferedReader(reader);
	    String line;
	    try {
	    	while ((line = br.readLine()) != null) {
	    		if(line.equals("<error>") || line.equals("<warning>"))
	    			continue;
				String[] entry = firstDelimiter.split(line);
				if(entry != null) {
					String[] errors = secondDelimiter.split(entry[0]);
					for(String err : errors) {
						Iterator<?> searcher = tree.search("  " + err + "  ");
						assert(searcher != null && searcher.hasNext() &&
								((DictionaryEntry)searcher.next()).getError().equals(err));
					}
				}
	    	}	
	    	is.close();
	    } catch (IOException ioe) {
	    	//TODO
	    	ioe.printStackTrace();
	    }
	}
	
	@Test
	public void testSearchMetadata() {
		Iterator<?> searcher = tree.search("<error>");
		assert(searcher != null && !searcher.hasNext());
		searcher = tree.search("<warning>");
		assert(searcher != null && !searcher.hasNext());
	}

}
