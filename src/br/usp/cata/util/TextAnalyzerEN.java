package br.usp.cata.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import br.usp.cata.model.Position;


public class TextAnalyzerEN extends TextAnalyzerLanguage {
	
	private StanfordCoreNLP pipeline;

	public TextAnalyzerEN() {
		Properties props = new Properties();
	    props.put("annotators", "tokenize, ssplit, pos, lemma");
	    pipeline = new StanfordCoreNLP(props);
	}

	@Override
	void analyze(ArrayList<String> text) {
		startsTokenized = new HashMap<Integer, Position>();
		endsTokenized = new HashMap<Integer, Position>();
		
		startsLemmatized = new HashMap<Integer, Position>();
		endsLemmatized = new HashMap<Integer, Position>();
		
		ArrayList<Byte> tokenizedText = new ArrayList<Byte>();
		ArrayList<Byte> lemmatizedText = new ArrayList<Byte>();
		
		int blankBytesLength = " ".getBytes().length;
		
		int lineNum = 0;
		
		int startToken, endToken, startLemma, endLemma;
		Position startPosition, endPosition;
		
		for(String line : text) {
			Annotation document = new Annotation(line);
			pipeline.annotate(document);
			
			List<CoreMap> sentences = document.get(SentencesAnnotation.class);
			for(CoreMap sentence: sentences) {
				for(CoreLabel token: sentence.get(TokensAnnotation.class)) {
					startPosition = new Position(lineNum, token.beginPosition());
					endPosition = new Position(lineNum, token.endPosition());
					
					startToken = tokenizedText.size();				
					String word = " " + token.get(TextAnnotation.class).toLowerCase();			
					for(byte b : word.getBytes())
						tokenizedText.add(b);			
					endToken = tokenizedText.size() + blankBytesLength;
							
					startsTokenized.put(startToken, startPosition);
					endsTokenized.put(endToken, endPosition);
					
					startLemma = lemmatizedText.size();
					String lemma = " " + token.get(LemmaAnnotation.class).toLowerCase();
					for(byte b : lemma.getBytes())
						lemmatizedText.add(b);
					endLemma = lemmatizedText.size() + blankBytesLength;
					
					startsLemmatized.put(startLemma, startPosition);
					endsLemmatized.put(endLemma, endPosition);
				}
		    }
			
			lineNum++;
		}
		
		for(byte b : " ".getBytes()) {
    		tokenizedText.add(b);
    		lemmatizedText.add(b);
		}
		
		tokenizedTextBytes = new byte[tokenizedText.size()];
		for(int i = 0; i < tokenizedText.size(); i++)
			tokenizedTextBytes[i] = tokenizedText.get(i);
		
		lemmatizedTextBytes = new byte[lemmatizedText.size()];
		for(int i = 0; i < lemmatizedText.size(); i++)
			lemmatizedTextBytes[i] = lemmatizedText.get(i);
	}
	
}
