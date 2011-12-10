package br.usp.cata.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import ptstemmer.Stemmer;
import br.usp.cata.component.keywordExtraction.StemmerPT;
import br.usp.cata.component.keywordExtraction.StopWords;
import br.usp.cata.model.KeywordFrequencyPair;
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
		
		
		//KeywordExtraction
		Stemmer st = StemmerPT.getStemmerPT();
		HashSet<String> stopWordsPT = StopWords.getStopWordsPT();
		HashMap<String, Integer> wordsFrequency = new HashMap<String, Integer>();
		for(ArrayList<String> sentence : listOfTokens) {
			for(String token : sentence) {
				if(!stopWordsPT.contains(token) && !token.matches(".*\\d.*")) {
					// Stemming
					String stemmedToken = st.getWordStem(token);
					int frequency;
					if(wordsFrequency.containsKey(stemmedToken))
						frequency = wordsFrequency.get(stemmedToken) + 1;
					else
						frequency = 1;
					wordsFrequency.put(stemmedToken, frequency);
				}
			}
		}
		
		Iterator<String> wordsFrequencyIterator = wordsFrequency.keySet().iterator();
		List<KeywordFrequencyPair> keywordsList = new ArrayList<KeywordFrequencyPair>();
		while(wordsFrequencyIterator.hasNext()) {
			String keyword = wordsFrequencyIterator.next();
			keywordsList.add(new KeywordFrequencyPair(keyword, wordsFrequency.get(keyword)));
		}
		
		Collections.sort(keywordsList);
		
		keywords = new ArrayList<String>();
		for(int i = 0; i < 0.15*keywordsList.size(); i++)
			keywords.add(keywordsList.get(i).getKeyword());
		
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
