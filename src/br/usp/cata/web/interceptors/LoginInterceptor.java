package br.usp.cata.web.interceptors;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;

import br.usp.cata.web.controller.IndexController;
import br.usp.cata.web.controller.UserSession;


@Intercepts
@RequestScoped
public class LoginInterceptor implements Interceptor {
	
    private final Result result;
    private final UserSession userSession;

    public LoginInterceptor(final Result result, final UserSession userSession) {
        this.result = result;
        this.userSession = userSession;
    }

    public boolean accepts(ResourceMethod method) {
        return true;
    }

    public void intercept(InterceptorStack stack, ResourceMethod method,
    		Object resourceInstance) {
        if(userSession.isAuthenticatedUser())
            result.redirectTo(IndexController.class).index();
        else if(wantsAccessToIrrestrictMethod(method))
            stack.next(method, resourceInstance);
        else
            result.redirectTo(IndexController.class).index();
    }

    private boolean wantsAccessToIrrestrictMethod(final ResourceMethod method ) {
        return method.getMethod().isAnnotationPresent(IrrestrictAccess.class) ||
        		method.getResource().getType().isAnnotationPresent(IrrestrictAccess.class);
    }
}

