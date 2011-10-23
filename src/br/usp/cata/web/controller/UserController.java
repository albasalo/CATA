package br.usp.cata.web.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.usp.cata.model.CataConstraints;
import br.usp.cata.model.User;
import br.usp.cata.service.CryptoService;
import br.usp.cata.service.RuleService;
import br.usp.cata.service.UserService;
import br.usp.cata.web.interceptor.Transactional;


@Resource
public class UserController {
	
	private final Result result;
	private final Validator validator;
	private final RuleService ruleService;
	private final UserService userService;
	private final UserSession userSession;
	
	public UserController(Result result, Validator validator, RuleService ruleService,
			UserService userService, UserSession userSession) {
		this.result = result;
		this.validator = validator;
		this.ruleService = ruleService;
		this.userService = userService;
		this.userSession = userSession;
	}
	
	@Get
	@Path("/user/profile")
	public void profile() {
		result.include("rules", ruleService.findByUser(userSession.getUser()));
	}
	
	@Get
	@Path("/user/changepassword")
	public void changepassword() {
	}

	@Post
	@Path("/user/changepassword")
	@Transactional
	public void changepassword(String currentPassword, String password, String password2) {
		if(password.length() < CataConstraints.PASSWORD_MIN_LENGTH)
    		validator.add(new ValidationMessage(
    				"A senha deve ter, no mínimo, 6 caracteres.", "Nova senha"));
    	if(password.length() > CataConstraints.PASSWORD_MAX_LENGTH)
    		validator.add(new ValidationMessage(
    				"A senha deve ter, no máximo, 32 caracteres", "Nova senha"));
    	if(!password.equals(password2))
        	validator.add(new ValidationMessage(
        			"As senhas digitadas não são idênticas.", "Senhas"));
    	
    	String cryptoCurrent = CryptoService.generateMd5(currentPassword);
    	User user = userSession.getUser();
    	if(!user.getPassword().equals(cryptoCurrent))
    		validator.add(new ValidationMessage(
        			"Senha inválida.", "Senha atual"));
    	
    	validator.onErrorRedirectTo(UserController.class).changepassword();
    	
    	user.setPassword(CryptoService.generateMd5(password));
    	userService.update(user);
    	
    	result.include("messages", "Sua senha foi alterada com sucesso.");
    	result.redirectTo(UserController.class).profile();
	}
}
