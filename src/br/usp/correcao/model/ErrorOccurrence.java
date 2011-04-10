package br.usp.correcao.model;

public class ErrorOccurrence {
	
	private DictionaryEntry suggestion;
	private int firstIndex, lastIndex;
	
	public ErrorOccurrence(DictionaryEntry suggestion, int index) {
		this.suggestion = suggestion;
		this.lastIndex = index;
		this.firstIndex = index - suggestion.getLength();
	}

	public DictionaryEntry getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(DictionaryEntry suggestion) {
		this.suggestion = suggestion;
	}
	
	public int getFirstIndex() {
		return firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int index) {
		this.lastIndex = index;
	}

}
