package br.usp.cata.util.lemmatizer;


public class SuffixesChar {
	
	private char character;
	private int exactTag;
	private String[] lemma;
	private double[] tags;
	private SuffixesChar less;
	private SuffixesChar equal;
	private SuffixesChar greater;
	
	public SuffixesChar() {
		lemma = new String[LemmatizerConstants.numberOfTags];
		tags = new double[LemmatizerConstants.numberOfTags];
	}

	public char getCharacter() {
		return character;
	}

	public void setCharacter(char character) {
		this.character = character;
	}
	
	public int getExactTag() {
		return exactTag;
	}

	public void setExactTag(int exactTag) {
		this.exactTag = exactTag;
	}

	public String[] getLemma() {
		return lemma;
	}

	public void setLemma(String[] lemma) {
		this.lemma = lemma;
	}

	public double[] getTags() {
		return tags;
	}

	public void setTags(double[] tags) {
		this.tags = tags;
	}

	public SuffixesChar getLess() {
		return less;
	}

	public void setLess(SuffixesChar less) {
		this.less = less;
	}

	public SuffixesChar getEqual() {
		return equal;
	}

	public void setEqual(SuffixesChar equal) {
		this.equal = equal;
	}

	public SuffixesChar getGreater() {
		return greater;
	}

	public void setGreater(SuffixesChar greater) {
		this.greater = greater;
	}
	
}
