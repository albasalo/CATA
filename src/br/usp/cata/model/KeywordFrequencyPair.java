package br.usp.cata.model;

public class KeywordFrequencyPair implements Comparable<KeywordFrequencyPair> {
	
	private String keyword;
	private int frequency;
	
	public KeywordFrequencyPair(String keyword, int frequency) {
		this.keyword = keyword;
		this.frequency = frequency;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	public int compareTo(KeywordFrequencyPair arg0) {
		return arg0.frequency - this.getFrequency();
	}
	
}
