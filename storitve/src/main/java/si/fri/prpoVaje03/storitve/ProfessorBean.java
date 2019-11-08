package si.fri.prpoVaje03.storitve;

import org.eclipse.persistence.sessions.Session;
import si.fri.prpoVaje03.entitete.Professor;
import si.fri.prpoVaje03.entitete.Topic;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class ProfessorBean {

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

    public List<Professor> getProfessor(int id){
        return em.createNamedQuery("Professor.getByID").setParameter(1, id).getResultList();
    }

    public List<Professor> getProfessor(String name, String surname) {
        return em.createNamedQuery("Professor.getByNameSurname").setParameter(1, name).setParameter(2, surname).getResultList();
    }
}