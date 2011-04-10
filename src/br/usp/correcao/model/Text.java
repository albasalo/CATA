package br.usp.correcao.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;


public class Text {

	private InputStream is;
	private ArrayList<Line> analyzedText;
	private int errorsFound;
	
	public Text(InputStream is) {
		this.is = is;
		this.analyzedText = null;
		this.errorsFound = 0;
	}
	
	public ArrayList<Line> analyzeText() throws IOException {
	    if(analyzedText == null) {
	    	analyzedText = new ArrayList<Line>();
	    	BufferedReader br = new BufferedReader(new InputStreamReader(is));
		    String inputLine;
	    	while((inputLine = br.readLine()) != null) {
	    		Line l = new Line(inputLine);
	    		l.analyze();
	    		errorsFound += l.getErrorsFound();
	    		analyzedText.add(l);
	    	}
	    	br.close();
	    	//TODO verificar isso
	    	is.close();
	    }
    	
	    return analyzedText;
	}
	
	public int getErrorsFound() {
		return errorsFound;
	}
	
}
