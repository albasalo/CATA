package br.usp.cata.web.controller;

import java.util.Date;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.usp.cata.model.Source;
import br.usp.cata.model.TypesOfSources;
import br.usp.cata.service.SourceService;

@Resource
public class RulesController {
	
	private final Result result;
	private final SourceService sourceService;
	private final UserSession userSession;
	
	public RulesController(final Result result, final SourceService sourceService,
			final UserSession userSession) {
		this.result = result;
		this.sourceService = sourceService;
		this.userSession = userSession;
	}
	
	@Get
	@Path("/rules/newsource")
	public void newsource() {
		result.include("typesOfSources", TypesOfSources.values());
	}
	
	@Post
	@Path("/rules/newsource")
	public void newsource(Source newSource) {
		// TODO validacao
		newSource.setUser(userSession.getUser());
		newSource.setRegistrationDate(new Date());
		
		sourceService.save(newSource);
		
		result.include("messages", "A Referência foi cadastrada com sucesso");
		
		// TODO redirecionar para uma pagina de regras
		result.redirectTo(HomeController.class).index();
	}

}
