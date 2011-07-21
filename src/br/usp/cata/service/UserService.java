package br.usp.cata.service;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.usp.cata.dao.UserDAO;
import br.usp.cata.model.User;
import br.usp.cata.web.controller.UserSession;

@RequestScoped
@Component
public class UserService {
	
    private final UserDAO userDAO;
    private final UserSession userSession;

    public UserService(UserDAO userDAO, UserSession userSession) {
        this.userDAO = userDAO;
        this.userSession = userSession;
    }

    public boolean isAuthenticatedUser() {
        return userSession.isAuthenticatedUser();
    }

    public void save(final User user) {
        final String password = CryptoService.md5(user.getPassword());

        user.setPassword(password);
        userDAO.save(user);
    }
    
    public List<User> findAll() {
    	return userDAO.findAll();
    }

    public User findByEmailAndStatus(String email, boolean active) {
        return userDAO.findByEmailAndStatus(email, active);
    }

    public boolean authenticate(final String email, String password) {
        String encryptedPassword = CryptoService.md5(password);

        final User user = userDAO.findActiveUsersByEmailAndPassword(email, encryptedPassword);

        final boolean success;
        if(user == null)
            success = false;
        else {
            userSession.setUser(user);
            success = true;
        }
        return success;
    }

    public void deauthenticate() {
        userSession.setUser( null );
    }
}
