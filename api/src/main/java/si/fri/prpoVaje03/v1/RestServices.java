package si.fri.prpoVaje03.v1;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.discovery.annotations.RegisterService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;

@RegisterService(value = "prpo-mainApp")
@OpenAPIDefinition(info = @Info(title = "Rest API", version = "v1", contact = @Contact(), license = @License(), description = "Thesis management API"), servers = @Server(url ="http://localhost:8083/v1"))
@ApplicationPath("/v1/")
public class RestServices extends javax.ws.rs.core.Application {
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<Class<?>>();
        resources.add(MultiPartFeature.class);
        resources.add(si.fri.prpoVaje03.v1.viri.TopicsSource.class);
        resources.add(si.fri.prpoVaje03.v1.viri.ProfessorsSource.class);
        resources.add(si.fri.prpoVaje03.v1.ReqFilter.class);
        return resources;
    }
}
