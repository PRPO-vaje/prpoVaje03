package si.fri.prpoVaje03.storitve;

import si.fri.prpoVaje03.entitete.Professor;
import si.fri.prpoVaje03.entitete.Topic;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class TopicsBean {

    @PersistenceContext(unitName = "prpoVaje03-JPA")
    private EntityManager em;

    public List<Topic> getTopics() {
        Query q = em.createNamedQuery("Topic.getAll");

        List<Topic> l = q.getResultList();

        System.out.println(l);

        return l;
    }

}