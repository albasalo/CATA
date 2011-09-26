package br.usp.cata.util.lemmatizer;

public class ExpressionsChar {
	
	private char character;
	private int tag;
	private ExpressionsChar less;
	private ExpressionsChar equal;
	private ExpressionsChar greater;
	
	public char getCharacter() {
		return character;
	}
	
	public void setCharacter(char character) {
		this.character = character;
	}
	
	public int getTag() {
		return tag;
	}
	
	public void setTag(int tag) {
		this.tag = tag;
	}
	
	public ExpressionsChar getLess() {
		return less;
	}
	
	public void setLess(ExpressionsChar less) {
		this.less = less;
	}
	
	public ExpressionsChar getEqual() {
		return equal;
	}
	
	public void setEqual(ExpressionsChar equal) {
		this.equal = equal;
	}
	
	public ExpressionsChar getGreater() {
		return greater;
	}
	
	public void setGreater(ExpressionsChar greater) {
		this.greater = greater;
	}
	
}
