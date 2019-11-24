package si.fri.prpoVaje03.entitete;

import javax.persistence.*;
import java.util.List;

@Entity(name="student")
@Table(name="students", schema="public")
@NamedQueries(value =
        {
                @NamedQuery(name = "Student.getAll", query = "SELECT o FROM student o"),
                @NamedQuery(name = "Student.getByNumber", query = "SELECT o FROM student o WHERE o.studentNumber = ?1"), //Query query = em.createNamedQuery("Student.getByNumber").setParameter(1, studentNumber);
                @NamedQuery(name = "Student.getByID", query = "SELECT o FROM student o WHERE o.id = ?1"),
                @NamedQuery(name = "Student.getByNameSurname", query = "SELECT o FROM student o WHERE o.firstName = ?1 and o.lastName = ?1")
        })
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="STUDENT_ID")
    private Integer id;

    @Column(name="STUDENT_NUMBER")
    private String studentNumber;

    @Column(name="STUDENT_NAME")
    private String firstName;

    @Column(name="STUDENT_SURNAME")
    private String lastName;

    @Column(name="STUDENT_EMAIL")
    private String email;

    //@javax.json.bind.annotation.JsonbTransient
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "appliedStudents")
    private List<Topic> topics;

    public Integer getStudentId() {
        return id;
    }
    public void setStudentId(Integer id) {
        this.id = id;
    }

    public String getStudentFirstName() {
        return firstName;
    }
    public void setUserFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getStudentLastName() {
        return lastName;
    }
    public void setStudentLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentMail() {
        return email;
    }
    public void setStudentMail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "\nSTUDENT:\nid: "+ id.toString() + "\nname: " + firstName + " " + lastName + "\nemail: " + email + "\n" + topics.toString() + "\n";
    }

}
