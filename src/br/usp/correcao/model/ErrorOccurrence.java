package br.usp.correcao.model;

public class ErrorOccurrence {
	
	private DictionaryEntry suggestion;
	private int lineNum;
	
	public ErrorOccurrence(DictionaryEntry suggestion, int lineNum) {
		this.suggestion = suggestion;
		this.lineNum = lineNum;
	}

	public DictionaryEntry getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(DictionaryEntry suggestion) {
		this.suggestion = suggestion;
	}

	public int getLineNum() {
		return lineNum;
	}

	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}

}
