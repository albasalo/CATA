package br.usp.cata.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.arabidopsis.ahocorasick.AhoCorasick;

import br.usp.cata.model.ExactMatching;
import br.usp.cata.model.Languages;
import br.usp.cata.model.Lemma;
import br.usp.cata.model.Rule;
import br.usp.cata.model.RuleInstance;
import br.usp.cata.model.Source;
import br.usp.cata.model.User;
import br.usp.cata.service.RuleService;
import br.usp.cata.service.SourceService;
import br.usp.cata.service.UserService;
import br.usp.cata.web.controller.UserSession;


public class RulesTrees {
	
	private AhoCorasick matchingsTree;
	private AhoCorasick lemmasTree;
	private final RuleService ruleService;
	private final SourceService sourceService;
	private final UserService userService;
	private final UserSession userSession;
	
	public RulesTrees(RuleService ruleService, SourceService sourceService, UserService userService, UserSession userSession) {
		this.ruleService = ruleService;
		this.sourceService = sourceService;
		this.userService = userService;
		this.userSession = userSession;
		
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
	
	public void buildDefaultTrees(Languages language) {
		List<Rule> defaultRules = ruleService.findDefault(language);
		if(userService.isAuthenticatedUser())
			defaultRules.addAll(ruleService.findByUser(userSession.getUser()));
		addRulesAndPrepare(defaultRules);
	}
	
	public void buildAllTrees(Languages language) {
		List<Rule> allRules = ruleService.findAll(language);
		addRulesAndPrepare(allRules);
	}
	
	// FIXME
	public void buildUsersTrees(Languages language, long[] users) {
		List<Rule> rules = new ArrayList<Rule>();
		for(long userID : users) {
			User user = userService.findByID(userID);
			rules.addAll(ruleService.findByUser(language, user));
		}
		addRulesAndPrepare(rules);
	}
	
	// FIXME
	public void buildSourcesTrees(Languages language, long[] sources) {
		List<Rule> rules = new ArrayList<Rule>();
		for(long sourceID : sources) {
			Source source = sourceService.findByID(sourceID);
			rules.addAll(ruleService.findBySource(language, source));
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
