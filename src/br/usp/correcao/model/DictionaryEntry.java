package br.usp.correcao.model;

public class DictionaryEntry {

	private String error, suggestion;
	private Type type;
	private int length;
	
	public DictionaryEntry(String error, String suggestion, Type type) {
		this.error = error;
		this.length = error.getBytes().length;
		this.suggestion = suggestion;
		this.type = type;
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
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public int getLength() {
		return length;
	}
	
}
