package br.usp.correcao.component;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.usp.correcao.model.DictionaryTree;


@ApplicationScoped
@Component
public class Dictionary {
	
	private static DictionaryTree tree;
	
	public Dictionary() {
		tree = new DictionaryTree();
	}
	
	public static DictionaryTree getTree() {
		return tree;
	}
	
}
