package br.usp.cata.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.util.hibernate.SessionCreator;

import br.usp.cata.model.Source;


@Component
@RequestScoped
public class SourceDAO extends AbstractDAO<Long,Source> {

	public SourceDAO(SessionCreator sessionCreator) {
		super(sessionCreator);
	}
	
}
