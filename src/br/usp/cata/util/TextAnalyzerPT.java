package br.usp.cata.util;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;

import br.usp.cata.model.Position;
import br.usp.cata.util.lemmatizer.Lemmatizer;

public class TextAnalyzerPT extends TextAnalyzerLanguage {
	
	ServletContext servletContext;
	
	public TextAnalyzerPT(ServletContext servletContext) {	
		this.servletContext = servletContext;
	}

	@Override
	void analyze(ArrayList<String> text) {
		// Tokenization
		startsTokenized = new HashMap<Integer, Position>();
		endsTokenized = new HashMap<Integer, Position>();
		
		HashMap<Integer, Position> startsList = new HashMap<Integer, Position>();
		HashMap<Integer, Position> endsList = new HashMap<Integer, Position>();
		
		Tokenizer tokenizer = new Tokenizer(servletContext);
		ArrayList<Byte> tokenizedText = new ArrayList<Byte>();
		ArrayList<ArrayList<String>> listOfTokens = new ArrayList<ArrayList<String>>();

		tokenizer.tokenize(text, tokenizedText, startsTokenized, endsTokenized,
				startsList, endsList, listOfTokens);
		//FIXME Get text bytes
		tokenizedTextBytes = new byte[tokenizedText.size()];
		for(int i = 0; i < tokenizedText.size(); i++)
			tokenizedTextBytes[i] = tokenizedText.get(i);
		
		// Lemmatization
		ArrayList<Byte> lemmatizedText = new ArrayList<Byte>();
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
}
