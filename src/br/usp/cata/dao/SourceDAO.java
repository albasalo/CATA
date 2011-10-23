package br.usp.cata.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.util.hibernate.SessionCreator;

import br.usp.cata.model.Source;
import br.usp.cata.model.TypesOfSources;


@Component
@RequestScoped
public class SourceDAO extends AbstractDAO<Long,Source> {

	public SourceDAO(SessionCreator sessionCreator) {
		super(sessionCreator);
	}
	
	public Source findByID(Long sourceID) {
		List<Source> sources = findByCriteria(Restrictions.eq("sourceID", sourceID));		
		return (sources.isEmpty() ? null : sources.get(0));
	}
	
	public List<Source> findByType(TypesOfSources type) {
		return findByCriteria(Restrictions.eq("type", type));
	}
	
}
