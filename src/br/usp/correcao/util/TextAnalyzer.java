package br.usp.correcao.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.arabidopsis.ahocorasick.SearchResult;

import br.usp.correcao.component.Dictionary;
import br.usp.correcao.model.DictionaryEntry;
import br.usp.correcao.model.ErrorOccurrence;


public class TextAnalyzer {

	private InputStream is;
	private ArrayList<ErrorOccurrence> errors;
	
	public TextAnalyzer(InputStream is) {
		this.is = is;
		this.errors = new ArrayList<ErrorOccurrence>();
	}
	
	public ArrayList<ErrorOccurrence> getSuggestions() throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(is));
	    
	    String inputLine;
	    try {
	    	for (int lineNum = 1; (inputLine = br.readLine()) != null; lineNum++) {
	    		Iterator<?> searcher = Dictionary.getTree().search(inputLine);
	    	    while (searcher != null && searcher.hasNext()) {
	    	        SearchResult result = (SearchResult)searcher.next();
	    	        errors.add(new ErrorOccurrence((DictionaryEntry)result.getOutputs().iterator().next(), lineNum));
	    	    }
	    	}
	    } catch (IOException ioe) {
	    	throw new IOException();
	    }
	    return errors;
	}
	
}
