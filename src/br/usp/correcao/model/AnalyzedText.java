package br.usp.correcao.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;


public class AnalyzedText {

	private InputStream is;
	private ArrayList<AnalyzedLine> analyzedText;
	private int errorsFound;
	
	public AnalyzedText(InputStream is) {
		this.is = is;
		this.analyzedText = null;
		this.errorsFound = 0;
		analyzeText();
	}
	
	private void analyzeText() {
    	try {
	    	analyzedText = new ArrayList<AnalyzedLine>();
	    	BufferedReader br = new BufferedReader(new InputStreamReader(is));
		    String inputLine;
	    	while((inputLine = br.readLine()) != null) {
	    		AnalyzedLine l = new AnalyzedLine(inputLine);
		    	errorsFound += l.getErrorsFound();
		    	analyzedText.add(l);
	    	}
    		br.close();
    		is.close();
    	} catch(Exception e) {
    		//TODO
    		e.printStackTrace();
    	}
	}
	
	public ArrayList<AnalyzedLine> getAnalyzedText() {
		return analyzedText;
	}
	
	public int getErrorsFound() {
		return errorsFound;
	}
	
}
