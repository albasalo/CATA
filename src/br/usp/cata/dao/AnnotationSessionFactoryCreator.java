package br.usp.cata.dao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.usp.cata.model.User;


@RequestScoped
@Component
public class AnnotationSessionFactoryCreator implements ComponentFactory<SessionFactory> {
    
	private SessionFactory factory;

    public AnnotationSessionFactoryCreator()
    {
    }

    @PostConstruct
    public void create() {
        final AnnotationConfiguration cfg = new AnnotationConfiguration();
        cfg.addAnnotatedClass(User.class);
        factory = cfg.buildSessionFactory();
    }

    public SessionFactory getInstance() {
        return factory;
    }

    @PreDestroy
    public void destroy() {
        factory.close();
    }
    
}
