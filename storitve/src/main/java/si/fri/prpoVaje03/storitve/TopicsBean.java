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
        return em.createNamedQuery("Topic.getAll").getResultList();
    }

    public List<Topic> getTopics(int id) {
        return em.createNamedQuery("Topic.getByID").setParameter(1, id).getResultList();
    }

    public List<Topic> getTopics(String name, String surname) {
        return em.createNamedQuery("Topic.getByAuthor").setParameter(1, name).setParameter(2, surname).getResultList();
    }

}