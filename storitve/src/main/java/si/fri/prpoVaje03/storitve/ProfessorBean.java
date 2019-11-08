package si.fri.prpoVaje03.storitve;

import si.fri.prpoVaje03.entitete.Professor;
import si.fri.prpoVaje03.entitete.Topic;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class ProfessorBean {
    //@Inject
    @PersistenceContext(unitName = "prpoVaje03-JPA")
    private EntityManager em;

    //EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("prpoVaje03-JPA");
    //private EntityManager em = entityManagerFactory.createEntityManager();

    public List<Professor> getProfessors() {

        if(em==null)
            System.out.println("em null");
        else
            System.out.println("em NOT null");

        Query q = em.createNamedQuery("Professor.getAll");

        List<Professor> l = q.getResultList();

        System.out.println(l.get(0).getProfessorTopics());

        return l;
    }

}