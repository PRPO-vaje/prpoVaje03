package si.fri.prpoVaje03.v1.viri;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import si.fri.prpoVaje03.entitete.Professor;
import si.fri.prpoVaje03.exceptions.RequestArgumentException;
import si.fri.prpoVaje03.lib.ExceptionDTO;
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

    @Operation(description = "Returns list of Professors.", summary = "Professors list", tags = "professors", responses = {
            @ApiResponse(responseCode = "200",
                    description = "List of professors",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = ProfessorDTO.class)))
            )})
    @GET
    public Response getProfessors() {
        List<ProfessorDTO> professors = professorManagerBean.getAll();
        return Response.status(Response.Status.OK).entity(professors).build();
    }

    @Operation(description = "Returns a Professor with a given id.", parameters = @Parameter(name = "id"), summary = "Professors", tags = "professors", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Professor",
                    content = @Content(schema = @Schema(implementation = ProfessorDTO.class))
            )})
    @GET
    @Path("{id}")
    public Response getProfessors(@PathParam("id") int id) {
        ProfessorDTO professor = professorManagerBean.getProfessor(id);
        return Response.status(Response.Status.OK).entity("professor").build();
    }

    @Operation(description = "Creates and returns a professor", requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = ProfessorDTO.class))), summary = "Create professor", tags = "professors", responses = {
            @ApiResponse(responseCode = "201",
                    description = "Create professor",
                    content = @Content(schema = @Schema(implementation = ProfessorDTO.class))
            ),
            @ApiResponse(responseCode = "400",
                    description = "Create professor parameter error",
                    content = @Content(schema = @Schema(implementation = ExceptionDTO.class))
            )
    })
    @POST
    public Response createProfessor(ProfessorDTO professorDTO) {
        try {
        ProfessorDTO professor = professorManagerBean.create(professorDTO);
        return Response.status(Response.Status.CREATED).entity(professor).build();
        } catch (RequestArgumentException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(EntityDTOMapper.MapRequestException(ex)).build();
        }
    }

    @Operation(description = "Updates and returns a professor", requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = ProfessorDTO.class))), summary = "Update professor", tags = "professors", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Update professor",
                    content = @Content(schema = @Schema(implementation = ProfessorDTO.class))
            ),
            @ApiResponse(responseCode = "400",
                    description = "Update professor parameter error",
                    content = @Content(schema = @Schema(implementation = ExceptionDTO.class))
            )
    })
    @PUT
    public Response updateProfessor(ProfessorDTO professorDTO) {
        try {
            ProfessorDTO professor = professorManagerBean.update(professorDTO);
            return Response.status(Response.Status.OK).entity(professor).build();
        } catch (RequestArgumentException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(EntityDTOMapper.MapRequestException(ex)).build();
        }
    }

    @Operation(description = "Deletes a professor", parameters = @Parameter(name = "id"), summary = "Delete professor", tags = "professors", responses = {
            @ApiResponse(responseCode = "204",
                    description = "Delete professor"
            )})
    @DELETE
    @Path("{id}")
    public Response deleteProfessor(@PathParam("id") String id) {
        professorManagerBean.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
