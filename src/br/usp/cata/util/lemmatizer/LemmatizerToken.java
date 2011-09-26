package br.usp.cata.util.lemmatizer;

public class LemmatizerToken {
	
	private String result;
	private boolean printable;
	private String word;
	// space + word + space
	private String sWordS;
	private String lemma;
	private int tag;
	private String[] sfxLemmas;
	private double[] sfxTags;
	private double[] prevs;
	private double[] nexts;
	private LemmatizerToken prev;
	private LemmatizerToken next;
	
	public LemmatizerToken() {
		sfxLemmas = new String[LemmatizerConstants.numberOfTags];
		sfxTags = new double[LemmatizerConstants.numberOfTags];
		prevs = new double[LemmatizerConstants.numberOfTags];
		nexts = new double[LemmatizerConstants.numberOfTags];
		
		// Default values
		printable = false;
		word = ".";
		sWordS = " . ";
		lemma = ".";
		tag = LemmatizerConstants.defaultTag;
		
		this.reset();
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public boolean isPrintable() {
		return printable;
	}

	public void setPrintable(boolean printable) {
		this.printable = printable;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getSWordS() {
		return sWordS;
	}

	public void setSWordS(String sWordS) {
		this.sWordS = sWordS;
	}

	public String getLemma() {
		return lemma;
	}

	public void setLemma(String lemma) {
		this.lemma = lemma;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public String[] getSfxLemmas() {
		return sfxLemmas;
	}

	public void setSfxLemmas(String[] sfxLemmas) {
		this.sfxLemmas = sfxLemmas;
	}
	
	public double[] getSfxTags() {
		return sfxTags;
	}

	public void setSfxTags(double[] sfxTags) {
		this.sfxTags = sfxTags;
	}

	public double[] getPrevs() {
		return prevs;
	}

	public void setPrevs(double[] prevs) {
		this.prevs = prevs;
	}

	public double[] getNexts() {
		return nexts;
	}

	public void setNexts(double[] nexts) {
		this.nexts = nexts;
	}

	public LemmatizerToken getPrev() {
		return prev;
	}

	public void setPrev(LemmatizerToken prev) {
		this.prev = prev;
	}

	public LemmatizerToken getNext() {
		return next;
	}

	public void setNext(LemmatizerToken next) {
		this.next = next;
	}
	
	public void reset() {
		result = "";
		for(int i = 0; i < LemmatizerConstants.numberOfTags; i++) {
			prevs[i] = sfxTags[i] = nexts[i] = 0.00;
			sfxLemmas[i] = ""; 
		}
	}
	
}
