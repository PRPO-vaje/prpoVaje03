package si.fri.prpoVaje03.storitve;

import org.eclipse.persistence.sessions.Session;
import si.fri.prpoVaje03.entitete.Professor;
import si.fri.prpoVaje03.entitete.Topic;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.UUID;

@ApplicationScoped
public class ProfessorBean {

    private static final Logger LOGGER = Logger.getLogger(ProfessorBean.class.getName());

    private java.util.UUID UUID = java.util.UUID.randomUUID();

    @PostConstruct
    public void init(){
        LOGGER.log(Level.INFO, "initialized UUID: " + UUID);
    }

    @PreDestroy
    public void destory(){
        LOGGER.log(Level.INFO, "destroyed UUID: " + UUID);
    }

    @PersistenceContext(unitName = "prpoVaje03-JPA")
    private EntityManager em;

    public List<Professor> getProfessorsCriteriaAPI() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Professor> cr = cb.createQuery(Professor.class);
        Root<Professor> root = cr.from(Professor.class);
        cr.select(root);

        TypedQuery<Professor> query = em.createQuery(cr);
        List<Professor> results = query.getResultList();
        return results;
    }

    public List<Professor> getProfessors() {
        return em.createNamedQuery("Professor.getAll").getResultList();
    }

    public Professor getProfessor(int id){
        return em.find(Professor.class, id);
        //return (Professor) em.createNamedQuery("Professor.getByID").setParameter(1, id).getResultList().get(0);
    }

    public List<Professor> getProfessor(String name, String surname) {
        return em.createNamedQuery("Professor.getByNameSurname").setParameter(1, name).setParameter(2, surname).getResultList();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void createProfessor(Professor p) {
        em.persist(p);
        em.flush();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void updateProfessor(Professor p) {
        em.merge(p);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteProfessor(int id) {
        em.remove(em.find(Professor.class, id));
        em.flush();
    }

}