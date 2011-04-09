package br.usp.correcao.model;

public class DictionaryEntry {

	private String error, suggestion;
	
	public DictionaryEntry(String error, String suggestion) {
		this.error = error;
		this.suggestion = suggestion;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	
}
