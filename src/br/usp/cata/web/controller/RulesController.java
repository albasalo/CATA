package br.usp.cata.web.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.usp.cata.model.ExactMatching;
import br.usp.cata.model.Lemma;
import br.usp.cata.model.PatternSuggestionPair;
import br.usp.cata.model.Rule;
import br.usp.cata.model.RuleCategories;
import br.usp.cata.model.Source;
import br.usp.cata.model.TypesOfRules;
import br.usp.cata.model.TypesOfSources;
import br.usp.cata.service.ExactMatchingService;
import br.usp.cata.service.LemmaService;
import br.usp.cata.service.RuleService;
import br.usp.cata.service.SourceService;
import br.usp.cata.web.interceptor.Transactional;


@Resource
public class RulesController {
	
	private final Result result;
	private final Validator validator;
	private final RuleService ruleService;
	private final SourceService sourceService;
	private final UserSession userSession;
	private final LemmaService lemmaService;
	private final ExactMatchingService exactMatchingService;
	
	public RulesController(final Result result, final Validator validator,
		final RuleService ruleService, final SourceService sourceService, final UserSession userSession,
		final LemmaService lemmaService, ExactMatchingService exactMatchingService) {
		this.result = result;
		this.validator = validator;
		this.ruleService = ruleService;
		this.sourceService = sourceService;
		this.userSession = userSession;
		this.lemmaService = lemmaService;
		this.exactMatchingService = exactMatchingService;
	}
	
	public void includeInformation() {
		result.include("ruleCategories", RuleCategories.values());
		result.include("typesOfRules", TypesOfRules.values());
		result.include("typesOfSources", TypesOfSources.values());
		result.include("academics", sourceService.findByType(TypesOfSources.ACADEMIC_PUBLISHING));
		result.include("books", sourceService.findByType(TypesOfSources.BOOK));
		result.include("handbooks", sourceService.findByType(TypesOfSources.HANDBOOK));
		result.include("urls", sourceService.findByType(TypesOfSources.INTERNET));
		result.include("others", sourceService.findByType(TypesOfSources.OTHER));
	}
	
	@Get
	@Path("/rules/newrule")
	public void newrule() {
		includeInformation();
	}
	
	private void validateRule(Rule newRule, List<PatternSuggestionPair> lemmas,
			List<PatternSuggestionPair> exactMatchings, Source source) {
		
		if(lemmas == null && exactMatchings == null) {
			validator.add(new ValidationMessage(
    				"Você deve cadastrar pelo menos um Lema ou Expressão Exata.", "Lema ou Expressão Exata"));
		}
		else {
			if(lemmas != null) {
				for(PatternSuggestionPair pair : lemmas) {
					if(pair.getPattern().equals("") ||
							pair.getSuggestions().equals("")) {
						validator.add(new ValidationMessage(
			    				"Você deve cadastrar um padrão e sugestões para cada Lema.", "Lema"));
						break;
					}
				}
			}
			if(exactMatchings != null) {
				for(PatternSuggestionPair pair : exactMatchings) {
					if(pair.getPattern().equals("") ||
							pair.getSuggestions().equals("")) {
						validator.add(new ValidationMessage(
			    				"Você deve cadastrar um padrão e sugestões para cada Expressão Exata.", "Expressão Exata"));
						break;
					}
				}
			}
		}
		
		if(source.getSourceID() == null)
			validator.add(new ValidationMessage(
    				"Você deve associar uma referência à regra.", "Referência"));
		
	}
	
