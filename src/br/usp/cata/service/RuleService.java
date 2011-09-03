package br.usp.cata.service;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.usp.cata.dao.RuleDAO;
import br.usp.cata.model.PatternSuggestionElement;
import br.usp.cata.model.Rule;


@RequestScoped
@Component
public class RuleService {
	
	private final RuleDAO ruleDAO;
	private final PatternSuggestionElementService patternSuggestionElementService;
	
	public RuleService(final RuleDAO ruleDAO, final PatternSuggestionElementService patternSuggestionElementService) {
		this.ruleDAO = ruleDAO;
		this.patternSuggestionElementService = patternSuggestionElementService;
	}

	public void save(Rule rule) {
		//FIXME Consertar - não está salvando direito
		if(rule.getLemmaElement() != null)
			patternSuggestionElementService.tokenizePattern(rule.getLemmaElement());
		if(rule.getExactMatchingElement() != null)
			patternSuggestionElementService.tokenizePattern(rule.getExactMatchingElement());
		if(rule.getExactMatchingElements() != null) {
			for(PatternSuggestionElement pattern : rule.getExactMatchingElements())
				patternSuggestionElementService.tokenizePattern(pattern);
		}
		
		ruleDAO.save(rule);
	}
	
	public List<Rule> findAll() {
		return ruleDAO.findAll();
	}
	
	public Rule findByID(Long ruleID) {
		return ruleDAO.findByID(ruleID);
	}
	
	public List<Rule> findDefault() {
		return ruleDAO.findDefault();
	}
	
}
