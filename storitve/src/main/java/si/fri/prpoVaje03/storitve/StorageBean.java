package si.fri.prpoVaje03.storitve;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import org.glassfish.jersey.media.multipart.Boundary;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.glassfish.jersey.media.multipart.file.StreamDataBodyPart;
import si.fri.prpoVaje03.entitete.FileUpload;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class StorageBean {
    private static final Logger LOGGER = Logger.getLogger(StorageBean.class.getName());

    private java.util.UUID UUID = java.util.UUID.randomUUID();

    private Client httpClient;
    String baseUrl;
    String bucket;

    @PostConstruct
    public void init(){
        LOGGER.log(Level.INFO, "initialized UUID: " + UUID);
        httpClient = ClientBuilder.newClient();
        httpClient.register(MultiPartFeature.class);
        baseUrl = ConfigurationUtil.getInstance().get("kumuluzee.storage.base-url").orElse("");
        bucket = ConfigurationUtil.getInstance().get("kumuluzee.storage.bucket").orElse("");
    }

    @PreDestroy
    public void destory(){
        LOGGER.log(Level.INFO, "destroyed UUID: " + UUID);
    }


    public Response SaveFile(InputStream stream, String filename) {
        MultiPart multiPart = new MultiPart();
        multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
        StreamDataBodyPart streamBody = new StreamDataBodyPart("fileStream", stream);
        multiPart.bodyPart(streamBody);
        MediaType contentType = MediaType.MULTIPART_FORM_DATA_TYPE;
        contentType = Boundary.addBoundary(contentType);

        Response res = httpClient
                .target(baseUrl + bucket + "/" + filename)
                .request().post(Entity.entity(multiPart, contentType));
        return res;
    }
}
