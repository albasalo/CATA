package br.usp.cata.util.lemmatizer;


public class AccentsChar {
	
	private char character;
	private AccentsLemmaTagChar tagLemma;
	private AccentsChar less;
	private AccentsChar equal;
	private AccentsChar greater;
	
	public char getCharacter() {
		return character;
	}
	
	public void setCharacter(char character) {
		this.character = character;
	}
	
	public AccentsLemmaTagChar getTagLemma() {
		return tagLemma;
	}
	
	public void setTagLemma(AccentsLemmaTagChar tagLemma) {
		this.tagLemma = tagLemma;
	}
	
	public AccentsChar getLess() {
		return less;
	}
	
	public void setLess(AccentsChar less) {
		this.less = less;
	}
	
	public AccentsChar getEqual() {
		return equal;
	}
	
	public void setEqual(AccentsChar equal) {
		this.equal = equal;
	}
	
	public AccentsChar getGreater() {
		return greater;
	}
	
	public void setGreater(AccentsChar greater) {
		this.greater = greater;
	}
	
}
