package si.fri.prpoVaje03.storitve;

import si.fri.prpoVaje03.entitete.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import java.util.List;

@ApplicationScoped
public class StudentiZrno {

    @PersistenceContext(unitName = "prpoVaje03-JPA")
    private EntityManager em;

    public List<Student> getStudenti() {

        Query q = em.createNamedQuery("Student.getAll");

        List<Student> l = q.getResultList();

        if(l==null)
            System.out.println("null");
        else
            System.out.println("NOT null");

        return l;
    }
}