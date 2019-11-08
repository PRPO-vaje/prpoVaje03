import si.fri.prpoVaje03.entitete.Student;
import si.fri.prpoVaje03.entitete.Topic;
import si.fri.prpoVaje03.storitve.ProfessorBean;
import si.fri.prpoVaje03.storitve.StudentiZrno;
import si.fri.prpoVaje03.storitve.TopicsBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/servlet/topics")
public class JPAServletTopics extends HttpServlet {

    @Inject
    private StudentiZrno studentiZrno;
    @Inject
    private ProfessorBean professorBean;
    @Inject
    private TopicsBean topicsBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Topic> topics = topicsBean.getTopics();

        resp.getWriter().println(topics.toString());

    }
}