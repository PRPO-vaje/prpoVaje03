package si.fri.prpoVaje03.v1;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class ReqFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext request,
                       ContainerResponseContext response) throws IOException {
        response.getHeaders().add("Access-Control-Allow-Origin", "*");
        response.getHeaders().add("Access-Control-Allow-Headers",
                "Access-Control-Allow-Methods, Access-Control-Allow-Origin, Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
        response.getHeaders().add("Access-Control-Request-Headers", "Access-Control-Allow-Origin, Content-Type");
        response.getHeaders().add("Access-Control-Allow-Credentials", "true");
        response.getHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }

}