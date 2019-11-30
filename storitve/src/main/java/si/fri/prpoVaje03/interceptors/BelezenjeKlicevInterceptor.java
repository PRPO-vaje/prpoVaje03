package si.fri.prpoVaje03.interceptors;

import si.fri.prpoVaje03.storitve.BelezenjeKlicevZrno;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@BeleziKlice
public class BelezenjeKlicevInterceptor {

    @Inject
    private BelezenjeKlicevZrno beleznikKlicev;

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        beleznikKlicev.call();
        Object result = context.proceed();
        return result;
    }
}
