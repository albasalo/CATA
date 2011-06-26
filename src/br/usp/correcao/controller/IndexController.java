/*
 * Modificado de IndexController.java -
 * 	http://vraptor3.googlecode.com/files/vraptor-blank-project-3.3.1.zip
 */
package br.usp.correcao.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.validator.ValidationMessage;


@Resource
public class IndexController {

	private final Result result;
	private Validator validator;

	public IndexController(Result result, Validator validator) {
		this.result = result;
		this.validator = validator;
	}

	@Get
	@Path("/")
	public void index() {
	}
	
	@Get
	@Path("/about")
	public void about() {
	}
	
	@Get
	@Path("/advice")
	public void advice() {
	}

	@Post
	@Path("/advice")
	public void advice(UploadedFile file) {
		//FIXME Colocar as mensagens no messages.properties.
		if(file == null)
			validator.add(new ValidationMessage("", "error"));
		else if(!file.getContentType().equals("text/plain")) {
			validator.add(new ValidationMessage(
					"O arquivo deve estar no formato .txt", "error"));
		}
		validator.onErrorUsePageOf(IndexController.class).advice();
		
		//result.redirectTo(SuggestionsController.class).results(file);
		result.forwardTo(SuggestionsController.class).results(file);
	}
}
