package br.usp.cata.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.usp.cata.dao.UserDAO;
import br.usp.cata.model.Email;
import br.usp.cata.model.EmailException;
import br.usp.cata.model.User;
import br.usp.cata.service.EmailService.EmailResult;
import br.usp.cata.web.controller.UserSession;


@RequestScoped
@Component
public class UserService {
	
    private final UserDAO userDAO;
    private final UserSession userSession;
    private final EmailService emailService;
    private final HttpServletRequest httpServletRequest;

    public UserService(UserDAO userDAO, UserSession userSession, EmailService emailService,
    		HttpServletRequest httpServletRequest) {
        this.userDAO = userDAO;
        this.userSession = userSession;
        this.emailService = emailService;
        this.httpServletRequest = httpServletRequest;
    }

    public boolean isAuthenticatedUser() {
        return userSession.isAuthenticatedUser();
    }

    public void save(final User user) {
        final String password = CryptoService.generateMd5(user.getPassword());

        user.setPassword(password);
        userDAO.save(user);
    }
    
    public void update(final User user) {
    	userDAO.saveOrUpdate(user);
    }
    
    public List<User> findAll() {
    	return userDAO.findAll();
    }

    public User findByEmailAndStatus(String email, boolean active) {
        return userDAO.findByEmailAndStatus(email, active);
    }
    
    public User findByEmail(String email) {
    	return userDAO.findByEmail(email);
    }
    
    public User findByNewPasswordKey(String newPasswordKey) {
    	return userDAO.findByNewPasswordKey(newPasswordKey);
    }

    public boolean authenticate(final String email, String password) {
        String encryptedPassword = CryptoService.generateMd5(password);
        
        final boolean success;
        
        final User user = userDAO.findActiveUsersByEmailAndPassword(email, encryptedPassword);
        
        if(user == null)
            success = false;
        else {
            userSession.setUser(user);
            success = true;
        }
        
        return success;
    }

    public void deauthenticate() {
        userSession.setUser(null);
    }
    
    private String getRecoveryUrl() {
        return ((httpServletRequest == null)
        		? "http://localhost:8080/cata/recover"
        				: httpServletRequest.getRequestURL().toString());
    }
    
    private Email buildNewPasswordEmail(final String email, final String key) {        
    	final StringBuilder linkBuilder = new StringBuilder();
	        linkBuilder.append("<a href='");
	        linkBuilder.append(getRecoveryUrl());
	        linkBuilder.append("/newpassword/");
	        linkBuilder.append(key);
	        linkBuilder.append("'>");
	        linkBuilder.append("aqui");
	        linkBuilder.append("</a>");

        final String subject = "CATA: Nova senha";
        final String body = "Clique " + linkBuilder.toString()  + " para definir uma nova senha " +
        		"de acesso ao Sistema CATA.";

        return emailService.buildEmail(subject, body, email);
    }

    public EmailResult sendRecoverPasswordEmail(final User user)  { 	
		try {
			emailService.sendEmail(buildNewPasswordEmail(user.getEmail(), user.getNewPasswordKey()));
		} catch (EmailException e) {
			return EmailResult.NO_EMAIL_SENT;
		}
        
        return EmailResult.EMAIL_SENT;
    }
    
    public EmailResult recoverPassword(User user) {
    	user.setNewPasswordKey(CryptoService.generateMd5(user.getPassword() + (new Date()).toString()));
    	return sendRecoverPasswordEmail(user);
    }
    
}
