package br.usp.cata.util;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;

import br.usp.cata.model.Position;

public class TextAnalyzer {
	
	private ArrayList<String> text;
	private byte[] tokenizedTextBytes;
	private HashMap<Integer, Position> startsTokenized, endsTokenized;
	private byte[] lemmatizedTextBytes;
	private HashMap<Integer, Position> startsLemmatized, endsLemmatized;
	
	public TextAnalyzer(ArrayList<String> text, ServletContext servletContext) {
		this.text = text;		
		analyzeText(servletContext);
	}

	public ArrayList<String> getText() {
		return text;
	}

	public void setText(ArrayList<String> text) {
		this.text = text;
	}

	public byte[] getTokenizedTextBytes() {
		return tokenizedTextBytes;
	}

	public HashMap<Integer, Position> getStartsTokenized() {
		return startsTokenized;
	}

	public HashMap<Integer, Position> getEndsTokenized() {
		return endsTokenized;
	}

	public byte[] getLemmatizedTextBytes() {
		return lemmatizedTextBytes;
	}

	public HashMap<Integer, Position> getStartsLemmatized() {
		return startsLemmatized;
	}

	public HashMap<Integer, Position> getEndsLemmatized() {
		return endsLemmatized;
	}
	
	private void analyzeText(ServletContext servletContext) {
		Tokenizer tokenizer = new Tokenizer(servletContext);
		Lemmatizer lemmatizer = new Lemmatizer();
		
		ArrayList<Byte> tokenizedText = new ArrayList<Byte>();
		ArrayList<Byte> lemmatizedText = new ArrayList<Byte>();
		
		startsTokenized = new HashMap<Integer, Position>();
		endsTokenized = new HashMap<Integer, Position>();
		startsLemmatized = new HashMap<Integer, Position>();
		endsLemmatized = new HashMap<Integer, Position>();
		
		tokenizer.tokenize(text, tokenizedText, startsTokenized, endsTokenized);
		tokenizedTextBytes = new byte[tokenizedText.size()];
		for(int i = 0; i < tokenizedText.size(); i++)
			tokenizedTextBytes[i] = tokenizedText.get(i);
		
		lemmatizer.lemmatize(text, lemmatizedText, startsLemmatized, endsLemmatized);
		lemmatizedTextBytes = new byte[lemmatizedText.size()];
		for(int i = 0; i < lemmatizedText.size(); i++)
			lemmatizedTextBytes[i] = lemmatizedText.get(i);
	}
}
