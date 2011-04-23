package br.usp.correcao.model;

public class AnalyzedSegment {
	
	private String text;
	private Rule brokenRule;
	
	public AnalyzedSegment(String text, Rule brokenRule) {
		this.text = text;
		this.brokenRule = brokenRule;
	}

	public String getText() {
		return text;
	}

	public Rule getBrokenRule() {
		return brokenRule;
	}

}
