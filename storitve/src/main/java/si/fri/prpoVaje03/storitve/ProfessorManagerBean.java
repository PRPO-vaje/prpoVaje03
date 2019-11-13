package si.fri.prpoVaje03.storitve;

import org.eclipse.persistence.sessions.Session;
import si.fri.prpoVaje03.entitete.Professor;
import si.fri.prpoVaje03.entitete.Topic;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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

@ApplicationScoped

public class ProfessorManagerBean {
    private static final Logger LOGGER = Logger.getLogger(ProfessorManagerBean.class.getName());

    @PostConstruct
    public void init(){
        LOGGER.log(Level.INFO, "initialized");
    }

    @PreDestroy
    public void destory(){
        LOGGER.log(Level.INFO, "destroyed");
    }

    @Inject
    private ProfessorBean repo;

    public List<Professor> getAll() {
        return repo.getProfessorsCriteriaAPI();
    }

    public Professor create(String firstName, String lastName, String email) {
        if (firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty() ||
                email == null || email.trim().isEmpty()) {
            return null;
        }

        Professor p = new Professor();
        p.setProfessorFirstName(firstName);
        p.setProfessorLastName(lastName);
        p.setProfessorMail(email);

        repo.createProfessor(p);

        return p;
    }

    public Professor update(String id, String firstName, String lastName, String email) {
        if (id == null || id.trim().isEmpty() || !id.matches("\\d+") ||
                firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty() ||
                email == null || email.trim().isEmpty()) {
            return null;
        }

        int profId = Integer.parseInt(id);
        Professor p = repo.getProfessor(profId);
        p.setProfessorFirstName(firstName);
        p.setProfessorLastName(lastName);
        p.setProfessorMail(email);

        repo.updateProfessor(p);

        return p;
    }

    public void delete(String id) {
        if (id == null || id.trim().isEmpty() || !id.matches("\\d+")) {
            return;
        }

        int profId = Integer.parseInt(id);

        repo.deleteProfessor(profId);
    }

}
