package br.usp.cata.model;


public class RuleInstance {
	
	private Rule rule;
	private PatternSuggestionElement patternSuggestionElement;
	
	public RuleInstance(Rule rule, PatternSuggestionElement pattern) {
		this.rule = rule;
		this.patternSuggestionElement = pattern;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public PatternSuggestionElement getPatternSuggestionElement() {
		return patternSuggestionElement;
	}

	public void setPatternSuggestionElement(
			PatternSuggestionElement patternSuggestionElement) {
		this.patternSuggestionElement = patternSuggestionElement;
	}
	
}
