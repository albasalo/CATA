package br.usp.cata.service;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.usp.cata.dao.ExactMatchingDAO;
import br.usp.cata.model.ExactMatching;


@RequestScoped
@Component
public class ExactMatchingService {
	
	private final ExactMatchingDAO exactMatchingDAO;
	
	public ExactMatchingService(ExactMatchingDAO exactMatchingDAO) {
		this.exactMatchingDAO = exactMatchingDAO;
	}
	
	public void delete(ExactMatching exactMatching) {
		exactMatchingDAO.delete(exactMatching);
	}

}
