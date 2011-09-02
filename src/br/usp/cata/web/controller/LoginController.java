package br.usp.cata.web.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import br.usp.cata.service.UserService;


@Resource
public class LoginController {
	
	private final Result result;
	private final UserService userService;
	
	public LoginController(final Result result, final UserService userService) {
		this.result = result;
		this.userService = userService;
	}

	@Get
	@Path("/logout")
	public void logout() {
		userService.deauthenticate();
		
		result.redirectTo(IndexController.class).index();
	}
	
}
