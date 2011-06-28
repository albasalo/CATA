package br.usp.cata.tests;

import org.junit.After;
import org.junit.Before;

import br.com.caelum.vraptor.util.hibernate.SessionCreator;

import br.usp.cata.dao.AnnotationSessionFactoryCreator;

public abstract class DatabaseTestCase extends CataTestCase
{
    private static AnnotationSessionFactoryCreator annotationSessionFactoryCreator;
    private static SessionCreator sessionCreator;

    public DatabaseTestCase(final String name) {
        super( name );
    }

    @Before
    protected void setUp() throws Exception {
        super.setUp();

        if(annotationSessionFactoryCreator == null) {
            annotationSessionFactoryCreator = new AnnotationSessionFactoryCreator();
            annotationSessionFactoryCreator.create();
        }
        if(sessionCreator == null) {
            sessionCreator = new SessionCreator( annotationSessionFactoryCreator.getInstance() );
            sessionCreator.create();
        }

        sessionCreator.getInstance().beginTransaction();
    }

    @After
    protected void tearDown() throws Exception {
        super.tearDown();

        sessionCreator.getInstance().getTransaction().commit();
    }

    public final SessionCreator getSessionCreator() {
        return sessionCreator;
    }

}
