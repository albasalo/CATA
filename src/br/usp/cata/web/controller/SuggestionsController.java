package br.usp.cata.web.controller;

import javax.servlet.ServletContext;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

import br.usp.cata.service.RuleService;
import br.usp.cata.util.Checker;
import br.usp.cata.util.FileProcessor;
import br.usp.cata.util.RulesTrees;
import br.usp.cata.util.TextAnalyzer;


@Resource
public class SuggestionsController {
	
	private final Result result;
	private final RuleService ruleService;
	private final ServletContext servletContext;

	public SuggestionsController(Result result, final RuleService ruleService,
			final ServletContext servletContext) {
		this.result = result;
		this.ruleService = ruleService;
		this.servletContext = servletContext;
	}

	@Post
	@Path("/suggestions/results")
	public void results(UploadedFile file) {
		RulesTrees rulesTrees = new RulesTrees(ruleService);
		rulesTrees.buildDefaultTrees();
		
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
