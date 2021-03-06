package si.fri.prpo.Vaje03.servleti;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.fri.prpoVaje03.entitete.Professor;
import si.fri.prpoVaje03.entitete.Student;
import si.fri.prpoVaje03.exceptions.RequestArgumentException;
import si.fri.prpoVaje03.storitve.ProfessorBean;
import si.fri.prpoVaje03.storitve.ProfessorManagerBean;
import si.fri.prpoVaje03.storitve.StudentiZrno;
import si.fri.prpoVaje03.lib.ProfessorDTO;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.List;

@WebServlet("/servlet/professors/*")
public class JPAServletProfessors extends HttpServlet {

    @Inject
    private ProfessorManagerBean professorBean;
    @Context
    protected UriInfo uriInfo;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QueryParameters queryParams = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<ProfessorDTO> professors = professorBean.getAll(queryParams);
        resp.getWriter().println(professors.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");

        ProfessorDTO profDTO = new ProfessorDTO();
        profDTO.setProfFirstName(firstName);
        profDTO.setProfLastName(lastName);
        profDTO.setProfEmail(email);

        try {
            ProfessorDTO p = professorBean.create(profDTO);

            resp.getWriter().println(p.toString());
        } catch (RequestArgumentException ex) {
            resp.getWriter().println(ex.toString());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");

        ProfessorDTO profDTO = new ProfessorDTO();
        profDTO.setProfID(id);
        profDTO.setProfFirstName(firstName);
        profDTO.setProfLastName(lastName);
        profDTO.setProfEmail(email);
        try {
            ProfessorDTO p = professorBean.update(profDTO);

            resp.getWriter().println(p.toString());
        } catch (RequestArgumentException ex) {
            resp.getWriter().println(ex.toString());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if(pathInfo == null || pathInfo.equals("/")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String[] splits = pathInfo.split("/");

        if(splits.length != 2) {

            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String id = splits[1];

        professorBean.delete(id);

        resp.getWriter().println();
    }

}