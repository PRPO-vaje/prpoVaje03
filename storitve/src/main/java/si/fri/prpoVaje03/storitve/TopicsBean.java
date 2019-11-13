package si.fri.prpoVaje03.storitve;

import si.fri.prpoVaje03.entitete.Professor;
import si.fri.prpoVaje03.entitete.Topic;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class TopicsBean {

    private static final Logger LOGGER = Logger.getLogger(TopicsBean.class.getName());

    @PostConstruct
    public void init(){
        LOGGER.log(Level.INFO, "initialized");
    }

    @PreDestroy
    public void destory(){
        LOGGER.log(Level.INFO, "destroyed");
    }

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

    public void createProfessor(Topic t) {
        em.persist(t);
    }

    public void updateProfessor(Topic t) {
        em.merge(t);
    }

    public void deleteProfessor(int id) {
        em.remove(em.find(Topic.class, id));
    }

}