	@Post
	@Path("rules/newrule")
	public void newrule(Rule newRule, List<PatternSuggestionPair> lemmas,
			List<PatternSuggestionPair> exactMatchings, Source source) {
		
		validateRule(newRule, lemmas, exactMatchings, source);
		Source dbSource = sourceService.findByID(source.getSourceID());
		if(dbSource == null)
			validator.add(new ValidationMessage(
    				"Referência Bibliográfica inválida.", "Referência"));
		validator.onErrorRedirectTo(RulesController.class).newrule();
		
		if(newRule.getExplanation().equals(""))
			newRule.setExplanation(null);
		
		if(lemmas != null) {
			newRule.setLemmas(new HashSet<Lemma>());
			for(PatternSuggestionPair pair : lemmas) {
				pair.setDefaultPair(false);
				Lemma lemma = new Lemma();
				lemma.setRule(newRule);
				lemma.setPair(pair);
				newRule.getLemmas().add(lemma);
			}
			lemmas.get(0).setDefaultPair(true);
		}
		if(exactMatchings != null) {
			newRule.setExactMatchings(new HashSet<ExactMatching>());
			for(PatternSuggestionPair pair : exactMatchings) {
				pair.setDefaultPair(false);
				ExactMatching exactMatching = new ExactMatching();
				exactMatching.setRule(newRule);
				exactMatching.setPair(pair);
				newRule.getExactMatchings().add(exactMatching);
			}
			exactMatchings.get(0).setDefaultPair(true);
		}
		
		newRule.setUser(userSession.getUser());
		newRule.setDate(new Date());
		//TODO Mudar para false: as novas regras não devem ser default do sistema
		newRule.setDefaultRule(true);
		
		newRule.setSource(dbSource);
		
		ruleService.save(newRule);
		
		result.include("messages", "A Regra foi cadastrada com sucesso.");
		
		result.redirectTo(IndexController.class).rules();
	}
	
	@Get
	@Path("/rules/newsource")
	public void newsource() {
		result.include("typesOfSources", TypesOfSources.values());
	}
	
	public void validateSource(Source source) {
		switch(source.getType()) {
			case ACADEMIC_PUBLISHING:
				if(source.getTitle().equals(""))
		    		validator.add(new ValidationMessage(
		    				"O campo não pode ser vazio.", "Título"));
				if(source.getAuthors().equals(""))
		    		validator.add(new ValidationMessage(
		    				"O campo não pode ser vazio.", "Autor(es)"));
				
				if((!source.getPublisher().equals("")) || (!source.getUrl().equals("")))
					validator.add(new ValidationMessage("Erro inesperado.", "Erro"));
				else {
					source.setPublisher(null);
					source.setUrl(null);
				}
				
				if(source.getInstitution().equals(""))
					source.setInstitution(null);				
				if(source.getDate().equals(""))
					source.setDate(null);			
				if(source.getMoreInformation().equals(""))
					source.setMoreInformation(null);
				
				break;
				
			case BOOK:
			case HANDBOOK:
				if(source.getTitle().equals(""))
		    		validator.add(new ValidationMessage(
		    				"O campo não pode ser vazio.", "Título"));
				if(source.getAuthors().equals(""))
		    		validator.add(new ValidationMessage(
		    				"O campo não pode ser vazio.", "Autor(es)"));
				
				if((!source.getInstitution().equals("")) || (!source.getUrl().equals("")))
					validator.add(new ValidationMessage("Erro inesperado", "Erro"));
				else {
					source.setInstitution(null);
					source.setUrl(null);
				}
				
				if(source.getPublisher().equals(""))
					source.setPublisher(null);				
				if(source.getDate().equals(""))
					source.setDate(null);				
				if(source.getMoreInformation().equals(""))
					source.setMoreInformation(null);
				
				break;
				
			case INTERNET:
				if(source.getTitle().equals(""))
		    		validator.add(new ValidationMessage(
		    				"O campo não pode ser vazio.", "Título"));
				if(source.getUrl().equals(""))
		    		validator.add(new ValidationMessage(
		    				"O campo não pode ser vazio.", "URL"));
				
				if(!(source.getAuthors().equals("")) || !(source.getInstitution().equals("")) ||
						!(source.getPublisher().equals("") || !(source.getDate().equals(""))))
					validator.add(new ValidationMessage("Erro inesperado.", "Erro"));
				else {
					source.setAuthors(null);
					source.setInstitution(null);
					source.setPublisher(null);
					source.setDate(null);
				}
				
				if(source.getMoreInformation().equals(""))
					source.setMoreInformation(null);
				
				break;
				
			case OTHER:
				if(source.getMoreInformation().equals(""))
		    		validator.add(new ValidationMessage(
		    				"O campo não pode ser vazio.", "Mais informações"));
				
				if((!source.getAuthors().equals("")) || (!source.getInstitution().equals("")) ||
						(!source.getPublisher().equals("")) || (!source.getTitle().equals("")) ||
						(!source.getUrl().equals("")) || (!source.getDate().equals("")))
					validator.add(new ValidationMessage("Erro inesperado.", "Erro"));
				else {
					source.setAuthors(null);
					source.setInstitution(null);
					source.setPublisher(null);
					source.setTitle(null);
					source.setUrl(null);
					source.setDate(null);
				}
				break;
				
			default:
				break;
		}
	}
	
