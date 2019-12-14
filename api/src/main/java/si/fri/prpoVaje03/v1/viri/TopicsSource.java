package si.fri.prpoVaje03.v1.viri;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.fri.popoVaje03.mappers.EntityDTOMapper;
import si.fri.prpoVaje03.exceptions.RequestArgumentException;
import si.fri.prpoVaje03.lib.TopicDTO;
import si.fri.prpoVaje03.storitve.TopicsBean;

import javax.enterprise.context.ApplicationScoped;
import org.glassfish.jersey.media.multipart.FormDataParam;
import si.fri.prpoVaje03.storitve.TopicsManagerBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.InputStream;
import java.util.List;

@Path("/topics")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class TopicsSource {

    @Inject
    private TopicsManagerBean topicsManagerBean;
    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getTopics() {
        QueryParameters queryParams = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        System.out.println(queryParams);
        List<TopicDTO> topics = topicsManagerBean.getAll(queryParams);
        long topicsCount = topicsManagerBean.getAllCount(queryParams);
        return Response.status(Response.Status.OK).entity(topics).header("X-Total-Count", topicsCount).build();
    }

    @POST
    @Path("{id}/files")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response addFile(@FormDataParam("fileStream") InputStream fileStream, @FormDataParam("filename") String filename, @PathParam("id") int id) {
        try {
            TopicDTO topic = topicsManagerBean.addFile(id, filename, fileStream);
            return Response.status(Response.Status.CREATED).entity(topic).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }
}
