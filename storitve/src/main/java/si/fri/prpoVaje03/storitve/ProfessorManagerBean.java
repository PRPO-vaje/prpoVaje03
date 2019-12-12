package si.fri.prpoVaje03.storitve;

import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.persistence.sessions.Session;
import si.fri.popoVaje03.mappers.EntityDTOMapper;
import si.fri.prpoVaje03.entitete.Professor;
import si.fri.prpoVaje03.entitete.Topic;
import si.fri.prpoVaje03.exceptions.RequestArgumentException;
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

    private java.util.UUID UUID = java.util.UUID.randomUUID();

    @PostConstruct
    public void init(){
        LOGGER.log(Level.INFO, "initialized UUID: " + UUID);
    }

    @PreDestroy
    public void destory(){
        LOGGER.log(Level.INFO, "destroyed UUID: " + UUID);
    }

    @Inject
    private ProfessorBean repo;

    public List<ProfessorDTO> getAll(QueryParameters queryParams) {
        //return EntityDTOMapper.ProfessorListToDTO(repo.getProfessorsCriteriaAPI());
        return EntityDTOMapper.ProfessorListToDTO(repo.getProfessors(queryParams));
    }

    public long getAllCount(QueryParameters queryParams) {
        //return EntityDTOMapper.ProfessorListToDTO(repo.getProfessorsCriteriaAPI());
        return repo.getProfessorsCount(queryParams);
    }

    public ProfessorDTO getProfessor(int id) { return EntityDTOMapper.ProfessorToProfessoDTO(repo.getProfessor(id)); }

    public ProfessorDTO create(ProfessorDTO profDTO) throws RequestArgumentException {
        if (profDTO.getProfFirstName() == null || profDTO.getProfFirstName().trim().isEmpty())
            throw new RequestArgumentException("firstName", "First name is required");
        if (profDTO.getProfLastName() == null || profDTO.getProfLastName().trim().isEmpty())
            throw new RequestArgumentException("lastName", "Last name is required");
        if (profDTO.getProfEmail() == null || profDTO.getProfEmail().trim().isEmpty())
            throw new RequestArgumentException("email", "Email is required");

        Professor p = new Professor();
        p.setProfessorFirstName(profDTO.getProfFirstName());
        p.setProfessorLastName(profDTO.getProfLastName());
        p.setProfessorMail(profDTO.getProfEmail());

        repo.createProfessor(p);

        return EntityDTOMapper.ProfessorToProfessoDTO(p);
    }

    public ProfessorDTO update(ProfessorDTO profDTO) throws RequestArgumentException {
        if (profDTO.getProfID() == null || profDTO.getProfID().trim().isEmpty())
            throw new RequestArgumentException("Id", "Id is required");
        if (!profDTO.getProfID().matches("\\d+"))
            throw new RequestArgumentException("Id", "Id must be an integer");
        if (profDTO.getProfFirstName() == null || profDTO.getProfFirstName().trim().isEmpty())
            throw new RequestArgumentException("firstName", "First name is required");
        if (profDTO.getProfLastName() == null || profDTO.getProfLastName().trim().isEmpty())
            throw new RequestArgumentException("lastName", "Last name is required");
        if (profDTO.getProfEmail() == null || profDTO.getProfEmail().trim().isEmpty())
            throw new RequestArgumentException("email", "Email is required");


        int profId = Integer.parseInt(profDTO.getProfID());
        Professor p = repo.getProfessor(profId);
        p.setProfessorFirstName(profDTO.getProfFirstName());
        p.setProfessorLastName(profDTO.getProfLastName());
        p.setProfessorMail(profDTO.getProfEmail());

        repo.updateProfessor(p);

        return EntityDTOMapper.ProfessorToProfessoDTO(p);
    }

    public void delete(String id) {
        if (id == null || id.trim().isEmpty() || !id.matches("\\d+")) {
            return;
        }

        int profId = Integer.parseInt(id);

        repo.deleteProfessor(profId);
    }

}
