package si.fri.prpoVaje03.storitve;

import org.eclipse.persistence.sessions.Session;
import si.fri.prpoVaje03.entitete.Professor;
import si.fri.prpoVaje03.entitete.Topic;
import si.fri.prpoVaje03.lib.ProfessorDTO;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
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

@RequestScoped

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

    public Professor getProfessor(int id) { return repo.getProfessor(id); }

    public Professor create(ProfessorDTO profDTO) {
        if (profDTO.getProfFirstName() == null || profDTO.getProfFirstName().trim().isEmpty() ||
                profDTO.getProfLastName() == null || profDTO.getProfLastName().trim().isEmpty() ||
                profDTO.getProfEmail() == null || profDTO.getProfEmail().trim().isEmpty()) {
            return null;
        }

        Professor p = new Professor();
        p.setProfessorFirstName(profDTO.getProfFirstName());
        p.setProfessorLastName(profDTO.getProfLastName());
        p.setProfessorMail(profDTO.getProfEmail());

        repo.createProfessor(p);

        return p;
    }

    public Professor update(ProfessorDTO profDTO) {
        if (profDTO.getProfID() == null || profDTO.getProfID().trim().isEmpty() || !profDTO.getProfID().matches("\\d+") ||
                profDTO.getProfFirstName() == null || profDTO.getProfFirstName().trim().isEmpty() ||
                profDTO.getProfLastName() == null || profDTO.getProfLastName().trim().isEmpty() ||
                profDTO.getProfEmail() == null || profDTO.getProfEmail().trim().isEmpty()) {
            return null;
        }

        int profId = Integer.parseInt(profDTO.getProfID());
        Professor p = repo.getProfessor(profId);
        p.setProfessorFirstName(profDTO.getProfFirstName());
        p.setProfessorLastName(profDTO.getProfLastName());
        p.setProfessorMail(profDTO.getProfEmail());

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
