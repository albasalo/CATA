package br.usp.cata.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.util.hibernate.SessionCreator;

import br.usp.cata.model.ExactMatching;


@Component
@RequestScoped
public class ExactMatchingDAO extends AbstractDAO<Long, ExactMatching> {

	public ExactMatchingDAO(SessionCreator sessionCreator) {
		super(sessionCreator);
	}

	public void delete(ExactMatching exactMatching) {
		super.delete(exactMatching);
	}
	
}
