package br.usp.cata.service;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.usp.cata.dao.RuleDAO;
import br.usp.cata.model.ExactMatching;
import br.usp.cata.model.Lemma;
import br.usp.cata.model.Rule;
import br.usp.cata.model.User;


@RequestScoped
@Component
public class RuleService {
	
	private final RuleDAO ruleDAO;
	private final PatternSuggestionPairService patternSuggestionPairService;
	
	public RuleService(final RuleDAO ruleDAO, final PatternSuggestionPairService patternSuggestionPairService) {
		this.ruleDAO = ruleDAO;
		this.patternSuggestionPairService = patternSuggestionPairService;
	}

	public void save(Rule rule) {
		if(rule.getLemmas() != null) {
			for(Lemma lemma : rule.getLemmas())
				patternSuggestionPairService.tokenizePattern(lemma.getPair());
		}
		if(rule.getExactMatchings() != null) {
			for(ExactMatching exactMatching : rule.getExactMatchings())
				patternSuggestionPairService.tokenizePattern(exactMatching.getPair());
		}
		
		ruleDAO.save(rule);
	}
	
	public List<Rule> findAll() {
		return ruleDAO.findAll();
	}
	
	public Rule findByID(Long ruleID) {
		return ruleDAO.findByID(ruleID);
	}
	
	public List<Rule> findByUser(User user) {
		return ruleDAO.findByUser(user);
	}
	
	public List<Rule> findDefault() {
		return ruleDAO.findDefault();
	}
	
}
