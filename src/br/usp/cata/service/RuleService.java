package br.usp.cata.service;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

import br.usp.cata.dao.RuleDAO;
import br.usp.cata.model.Rule;


@RequestScoped
@Component
public class RuleService {
	
	private final RuleDAO ruleDAO;
	
	public RuleService(final RuleDAO ruleDAO) {
		this.ruleDAO = ruleDAO;
	}

	public void save(Rule rule) {
		ruleDAO.save(rule);
	}
	
	public List<Rule> findDefault() {
		return ruleDAO.findDefault();
	}
}
