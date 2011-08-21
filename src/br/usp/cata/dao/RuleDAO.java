package br.usp.cata.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.util.hibernate.SessionCreator;

import br.usp.cata.model.Rule;


@Component
@RequestScoped
public class RuleDAO extends AbstractDAO<Long, Rule>{

	public RuleDAO(SessionCreator sessionCreator) {
		super(sessionCreator);
	}

	public List<Rule> findDefault() {
		return findByCriteria(Restrictions.eq("defaultRule", true));
	}
	
}
