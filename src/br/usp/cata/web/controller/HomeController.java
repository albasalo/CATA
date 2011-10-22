package br.usp.cata.web.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;


@Resource
public class HomeController {
	
	public HomeController() {
	}
	
	@Get
	@Path("/home")
	public void index() {
	}
	
}
