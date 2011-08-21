package br.usp.cata.web.controller;

import java.util.Date;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.usp.cata.model.Source;
import br.usp.cata.model.TypesOfSources;
import br.usp.cata.service.SourceService;

@Resource
public class RulesController {
	
	private final Result result;
	private final Validator validator;
	private final SourceService sourceService;
	private final UserSession userSession;
	
	public RulesController(final Result result, final Validator validator,
			final SourceService sourceService, final UserSession userSession) {
		this.result = result;
		this.validator = validator;
		this.sourceService = sourceService;
		this.userSession = userSession;
	}
	
	@Get
	@Path("/rules/newsource")
	public void newsource() {
		result.include("typesOfSources", TypesOfSources.values());
	}
	
	public void validate(Source source) {
		switch(source.getType()) {
			case ACADEMIC_PUBLISHING:
				if(source.getTitle().equals(""))
		    		validator.add(new ValidationMessage(
		    				"O campo não pode ser vazio", "Título"));
				if(source.getAuthors().equals(""))
		    		validator.add(new ValidationMessage(
		    				"O campo não pode ser vazio", "Autor(es)"));
				if((!source.getPublisher().equals("")) || (!source.getUrl().equals("")))
					validator.add(new ValidationMessage("Erro inesperado", "Erro"));
				break;
			case BOOK:
			case HANDBOOK:
				if(source.getTitle().equals(""))
		    		validator.add(new ValidationMessage(
		    				"O campo não pode ser vazio", "Título"));
				if(source.getAuthors().equals(""))
		    		validator.add(new ValidationMessage(
		    				"O campo não pode ser vazio", "Autor(es)"));
				if((!source.getInstitution().equals("")) || (!source.getUrl().equals("")))
					validator.add(new ValidationMessage("Erro inesperado", "Erro"));
				break;
			case INTERNET:
				if(source.getTitle().equals(""))
		    		validator.add(new ValidationMessage(
		    				"O campo não pode ser vazio", "Título"));
				if(source.getUrl().equals(""))
		    		validator.add(new ValidationMessage(
		    				"O campo não pode ser vazio", "URL"));	
				if(source.getDate().equals(""))
		    		validator.add(new ValidationMessage(
		    				"O campo não pode ser vazio", "Data de acesso"));
				if(!(source.getAuthors().equals("")) || !(source.getInstitution().equals("")) ||
						!(source.getPublisher().equals("")))
					validator.add(new ValidationMessage("Erro inesperado", "Erro"));
				break;
			case OTHER:
				if(source.getMoreInformation().equals(""))
		    		validator.add(new ValidationMessage(
		    				"O campo não pode ser vazio", "Mais informações"));
				if((!source.getAuthors().equals("")) || (!source.getInstitution().equals("")) ||
						(!source.getPublisher().equals("")) || (!source.getTitle().equals("")) ||
						(!source.getUrl().equals("")))
					validator.add(new ValidationMessage("Erro inesperado", "Erro"));
				break;
			default:
				break;
		}
	}
	
	@Post
	@Path("/rules/newsource")
	public void newsource(Source newSource) {
		validate(newSource);
		result.include("selectedType", newSource.getType());
		validator.onErrorRedirectTo(RulesController.class).newsource();
		
		newSource.setUser(userSession.getUser());
		newSource.setRegistrationDate(new Date());
		sourceService.save(newSource);
		
		result.include("messages", "A Referência foi cadastrada com sucesso");

		// TODO redirecionar para uma pagina de regras
		result.redirectTo(HomeController.class).index();
	}

}