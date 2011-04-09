package br.usp.correcao.controller;

import java.io.IOException;
import java.util.ArrayList;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

import br.usp.correcao.model.ErrorOccurrence;
import br.usp.correcao.util.TextAnalyzer;


@Resource
public class SuggestionsController {
	
	private final Result result;

	public SuggestionsController(Result result) {
		this.result = result;
	}

	@Post
	@Path("/suggestions/results")
	public void results(UploadedFile file) {
		TextAnalyzer analyzer = new TextAnalyzer(file.getFile());
		
		ArrayList<ErrorOccurrence> suggestions = null;
		try {
			suggestions = analyzer.getSuggestions();
		} catch (IOException e) {
			//TODO
		}
		
		if(!suggestions.isEmpty()) {
			result.include("output", "Algumas sugestões para melhorar o estilo " +
					"do texto enviado:");
			result.include("suggestions", suggestions);
		}
		else {
			result.include("output", "Não há sugestões para o texto enviado.");
		}
	}

}
