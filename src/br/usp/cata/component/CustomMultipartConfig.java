package br.usp.cata.component;

import br.com.caelum.vraptor.interceptor.multipart.DefaultMultipartConfig;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

import br.usp.cata.model.CataConstraints;


@Component
@ApplicationScoped
public class CustomMultipartConfig extends DefaultMultipartConfig {

	@Override
    public long getSizeLimit() {
        return CataConstraints.FILE_MAX_SIZE; // FIXME
    }
    
}