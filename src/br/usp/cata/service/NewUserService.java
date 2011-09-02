package br.usp.cata.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

import br.usp.cata.dao.UserDAO;
import br.usp.cata.model.Email;
import br.usp.cata.model.EmailException;
import br.usp.cata.model.User;


@RequestScoped
@Component
public class NewUserService {
	
	public static enum SignupResult {
		SUCCESS,
		USER_ALREADY_REGISTERED_ACTIVE,
		USER_ALREADY_REGISTERED_INACTIVE,
		NO_EMAIL_SENT_INACTIVE,
		NO_EMAIL_SENT,
		ACTIVATION_KEY_NOT_FOUND
	}
    
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
	        linkBuilder.append("/activate/");
	        linkBuilder.append(newUser.getActivationKey());
	        linkBuilder.append("'>");
	        linkBuilder.append("aqui");
	        linkBuilder.append("</a>");

        final String subject = "CATA: Ativação de conta";
        final String body = "Obrigado por se cadastrar no Sistema CATA de verificação" +
        		" de estilo para textos acadêmicos. Clique " +
        		linkBuilder.toString()  + " para ativar sua conta.";

        return emailService.buildEmail(subject, body, newUser.getEmail());
    }

    public SignupResult register(final User newUser)  { 	
    	String email = newUser.getEmail();
    	
    	final User activeUser = userDAO.findByEmailAndStatus(email, true);
    	if(activeUser != null)
    		return SignupResult.USER_ALREADY_REGISTERED_ACTIVE;
    	
    	final User inactiveUser = userDAO.findByEmailAndStatus(email, false);
    	if(inactiveUser != null) {
    		try {
				emailService.sendEmail(buildNewUserEmail(inactiveUser));
			} catch (EmailException e) {
				return SignupResult.NO_EMAIL_SENT_INACTIVE;
			}
    		return SignupResult.USER_ALREADY_REGISTERED_INACTIVE;
    	}
    	
    	try {
    		newUser.setPassword(CryptoService.generateMd5(newUser.getPassword()));
            newUser.setRegistrationDate(new Date());
            newUser.setActivationKey();
            newUser.setActive(false);
            
            emailService.sendEmail(buildNewUserEmail(newUser));
        } catch(EmailException e ) {
            return SignupResult.NO_EMAIL_SENT;
        }
        
    	userDAO.save(newUser);
        
        return SignupResult.SUCCESS;
    }
    
    public SignupResult activate(String activationKey) {
    	final User userToBeActivated = userDAO.findByActivationKey(activationKey);
    	
    	if(userToBeActivated == null)
    		return SignupResult.ACTIVATION_KEY_NOT_FOUND;
    	else if(userToBeActivated.isActive())
    		return SignupResult.USER_ALREADY_REGISTERED_ACTIVE;
    	
    	userToBeActivated.setActive(true);
    	
    	return SignupResult.SUCCESS;
    }

}
