package br.usp.correcao.model;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import java.util.ArrayList;

import org.apache.commons.io.IOUtils;


public class AnalyzedText {

	private ArrayList<AnalyzedLine> analyzedText;
	private int errorsFound;
	
	public AnalyzedText(InputStream is) {;
		this.analyzedText = null;
		this.errorsFound = 0;
		analyzeText(is);
	}
	
	//FIXME achar um metodo melhor para determinar o Encoding
	private Charset guessEncoding(byte[] fileBytes) {
		String[] charsetNames = { "UTF-8", "ISO-8859-1", "US-ASCII", 
				"ISO-8859-15", "cp1252", "UTF-16BE", "UTF-16LE", "UTF-16", "UTF-32", };
		
		Charset charset = null;
		
		for(String charsetName : charsetNames) { 
			if(!Charset.isSupported(charsetName))
				continue;
			
			charset = Charset.forName(charsetName);
			CharsetDecoder decoder = charset.newDecoder();
			try {
				decoder.decode(ByteBuffer.wrap(fileBytes));
			} catch(CharacterCodingException e) {
				charset = null;
				continue;
			}
			break;
		}
		
		if(charset == null)
			 charset = Charset.forName("UTF-8");
		
		return charset;
	}
	
	private void analyzeText(InputStream is) {		
    	try {
    		byte[] fileBytes = IOUtils.toByteArray(is);
    		Charset charset = guessEncoding(fileBytes);
	    	analyzedText = new ArrayList<AnalyzedLine>();
	    	
	    	BufferedReader br = new BufferedReader(
	    			new InputStreamReader(new ByteArrayInputStream(fileBytes), charset));
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
