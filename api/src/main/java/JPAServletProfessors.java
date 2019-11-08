import si.fri.prpoVaje03.entitete.Professor;
import si.fri.prpoVaje03.entitete.Student;
import si.fri.prpoVaje03.storitve.ProfessorBean;
import si.fri.prpoVaje03.storitve.StudentiZrno;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/servlet/professors")
public class JPAServletProfessors extends HttpServlet {

    @Inject
    private StudentiZrno studentiZrno;
    @Inject
    private ProfessorBean professorBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Professor> professors = professorBean.getProfessors();

        resp.getWriter().println(professors.toString());

    }
}