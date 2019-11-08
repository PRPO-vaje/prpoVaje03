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
        return em.createNamedQuery("Student.getAll").getResultList();
    }
    public List<Student> getStudenti(String number) {
        return em.createNamedQuery("Student.getByNumber").setParameter(1, number).getResultList();
    }

    public List<Student> getStudenti(int id) {
        return em.createNamedQuery("Student.getByID").setParameter(1, id).getResultList();
    }

    public List<Student> getStudenti(String name, String surname) {
        return em.createNamedQuery("Student.getByNameSurname").setParameter(1, name).setParameter(2, surname).getResultList();
    }
}