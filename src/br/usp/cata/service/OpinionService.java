package br.usp.cata.service;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

import br.usp.cata.dao.OpinionDAO;
import br.usp.cata.model.Opinion;
import br.usp.cata.model.PatternSuggestionPair;


@RequestScoped
@Component
public class OpinionService {

	private final OpinionDAO opinionDAO;
	
	public OpinionService(OpinionDAO opinionDAO) {
		this.opinionDAO = opinionDAO;
	}
	
	public void save(Opinion opinion) {
		opinionDAO.save(opinion);
	}
	
	public void saveOrUpdate(Opinion opinion) {
		opinionDAO.saveOrUpdate(opinion);
	}
	
	public Opinion findById(long opinionID) {
		return opinionDAO.findById(opinionID);
	}
	
	public List<Opinion> findByPair(PatternSuggestionPair patternSuggestionPair) {
		return opinionDAO.findByPair(patternSuggestionPair);
	}
	
}
