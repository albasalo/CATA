package br.usp.cata.util;

import java.util.Iterator;
import java.util.List;

import org.arabidopsis.ahocorasick.AhoCorasick;

import br.usp.cata.model.PatternSuggestionElement;
import br.usp.cata.model.Rule;
import br.usp.cata.model.RuleInstance;
import br.usp.cata.service.RuleService;


public class RulesTrees {
	
	private AhoCorasick matchingsTree;
	private AhoCorasick lemmasTree;
	private final RuleService ruleService;
	
	public RulesTrees(RuleService ruleService) {
		this.ruleService = ruleService;
	}
	
	public void buildDefaultTrees() {
		List<Rule> defaultRules = ruleService.findDefault();
		
		lemmasTree = new AhoCorasick();
		matchingsTree = new AhoCorasick();
		
		for(Rule rule : defaultRules) {
			if(rule.getLemmaElement() != null)
				lemmasTree.add(rule.getLemmaElement().getTokenizedPatternBytes(),
						new RuleInstance(rule, rule.getLemmaElement()));
			
			for(PatternSuggestionElement patternSuggestion : rule.getExactMatchingElements())
				matchingsTree.add(patternSuggestion.getTokenizedPatternBytes(),
						new RuleInstance(rule, patternSuggestion));
		}
		
		lemmasTree.prepare();
		matchingsTree.prepare();
	}
	
	public Iterator<?> searchExactMatchings(byte[] text) {
		return matchingsTree.search(text);
	}
	
	public Iterator<?> searchLemmas(byte[] text) {
		return lemmasTree.search(text);
	}
}
