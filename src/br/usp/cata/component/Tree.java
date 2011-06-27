package br.usp.cata.component;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

import br.usp.cata.model.RulesTree;


@ApplicationScoped
@Component
public class Tree {
	
	private static RulesTree tree;
	
	public Tree() {
		tree = new RulesTree();
	}
	
	public static RulesTree getTree() {
		return tree;
	}
	
}
