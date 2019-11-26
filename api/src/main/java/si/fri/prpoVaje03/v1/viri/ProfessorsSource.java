package si.fri.prpoVaje03.v1.viri;

import si.fri.prpoVaje03.entitete.Professor;
import si.fri.prpoVaje03.lib.ProfessorDTO;
import si.fri.prpoVaje03.storitve.ProfessorBean;
import si.fri.prpoVaje03.storitve.ProfessorManagerBean;
import si.fri.popoVaje03.mappers.EntityDTOMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/professors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ProfessorsSource {

    @Inject
    private ProfessorManagerBean professorManagerBean;

    @GET
    public Response getProfessors() {
        List<ProfessorDTO> professors = professorManagerBean.getAll();
        return Response.status(Response.Status.OK).entity(professors).build();
    }

    @GET
    @Path("{id}")
    public Response getProfessors(@PathParam("id") int id) {
        ProfessorDTO professor = professorManagerBean.getProfessor(id);
        return Response.status(Response.Status.OK).entity("professor").build();
    }

    @POST
    public Response createProfessor(ProfessorDTO professorDTO) {
        ProfessorDTO professor = professorManagerBean.create(professorDTO);
        return Response.status(Response.Status.OK).entity(professor).build();
    }

    @PUT
    public Response updateProfessor(ProfessorDTO professorDTO) {
        ProfessorDTO professor = professorManagerBean.update(professorDTO);
        return Response.status(Response.Status.OK).entity(professor).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteProfessor(@PathParam("id") String id) {
        professorManagerBean.delete(id);
        return Response.status(Response.Status.OK).build();
    }

}
