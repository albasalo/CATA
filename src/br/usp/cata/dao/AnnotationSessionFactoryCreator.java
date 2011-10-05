package br.usp.cata.dao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import br.com.caelum.vraptor.ioc.RequestScoped;

import br.usp.cata.model.PatternSuggestionElement;
import br.usp.cata.model.Rule;
import br.usp.cata.model.Source;
import br.usp.cata.model.User;


@SuppressWarnings("deprecation")
@RequestScoped
@Component
public class AnnotationSessionFactoryCreator implements ComponentFactory<SessionFactory> {
    
	private SessionFactory factory;

    public AnnotationSessionFactoryCreator() {
    }

    @PostConstruct
    public void create() {
    	// FIXME: deprecation
		final AnnotationConfiguration cfg = new AnnotationConfiguration();
		cfg.addAnnotatedClass(User.class);
		cfg.addAnnotatedClass(PatternSuggestionElement.class);
        cfg.addAnnotatedClass(Rule.class);
        cfg.addAnnotatedClass(Source.class);
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
