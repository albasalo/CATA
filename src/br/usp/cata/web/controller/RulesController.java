package br.usp.cata.web.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.usp.cata.model.TypesOfSources;

@Resource
public class RulesController {
	
	private final Result result;
	
	public RulesController(final Result result) {
		this.result = result;
	}
	
	@Get
	@Path("/rules/newsource")
	public void newsource() {
		result.include("typesOfSources", TypesOfSources.values());
	}

}
