package br.usp.cata.model;


public class RuleInstance {
	
	private Rule rule;
	private PatternSuggestionPair patternSuggestionPair;
	
	public RuleInstance(Rule rule, PatternSuggestionPair pattern) {
		this.rule = rule;
		this.patternSuggestionPair = pattern;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public PatternSuggestionPair getPatternSuggestionPair() {
		return patternSuggestionPair;
	}

	public void setPatternSuggestionPair(
			PatternSuggestionPair patternSuggestionPair) {
		this.patternSuggestionPair = patternSuggestionPair;
	}
	
}
