package br.usp.cata.web.controller;

import javax.servlet.ServletContext;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.usp.cata.model.AdviceFilter;
import br.usp.cata.model.Languages;
import br.usp.cata.service.RuleService;
import br.usp.cata.service.SourceService;
import br.usp.cata.service.UserService;
import br.usp.cata.util.Checker;
import br.usp.cata.util.FileProcessor;
import br.usp.cata.util.RulesTrees;
import br.usp.cata.util.TextAnalyzer;


@Resource
public class SuggestionsController {
	
	private final Result result;
	private final RuleService ruleService;
	private final SourceService sourceService;
	private final UserService userService;
	private final ServletContext servletContext;

	public SuggestionsController(Result result, RuleService ruleService, SourceService sourceService, 
			UserService userService, ServletContext servletContext) {
		this.result = result;
		this.ruleService = ruleService;
		this.sourceService = sourceService;
		this.userService = userService;
		this.servletContext = servletContext;
	}

	@Post
	@Path("/suggestions/results")
	public void results(UploadedFile file, Languages language, AdviceFilter adviceFilter, long[] filterIDs) {
		RulesTrees rulesTrees = new RulesTrees(ruleService, sourceService, userService);
		//FIXME Always rebuild trees
		switch(adviceFilter) {
			case DEFAULT: 
				rulesTrees.buildDefaultTrees(language);
				break;
			case ALL:
				rulesTrees.buildAllTrees(language);
				break;
			case FILTERED_BY_USER:
				rulesTrees.buildUsersTrees(language, filterIDs);
				break;
			case FILTERED_BY_SOURCE:
				rulesTrees.buildSourcesTrees(language, filterIDs);
				break;
			default:
				break;
		}
		
		FileProcessor fileProcessor = new FileProcessor(file);		
		TextAnalyzer textAnalyzer = new TextAnalyzer(fileProcessor.getText(), servletContext);
		
		Checker checker = new Checker(textAnalyzer, rulesTrees);
		result.include("numOfMistakes", checker.getNumOfMistakes());
		
		if(checker.getNumOfMistakes() != 0) {
			result.include("output", "Algumas sugestões para melhorar o estilo " +
					"do texto enviado:");
			result.include("fileName", fileProcessor.getFileName());
			result.include("text", checker.getCheckedText());
		}
		else
			result.include("output", "Não há sugestões para o texto enviado.");
	}

}
