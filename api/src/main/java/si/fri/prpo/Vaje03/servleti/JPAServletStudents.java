package si.fri.prpo.Vaje03.servleti;

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

@WebServlet("/servlet/students")
public class JPAServletStudents extends HttpServlet {

    @Inject
    private StudentiZrno studentiZrno;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Student> studenti = studentiZrno.getStudenti();

        resp.getWriter().println(studenti.toString());

    }
}