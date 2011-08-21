package br.usp.cata.model;


public class Position {

	private int lineNumber;
	private int charIndex;
	
	public Position(int lineNumber, int charIndex) {
		this.lineNumber = lineNumber;
		this.charIndex = charIndex;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public int getCharIndex() {
		return charIndex;
	}

	public void setCharIndex(int charIndex) {
		this.charIndex = charIndex;
	}

	public int compareTo(Position p) {
		if(this.lineNumber == p.getLineNumber())
			return this.charIndex - p.getCharIndex();
		
		return this.lineNumber - p.getLineNumber();
	}
	
}
