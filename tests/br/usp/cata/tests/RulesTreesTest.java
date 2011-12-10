package br.usp.cata.tests;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.arabidopsis.ahocorasick.SearchResult;
import org.junit.Test;

import br.usp.cata.model.ExactMatching;
import br.usp.cata.model.Lemma;
import br.usp.cata.model.PatternSuggestionPair;
import br.usp.cata.model.Rule;
import br.usp.cata.model.RuleInstance;
import br.usp.cata.util.RulesTrees;


public class RulesTreesTest extends CataTestCase {

	private RulesTrees rulesTrees;
	
	public RulesTreesTest(String name) {
		super(name);
	}
	
	public void setUp() throws Exception {
		super.setUp();
		
		rulesTrees = new RulesTrees(null, null, null, null);
	}
	
	@Test
	public void testFindPattern() {
		PatternSuggestionPair patternSuggestionPair = new PatternSuggestionPair();
		patternSuggestionPair.setDefaultPair(true);
		patternSuggestionPair.setPattern("debugar");
		patternSuggestionPair.setTokenizedPatternBytes(" debugar ".getBytes());
		
		Lemma lemma = new Lemma();
		lemma.setPair(patternSuggestionPair);
		
		Set<Lemma> lemmas = new HashSet<Lemma>();
		lemmas.add(lemma);
		
		Rule rule = new Rule();
		rule.setLemmas(lemmas);
		rule.setExactMatchings(new HashSet<ExactMatching>());
		
		List<Rule> rules = new ArrayList<Rule>();
		rules.add(rule);
		
		rulesTrees.addRulesAndPrepare(rules);
		
		Iterator<?> searchResult = rulesTrees.searchLemmas(" vamos debugar o sistema ".getBytes());
		
		assertTrue(searchResult.hasNext());
		
		SearchResult oneResult = (SearchResult) searchResult.next();
		assertEquals("debugar", ((RuleInstance) oneResult.getOutputs().iterator().next()).getPatternSuggestionPair().getPattern());
		
		assertFalse(searchResult.hasNext());		
	}
	
}
