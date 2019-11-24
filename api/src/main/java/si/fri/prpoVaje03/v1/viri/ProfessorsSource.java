package si.fri.prpoVaje03.v1.viri;

import si.fri.prpoVaje03.entitete.Professor;
import si.fri.prpoVaje03.lib.ProfessorDTO;
import si.fri.prpoVaje03.storitve.ProfessorBean;
import si.fri.prpoVaje03.storitve.ProfessorManagerBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/uporabniki")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ProfessorsSource {

    @Inject
    private ProfessorManagerBean professorManagerBean;

    @Inject
    private ProfessorBean professorrBean;

    @GET
    public Response getProfessors() {
        List<Professor> professors = professorManagerBean.getAll();
        return Response.status(Response.Status.OK).entity(professors).build();
    }

    @GET
    @Path("{id}")
    public Response getProfessors(@PathParam("id") int id) {
        Professor professor = professorManagerBean.getProfessor(id);
        return Response.status(Response.Status.OK).entity(professor).build();
    }

    @POST
    public Response createProfessor(ProfessorDTO professorDTO) {
        Professor professor = professorManagerBean.create(professorDTO);
        return Response.status(Response.Status.OK).entity(professor).build();
    }

    @PUT
    public Response updateProfessor(ProfessorDTO professorDTO) {
        Professor professor = professorManagerBean.update(professorDTO);
        return Response.status(Response.Status.OK).entity(professor).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteProfessor(@PathParam("id") String id) {
        professorManagerBean.delete(id);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/direct")
    public Response getProfessorsDirect() {
        List<Professor> professors = professorrBean.getProfessors();
        return Response.status(Response.Status.OK).entity(professors).build();
    }

    @GET
    @Path("/direct/{id}")
    public Response getProfessorsDirect(@PathParam("id") int id) {
        Professor professor = professorrBean.getProfessor(id);
        return Response.status(Response.Status.OK).entity(professor).build();
    }

    @POST
    @Path("/direct")
    public Response createProfessorDirect(Professor professor) {
        professorrBean.createProfessor(professor);
        return Response.status(Response.Status.OK).entity(professor).build();
    }

    @PUT
    @Path("/direct")
    public Response updateProfessorDirect(Professor professor) {
        //System.out.println(professor.getId());
        professorrBean.updateProfessor(professor);
        return Response.status(Response.Status.OK).entity(professor).build();
    }

    @DELETE
    @Path("/direct/{id}")
    public Response deleteProfessorDirect(@PathParam("id") int id) {
        professorrBean.deleteProfessor(id);
        return Response.status(Response.Status.OK).build();
    }

}
