/*
 * Modificado de IndexController.java -
 * 	http://vraptor3.googlecode.com/files/vraptor-blank-project-3.3.1.zip
 */
package br.usp.cata.web.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.usp.cata.model.User;
import br.usp.cata.service.NewUserService;
import br.usp.cata.service.UserService;
import br.usp.cata.web.interceptors.IrrestrictAccess;
import br.usp.cata.web.interceptors.Transactional;


@Resource
@IrrestrictAccess
public class IndexController {

	private final Result result;
	private Validator validator;
	private UserService userService;
	private NewUserService newUserService;

	public IndexController(Result result, Validator validator,
			UserService userService, NewUserService newUserService) {
		this.result = result;
		this.validator = validator;
		this.userService = userService;
		this.newUserService = newUserService;
	}

	@Get
	@Path("/")
	public void index() {
	}
	
	@Get
	@Path("/about")
	public void about() {
	}
	
    @Post
    @Path("/login")
    @Transactional
    public void login(User user) {

        final boolean success = userService.authenticate(user.getEmail(), user.getPassword());

        if(success)
            result.redirectTo(IndexController.class).index();
        else {
        	validator.add(new ValidationMessage("Login ou senha inválidos", "error"));
            validator.onErrorRedirectTo(getClass()).index();
        }
    }
    
    @Get
    @Path("/signup")
    public void signup()
    {   	
    }
    
    @Post
    @Path("/signup")
    @Transactional
    public void signup(User newUser)
    {
    	newUserService.register(newUser);
    	result.forwardTo(IndexController.class).index();
    }
	
	@Get
	@Path("/advice")
	public void advice() {
	}

	@Post
	@Path("/advice")
	public void advice(UploadedFile file) {
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
