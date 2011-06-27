package br.usp.cata.controller;

import java.util.ArrayList;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

import br.usp.cata.model.AnalyzedLine;
import br.usp.cata.model.AnalyzedText;


@Resource
public class SuggestionsController {
	
	private final Result result;

	public SuggestionsController(Result result) {
		this.result = result;
	}

	//FIXME
	@Post
	@Path("/suggestions/results")
	public void results(UploadedFile file) {
		
		AnalyzedText text = new AnalyzedText(file.getFile());		
		ArrayList<AnalyzedLine> analyzedText = text.getAnalyzedText();
		
		result.include("numOfErrors", text.getErrorsFound());
		
		if(text.getErrorsFound() != 0) {
			result.include("output", "Algumas sugestões para melhorar o estilo " +
					"do texto enviado:");
			result.include("fileName", file.getFileName());
			result.include("text", analyzedText);
		}
		else {
			result.include("output", "Não há sugestões para o texto enviado.");
		}
	}

}
