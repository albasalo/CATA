package br.usp.cata.service;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.usp.cata.dao.LemmaDAO;
import br.usp.cata.model.Lemma;


@RequestScoped
@Component
public class LemmaService {
	
	private final LemmaDAO lemmaDAO;
	
	public LemmaService(final LemmaDAO lemmaDAO) {
		this.lemmaDAO = lemmaDAO;
	}
	
	public void delete(Lemma lemma) {
		lemmaDAO.delete(lemma);
	}

}
