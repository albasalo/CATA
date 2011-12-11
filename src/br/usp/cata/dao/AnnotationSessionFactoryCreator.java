package br.usp.cata.dao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import br.com.caelum.vraptor.ioc.RequestScoped;

import br.usp.cata.model.ExactMatching;
import br.usp.cata.model.Keyword;
import br.usp.cata.model.KeywordSet;
import br.usp.cata.model.Lemma;
import br.usp.cata.model.Opinion;
import br.usp.cata.model.PatternSuggestionPair;
import br.usp.cata.model.Rule;
import br.usp.cata.model.Source;
import br.usp.cata.model.User;


@SuppressWarnings("deprecation") //FIXME
@RequestScoped
@Component
public class AnnotationSessionFactoryCreator implements ComponentFactory<SessionFactory> {
    
	private SessionFactory factory;

    public AnnotationSessionFactoryCreator() {
    }

    @PostConstruct
    public void create() {
		final AnnotationConfiguration cfg = new AnnotationConfiguration();
		cfg.addAnnotatedClass(User.class);
		cfg.addAnnotatedClass(PatternSuggestionPair.class);
		cfg.addAnnotatedClass(Lemma.class);
		cfg.addAnnotatedClass(ExactMatching.class);
        cfg.addAnnotatedClass(Rule.class);
        cfg.addAnnotatedClass(Source.class);
        cfg.addAnnotatedClass(Opinion.class);
        cfg.addAnnotatedClass(KeywordSet.class);
        cfg.addAnnotatedClass(Keyword.class);
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
