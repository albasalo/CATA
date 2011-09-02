package br.usp.cata.service;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

import br.usp.cata.dao.SourceDAO;
import br.usp.cata.model.Source;
import br.usp.cata.model.TypesOfSources;


@RequestScoped
@Component
public class SourceService {
	
	private final SourceDAO sourceDAO;
	
	public SourceService(final SourceDAO sourceDAO) {
		this.sourceDAO = sourceDAO;
	}
	
	public void save(Source source) {
		if(source.getSourceID() == null)
			sourceDAO.save(source);
		else
			sourceDAO.saveOrUpdate(source);
	}
	
	public List<Source> findAll() {
		return sourceDAO.findAll();
	}

	public Source findByID(Long sourceID) {
		return sourceDAO.findByID(sourceID);
	}
	
	public List<Source> findByType(TypesOfSources type) {
		return sourceDAO.findByType(type);
	}
}
