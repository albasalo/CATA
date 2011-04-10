package br.usp.correcao.controller;

import java.io.IOException;
import java.util.ArrayList;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

import br.usp.correcao.model.Line;
import br.usp.correcao.model.Text;


@Resource
public class SuggestionsController {
	
	private final Result result;

	public SuggestionsController(Result result) {
		this.result = result;
	}

	@Post
	@Path("/suggestions/results")
	public void results(UploadedFile file) {
		Text text = new Text(file.getFile());
		
		ArrayList<Line> analyzedText = null;
		try {
			analyzedText = text.analyzeText();
		} catch (IOException e) {
			//TODO
		}
		
		if(text.getErrorsFound() != 0) {
			result.include("output", "Algumas sugestões para melhorar o estilo " +
					"do texto enviado:");
			result.include("text", analyzedText);
		}
		else {
			result.include("output", "Não há sugestões para o texto enviado.");
		}
	}

}
