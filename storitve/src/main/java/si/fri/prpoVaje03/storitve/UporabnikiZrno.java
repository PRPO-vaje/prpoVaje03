package si.fri.prpoVaje03.storitve;

import si.fri.prpoVaje03.entitete.Opomnik;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class UporabnikiZrno {

    @PersistenceContext(unitName = "lokacijski-opomniki-jpa")
    private EntityManager em;

    public List<Opomnik> getUporabniki() {

        // implementacija

        return null;
    }
}