	@Post
	@Path("/rules/newsource")
	@Transactional
	public void newsource(Source newSource) {
		validateSource(newSource);
		result.include("selectedType", newSource.getType());
		validator.onErrorRedirectTo(RulesController.class).newsource();
		
		newSource.setUser(userSession.getUser());
		newSource.setRegistrationDate(new Date());
		
		sourceService.save(newSource);
		
		result.include("messages", "A Referência foi cadastrada com sucesso.");

		result.redirectTo(RulesController.class).newrule();
	}
	
	@Get
	@Post
	@Path("rules/editrule")
	@Transactional
	public void editrule(Rule ruleToBeUpdated) {
		if(ruleToBeUpdated == null)
			result.redirectTo(UserController.class).profile();
		
		Rule rule = ruleService.findByID(ruleToBeUpdated.getRuleID());
		
		if(!rule.getUser().getUserID().equals(userSession.getUserID()))
			validator.add(new ValidationMessage(
    				"Você não possui autorização.", "Erro"));
		validator.onErrorRedirectTo(UserController.class).profile();
		
		includeInformation();
		result.include("rule", rule);
	}

	@Post
	@Path("rules/updaterule")
	@Transactional
	public void updaterule(Rule updatedRule, List<PatternSuggestionPair> lemmas,
			List<PatternSuggestionPair> exactMatchings, Source source) {
		
		Rule rule = ruleService.findByID(updatedRule.getRuleID());		
		if(!rule.getUser().getUserID().equals(userSession.getUserID())) {
			validator.add(new ValidationMessage(
    				"Você não possui autorização.", "Erro"));
		}
		validator.onErrorRedirectTo(UserController.class).profile();
		
		Source ruleSource;
		if(source != null && source.getSourceID() != null)
			ruleSource = sourceService.findByID(source.getSourceID());
		else {
			ruleSource = rule.getSource();
			source.setSourceID(ruleSource.getSourceID());
		}
		validateRule(updatedRule, lemmas, exactMatchings, source);
		if(ruleSource == null) {
			validator.add(new ValidationMessage(
    				"Referência Bibliográfica inválida.", "Referência"));
		}
		validator.onErrorRedirectTo(RulesController.class).editrule(rule);
		
		for(Lemma lemma : rule.getLemmas())
			lemmaService.delete(lemma);
		for(ExactMatching exactMatching : rule.getExactMatchings())
			exactMatchingService.delete(exactMatching);
				
		rule.setCategory(updatedRule.getCategory());
		rule.setType(updatedRule.getType());
		if(updatedRule.getExplanation().equals(""))
			rule.setExplanation(null);
		else
			rule.setExplanation(updatedRule.getExplanation());
		
		rule.setLemmas(new HashSet<Lemma>());
		rule.setExactMatchings(new HashSet<ExactMatching>());
		ruleService.update(rule);
		
		if(lemmas != null) {
			for(PatternSuggestionPair pair : lemmas) {
				pair.setDefaultPair(false);
				Lemma lemma = new Lemma();
				lemma.setRule(rule);
				lemma.setPair(pair);
				rule.getLemmas().add(lemma);
			}
			lemmas.get(0).setDefaultPair(true);
		}
		if(exactMatchings != null) {
			for(PatternSuggestionPair pair : exactMatchings) {
				pair.setDefaultPair(false);
				ExactMatching exactMatching = new ExactMatching();
				exactMatching.setRule(rule);
				exactMatching.setPair(pair);
				rule.getExactMatchings().add(exactMatching);
			}
			exactMatchings.get(0).setDefaultPair(true);
		}
		
		ruleService.update(rule);
		
		result.include("messages", "A Regra foi editada com sucesso.");
		result.redirectTo(UserController.class).profile();
	}
	
	@Post
	@Path("rules/deleterule")
	@Transactional
	public void deleterule(Rule ruleToBeDeleted) {
		Rule rule = ruleService.findByID(ruleToBeDeleted.getRuleID());		
		if(!rule.getUser().getUserID().equals(userSession.getUserID())) {
			validator.add(new ValidationMessage(
    				"Você não possui autorização.", "Erro"));
		}
		validator.onErrorRedirectTo(UserController.class).profile();
		
		ruleService.delete(rule);
		result.include("messages", "A Regra foi removida com sucesso.");
		result.redirectTo(UserController.class).profile();
	}
	
}
