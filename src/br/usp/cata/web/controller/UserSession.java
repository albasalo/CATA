package br.usp.cata.web.controller;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.usp.cata.model.User;

@SessionScoped
@Component
public class UserSession implements Serializable {

    private static final long serialVersionUID = 7238150308131102510L;

    private User user;

    public Long getUserID() {
        return user.getUserID();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return user.getName();
    }

    public boolean isAuthenticatedUser() {
        return (user != null);
    }

}
