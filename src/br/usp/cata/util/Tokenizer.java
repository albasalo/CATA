package br.usp.cata.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import br.usp.cata.model.Position;
import br.usp.pcs.lta.cogroo.configuration.LegacyRuntimeConfiguration;
import br.usp.pcs.lta.cogroo.configuration.RuntimeConfigurationI;
import br.usp.pcs.lta.cogroo.entity.Sentence;
import br.usp.pcs.lta.cogroo.entity.Token;
import br.usp.pcs.lta.cogroo.tools.sentencedetector.SentenceDetectorME;
import br.usp.pcs.lta.cogroo.tools.tokenizer.CogrooTokenizer;


public class Tokenizer {
	
	private RuntimeConfigurationI config;
	private SentenceDetectorME sentenceDetector;
	private CogrooTokenizer tokenizer;
	
	public Tokenizer(ServletContext servletContext) {
		config = new LegacyRuntimeConfiguration(
				servletContext.getRealPath("/WEB-INF/classes/br/usp/cata/resources"));
		sentenceDetector = config.getSentenceDetector();
		tokenizer = config.getTokenizer();
	}
	
	public void tokenize(ArrayList<String> text, ArrayList<Byte> tokenizedText, 
			HashMap<Integer, Position> starts, HashMap<Integer, Position> ends,
			HashMap<Integer, Position> startsList, HashMap<Integer, Position> endsList,
			List<ArrayList<String>> tokensList) {
		
		int lineNum = 0;
		int tokensListLength = 0;
		
		for(String line : text) {
			ArrayList<String> sentenceTokensList = new ArrayList<String>();
			List<Sentence> sentences;
			byte[] tokenBytes;
			int start, startToken, end, endToken;
			int blankBytesLength = " ".getBytes().length;
			
			sentences = sentenceDetector.detectSentences(line);
			for(Sentence sentence : sentences)
				tokenizer.tokenizeSentence(sentence);
			
			for(Sentence sentence : sentences) {
				for(Token token : sentence.getTokens()) {
					int offSet = sentence.getOffset();
					start = offSet + token.getSpan().getStart();
					end = offSet + token.getSpan().getEnd();
					
					startToken = tokenizedText.size();
					String substring = line.substring(start, end).toLowerCase();
					sentenceTokensList.add(substring);
					tokenBytes = (" " + substring).getBytes();				
					for(byte tokenByte : tokenBytes)
						tokenizedText.add(tokenByte);			
					endToken = tokenizedText.size() + blankBytesLength;
					
					starts.put(startToken, new Position(lineNum, start));
					ends.put(endToken, new Position(lineNum, end));
					
					startsList.put(tokensListLength, new Position(lineNum, start));
					endsList.put(tokensListLength, new Position(lineNum, end));
					tokensListLength++;
				}
			}
			tokensList.add(sentenceTokensList);
			lineNum++;
		}	
    	for(byte b : " ".getBytes())
    		tokenizedText.add(b);
	}
	
	public byte[] tokenize(String text) {
		String tokenizedText = "";
		List<Sentence> sentences;
		
		sentences = sentenceDetector.detectSentences(text);
		for(Sentence sentence : sentences)
			tokenizer.tokenizeSentence(sentence);
		
		for(Sentence sentence : sentences) {
			for(Token token : sentence.getTokens()) {
				int offSet = sentence.getOffset();			
				tokenizedText += (" " + text.substring(offSet + token.getSpan().getStart(),
						offSet + token.getSpan().getEnd())).toLowerCase();				
			}
		}
		tokenizedText += " ";
	
		return tokenizedText.getBytes();
	}

}
