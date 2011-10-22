package br.usp.cata.web.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.usp.cata.service.RuleService;


@Resource
public class UserController {
	
	private final Result result;
	private final RuleService ruleService;
	private final UserSession userSession;
	
	public UserController(Result result, RuleService ruleService, UserSession userSession) {
		this.result = result;
		this.ruleService = ruleService;
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

}
