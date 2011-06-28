package br.usp.cata.vraptor.provider;

import br.com.caelum.vraptor.ComponentRegistry;
import br.com.caelum.vraptor.ioc.spring.SpringProvider;
import br.com.caelum.vraptor.util.hibernate.SessionCreator;

import br.usp.cata.dao.AnnotationSessionFactoryCreator;


public class ApplicationCustomProvider extends SpringProvider
{
    @Override
    protected void registerCustomComponents(final ComponentRegistry registry) {
        registry.register(SessionCreator.class, SessionCreator.class);
        registry.register(AnnotationSessionFactoryCreator.class, AnnotationSessionFactoryCreator.class);
    }

}
