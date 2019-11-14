package si.fri.prpoVaje03.storitve;

import si.fri.prpoVaje03.entitete.Student;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class StudentiZrno {
    private static final Logger LOGGER = Logger.getLogger(StudentiZrno.class.getName());

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

    public List<Student> getStudenti() {
        return em.createNamedQuery("Student.getAll").getResultList();
    }
    public List<Student> getStudents(String number) {
        return em.createNamedQuery("Student.getByNumber").setParameter(1, number).getResultList();
    }

    public List<Student> getStudents(int id) {
        return em.createNamedQuery("Student.getByID").setParameter(1, id).getResultList();
    }

    public List<Student> getStudents(String name, String surname) {
        return em.createNamedQuery("Student.getByNameSurname").setParameter(1, name).setParameter(2, surname).getResultList();
    }

    public void createStudent(Student s) {
        em.persist(s);
    }

    public void updateStudent(Student s) {
        em.merge(s);
    }

    public void deleteStudent(int id) {
        em.remove(em.find(Student.class, id));
    }
}