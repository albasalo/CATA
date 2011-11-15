package br.usp.cata.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.util.hibernate.SessionCreator;
import br.usp.cata.model.PatternSuggestionPair;


@Component
@RequestScoped
public class PatternSuggestionPairDAO extends AbstractDAO<Long, PatternSuggestionPair>{

	public PatternSuggestionPairDAO(SessionCreator sessionCreator) {
		super(sessionCreator);
	}
	
	public PatternSuggestionPair findByID(Long patternSuggestionPairID) {
		List<PatternSuggestionPair> pairs = findByCriteria(Restrictions.eq("patternSuggestionPairID", patternSuggestionPairID));
		return (pairs.isEmpty() ? null : pairs.get(0));
	}

}
