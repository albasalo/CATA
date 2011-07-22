package br.usp.cata.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.usp.cata.dao.UserDAO;
import br.usp.cata.model.Email;
import br.usp.cata.model.User;

@RequestScoped
@Component
public class NewUserService {
    
    private final UserDAO userDAO;
    private final EmailService emailService;
    private final HttpServletRequest httpServletRequest;
    
    public NewUserService(UserDAO userDAO, EmailService emailService,
    		HttpServletRequest httpServletRequest) {
    	this.userDAO = userDAO;
    	this.emailService = emailService;
    	this.httpServletRequest = httpServletRequest;
    }
    
    private String getActivationUrl() {
        return ((httpServletRequest == null)
        		? "http://localhost:8080/cata/signup"
        				: httpServletRequest.getRequestURL().toString());
    }
    
    private Email buildNewUserEmail(final User newUser) {
        
    	final StringBuilder linkBuilder = new StringBuilder();
	        linkBuilder.append("<a href='");
	        linkBuilder.append(getActivationUrl());
	        linkBuilder.append("/ativacao/");
	        linkBuilder.append(newUser.getActivationKey());
	        linkBuilder.append("'>");
	        linkBuilder.append("clique_aqui");
	        linkBuilder.append("</a>");

        final String subject = "Ativação CATA";
        final String body = "Use o link para ativar" + linkBuilder.toString();

        return emailService.buildEmail(subject, body, newUser.getEmail());
    }

    public void register(final User newUser)  {
    	
        newUser.setActive(false);
        newUser.setRegistrationDate(new Date());
        newUser.setActivationKey();
        
        userDAO.save(newUser);

        emailService.sendEmail(buildNewUserEmail(newUser));
    }

}
