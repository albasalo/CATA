package br.usp.cata.util;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;

import br.usp.cata.model.Languages;
import br.usp.cata.model.Position;
import br.usp.cata.util.lemmatizer.Lemmatizer;


public class TextAnalyzer {
	
	private ArrayList<String> text;
	private byte[] tokenizedTextBytes;
	private HashMap<Integer, Position> startsTokenized, endsTokenized;
	private byte[] lemmatizedTextBytes;
	private HashMap<Integer, Position> startsList, endsList;
	private HashMap<Integer, Position> startsLemmatized, endsLemmatized;
	private ArrayList<ArrayList<String>> listOfTokens;
	
	public TextAnalyzer(ArrayList<String> text, Languages language, ServletContext servletContext) {
		this.text = text;		
		analyzeText(language, servletContext);
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
	
	private void analyzeText(Languages language, ServletContext servletContext) {
		Tokenizer tokenizer = new Tokenizer(servletContext);
		ArrayList<Byte> tokenizedText = new ArrayList<Byte>();
		
		startsTokenized = new HashMap<Integer, Position>();
		endsTokenized = new HashMap<Integer, Position>();
		startsList = new HashMap<Integer, Position>();
		endsList = new HashMap<Integer, Position>();
		listOfTokens = new ArrayList<ArrayList<String>>();
		
		tokenizer.tokenize(text, tokenizedText, startsTokenized, endsTokenized,
				startsList, endsList, listOfTokens);
		//FIXME Get text bytes
		tokenizedTextBytes = new byte[tokenizedText.size()];
		for(int i = 0; i < tokenizedText.size(); i++)
			tokenizedTextBytes[i] = tokenizedText.get(i);
		
		ArrayList<Byte> lemmatizedText = new ArrayList<Byte>();
		if(language == Languages.PORTUGUESE) {
			Lemmatizer lemmatizer = new Lemmatizer();

			startsLemmatized = new HashMap<Integer, Position>();
			endsLemmatized = new HashMap<Integer, Position>();
			
			int offset = 0;
			for(ArrayList<String> tokens : listOfTokens) {
				lemmatizer.lemmatize(tokens, offset, lemmatizedText, startsList, endsList,
						startsLemmatized, endsLemmatized);
				offset += tokens.size();
			}
			for(byte b : " ".getBytes())
				lemmatizedText.add(b);
			lemmatizedTextBytes = new byte[lemmatizedText.size()];
			for(int i = 0; i < lemmatizedText.size(); i++)
				lemmatizedTextBytes[i] = lemmatizedText.get(i);
		}
		else {
			//TODO Lemmatizer for en
			lemmatizedTextBytes = new byte[0];
		}
	}
	
}
