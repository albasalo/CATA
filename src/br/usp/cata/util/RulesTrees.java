package br.usp.cata.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.arabidopsis.ahocorasick.AhoCorasick;

import br.usp.cata.model.ExactMatching;
import br.usp.cata.model.Lemma;
import br.usp.cata.model.Rule;
import br.usp.cata.model.RuleInstance;
import br.usp.cata.model.Source;
import br.usp.cata.model.User;
import br.usp.cata.service.RuleService;
import br.usp.cata.service.SourceService;
import br.usp.cata.service.UserService;


public class RulesTrees {
	
	private AhoCorasick matchingsTree;
	private AhoCorasick lemmasTree;
	private final RuleService ruleService;
	private final SourceService sourceService;
	private final UserService userService;
	
	public RulesTrees(RuleService ruleService, SourceService sourceService, UserService userService) {
		this.ruleService = ruleService;
		this.sourceService = sourceService;
		this.userService = userService;
		
		lemmasTree = new AhoCorasick();
		matchingsTree = new AhoCorasick();
	}
	
	private void addRulesAndPrepare(List<Rule> rules) {
		for(Rule rule : rules) {
			for(Lemma lemma : rule.getLemmas())
				lemmasTree.add(lemma.getPair().getTokenizedPatternBytes(),
						new RuleInstance(rule, lemma.getPair()));
			for(ExactMatching exactMatching : rule.getExactMatchings())
				matchingsTree.add(exactMatching.getPair().getTokenizedPatternBytes(),
						new RuleInstance(rule, exactMatching.getPair()));
		}
		
		lemmasTree.prepare();
		matchingsTree.prepare();
	}
	
	public void buildDefaultTrees() {
		List<Rule> defaultRules = ruleService.findDefault();
		addRulesAndPrepare(defaultRules);
	}
	
	public void buildAllTrees() {
		List<Rule> allRules = ruleService.findAll();
		addRulesAndPrepare(allRules);
	}
	
	// FIXME
	public void buildUsersTrees(long[] users) {
		List<Rule> rules = new ArrayList<Rule>();
		for(long userID : users) {
			User user = userService.findByID(userID);
			rules.addAll(ruleService.findByUser(user));
		}
		addRulesAndPrepare(rules);
	}
	
	// FIXME
	public void buildSourcesTrees(long[] sources) {
		List<Rule> rules = new ArrayList<Rule>();
		for(long sourceID : sources) {
			Source source = sourceService.findByID(sourceID);
			rules.addAll(ruleService.findBySource(source));
		}
		addRulesAndPrepare(rules);
	}
	
	public Iterator<?> searchExactMatchings(byte[] text) {
		return matchingsTree.search(text);
	}
	
	public Iterator<?> searchLemmas(byte[] text) {
		return lemmasTree.search(text);
	}
	
}
