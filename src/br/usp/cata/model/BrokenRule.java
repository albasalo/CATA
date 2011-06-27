package br.usp.cata.model;

public class BrokenRule {
	
	private Rule rule;
	private int firstIndex, lastIndex;
	
	public BrokenRule(Rule rule, int index) {
		this.rule = rule;
		this.lastIndex = index;
		this.firstIndex = index - rule.getLength();
	}

	public Rule getSuggestion() {
		return rule;
	}

	public void setSuggestion(Rule suggestion) {
		this.rule = suggestion;
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
