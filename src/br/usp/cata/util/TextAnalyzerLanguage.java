package br.usp.cata.util;

import java.util.ArrayList;
import java.util.HashMap;

import br.usp.cata.model.Position;


public abstract class TextAnalyzerLanguage {

	protected byte[] tokenizedTextBytes;
	protected HashMap<Integer, Position> startsTokenized, endsTokenized;
	protected byte[] lemmatizedTextBytes;
	protected HashMap<Integer, Position> startsLemmatized, endsLemmatized;
	
	public byte[] getTokenizedTextBytes() {
		return tokenizedTextBytes;
	}

	public void setTokenizedTextBytes(byte[] tokenizedTextBytes) {
		this.tokenizedTextBytes = tokenizedTextBytes;
	}

	public HashMap<Integer, Position> getStartsTokenized() {
		return startsTokenized;
	}

	public void setStartsTokenized(HashMap<Integer, Position> startsTokenized) {
		this.startsTokenized = startsTokenized;
	}

	public HashMap<Integer, Position> getEndsTokenized() {
		return endsTokenized;
	}

	public void setEndsTokenized(HashMap<Integer, Position> endsTokenized) {
		this.endsTokenized = endsTokenized;
	}

	public byte[] getLemmatizedTextBytes() {
		return lemmatizedTextBytes;
	}

	public void setLemmatizedTextBytes(byte[] lemmatizedTextBytes) {
		this.lemmatizedTextBytes = lemmatizedTextBytes;
	}

	public HashMap<Integer, Position> getStartsLemmatized() {
		return startsLemmatized;
	}

	public void setStartsLemmatized(HashMap<Integer, Position> startsLemmatized) {
		this.startsLemmatized = startsLemmatized;
	}

	public HashMap<Integer, Position> getEndsLemmatized() {
		return endsLemmatized;
	}

	public void setEndsLemmatized(HashMap<Integer, Position> endsLemmatized) {
		this.endsLemmatized = endsLemmatized;
	}

	abstract void analyze(ArrayList<String> text);

}
