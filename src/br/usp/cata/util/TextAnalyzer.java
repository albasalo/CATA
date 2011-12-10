package br.usp.cata.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import br.usp.cata.model.Languages;
import br.usp.cata.model.Position;


public class TextAnalyzer {
	
	ArrayList<String> text;
	TextAnalyzerLanguage textAnalyzerLanguage;
	
	public TextAnalyzer(ArrayList<String> text, Languages language, ServletContext servletContext) {
		this.text = text;
		
		if(language == Languages.ENGLISH)
			textAnalyzerLanguage = new TextAnalyzerEN();
		else
			textAnalyzerLanguage = new TextAnalyzerPT(servletContext);
		
		textAnalyzerLanguage.analyze(this.text);
	}

	public ArrayList<String> getText() {
		return text;
	}

	public void setText(ArrayList<String> text) {
		this.text = text;
	}

	public byte[] getTokenizedTextBytes() {
		return textAnalyzerLanguage.getTokenizedTextBytes();
	}

	public HashMap<Integer, Position> getStartsTokenized() {
		return textAnalyzerLanguage.getStartsTokenized();
	}

	public HashMap<Integer, Position> getEndsTokenized() {
		return textAnalyzerLanguage.getEndsTokenized();
	}

	public byte[] getLemmatizedTextBytes() {
		return textAnalyzerLanguage.getLemmatizedTextBytes();
	}

	public HashMap<Integer, Position> getStartsLemmatized() {
		return textAnalyzerLanguage.getStartsLemmatized();
	}

	public HashMap<Integer, Position> getEndsLemmatized() {
		return textAnalyzerLanguage.getEndsLemmatized();
	}
	
	public List<String> getKeywords() {
		return textAnalyzerLanguage.getKeywords();
	}
	
}
