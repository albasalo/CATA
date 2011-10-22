package br.usp.cata.component;

import br.com.caelum.vraptor.interceptor.multipart.DefaultMultipartConfig;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;


@Component
@ApplicationScoped
public class CustomMultipartConfig extends DefaultMultipartConfig {

	@Override
    public long getSizeLimit() {
        return Long.MAX_VALUE;
    }
    
}