package br.usp.cata.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.util.hibernate.SessionCreator;
import br.usp.cata.model.Opinion;
import br.usp.cata.model.PatternSuggestionPair;


@Component
@RequestScoped
public class OpinionDAO extends AbstractDAO<Long, Opinion> {

	public OpinionDAO(SessionCreator sessionCreator) {
		super(sessionCreator);
	}
	
	public Opinion findById(long opinionID) {
		List<Opinion> opinions = findByCriteria(Restrictions.eq("opinionID", opinionID));		
		return (opinions.isEmpty() ? null : opinions.get(0));
	}

	public List<Opinion> findByPair(PatternSuggestionPair patternSuggestionPair) {
		return findByCriteria(Restrictions.eq("patternSuggestionPair", patternSuggestionPair));		
	}
	
}
