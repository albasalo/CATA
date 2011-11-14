package br.usp.cata.service;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.usp.cata.dao.RuleDAO;
import br.usp.cata.model.ExactMatching;
import br.usp.cata.model.Languages;
import br.usp.cata.model.Lemma;
import br.usp.cata.model.Rule;
import br.usp.cata.model.Source;
import br.usp.cata.model.User;


@RequestScoped
@Component
public class RuleService {
	
	private final RuleDAO ruleDAO;
	private final PatternSuggestionPairService patternSuggestionPairService;
	
	public RuleService(final RuleDAO ruleDAO,
			final PatternSuggestionPairService patternSuggestionPairService) {
		this.ruleDAO = ruleDAO;
		this.patternSuggestionPairService = patternSuggestionPairService;
	}
	
	private void tokenizeLemmasAndMatchings(Rule rule) {	
		if(rule.getLemmas() != null) {
			for(Lemma lemma : rule.getLemmas())
				patternSuggestionPairService.tokenizePattern(lemma.getPair(), rule.getLanguage());
		}
		if(rule.getExactMatchings() != null) {
			for(ExactMatching exactMatching : rule.getExactMatchings())
				patternSuggestionPairService.tokenizePattern(exactMatching.getPair(), rule.getLanguage());
		}
	}

	public void save(Rule rule) {
		tokenizeLemmasAndMatchings(rule);
		ruleDAO.save(rule);
	}
	
	public void update(Rule rule) {
		tokenizeLemmasAndMatchings(rule);
		ruleDAO.saveOrUpdate(rule);
	}
	
	public void delete(Rule rule) {
		ruleDAO.delete(rule);
	}
	
	public List<Rule> findAll() {
		return ruleDAO.findAll();
	}
	
	public List<Rule> findAll(Languages language) {
		return ruleDAO.findAll(language);
	}
	
	public Rule findByID(Long ruleID) {
		return ruleDAO.findByID(ruleID);
	}
	
	public List<Rule> findDefault(Languages language) {
		return ruleDAO.findDefault(language);
	}
	
	public List<Rule> findBySource(Languages language, Source source) {
		return ruleDAO.findBySource(language, source);
	}
	
	public List<Rule> findByUser(User user) {
		return ruleDAO.findByUser(user);
	}
	
	public List<Rule> findByUser(Languages language, User user) {
		return ruleDAO.findByUser(language, user);
	}
	
}
