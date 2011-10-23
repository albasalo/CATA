package br.usp.cata.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.util.hibernate.SessionCreator;

import br.usp.cata.model.Lemma;


@Component
@RequestScoped
public class LemmaDAO extends AbstractDAO<Long, Lemma> {

	public LemmaDAO(SessionCreator sessionCreator) {
		super(sessionCreator);
	}

	public void delete(Lemma lemma) {
		super.delete(lemma);
	}
	
}
