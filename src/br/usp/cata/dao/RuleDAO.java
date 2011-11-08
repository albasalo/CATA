package br.usp.cata.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.util.hibernate.SessionCreator;
import br.usp.cata.model.Languages;
import br.usp.cata.model.Rule;
import br.usp.cata.model.Source;
import br.usp.cata.model.User;


@Component
@RequestScoped
public class RuleDAO extends AbstractDAO<Long, Rule>{

	public RuleDAO(SessionCreator sessionCreator) {
		super(sessionCreator);
	}
	
	public Rule findByID(Long ruleID) {
		List<Rule> rules = findByCriteria(Restrictions.eq("ruleID", ruleID));		
		return (rules.isEmpty() ? null : rules.get(0));
	}
	
	public List<Rule> findAll(Languages language) {
		return findByCriteria(Restrictions.eq("language", language));
	}
	
	public List<Rule> findDefault(Languages language) {
		return findByCriteria(Restrictions.and(Restrictions.eq("language", language), Restrictions.eq("defaultRule", true)));
	}
	
	public List<Rule> findBySource(Languages language, Source source) {
		return findByCriteria(Restrictions.and(Restrictions.eq("language", language), Restrictions.eq("source", source)));
	}
	
	public List<Rule> findByUser(User user) {
		return findByCriteria(Restrictions.eq("user", user));
	}
	
	public List<Rule> findByUser(Languages language, User user) {
		return findByCriteria(Restrictions.and(Restrictions.eq("language", language), Restrictions.eq("user", user)));
	}
	
}
