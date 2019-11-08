package si.fri.prpoVaje03.entitete;

import org.eclipse.persistence.oxm.annotations.XmlWriteOnly;

import javax.persistence.*;
import java.util.List;

@Entity(name="professor")
@Table(name="professors", schema="public")
@NamedQueries(value =
        {
                @NamedQuery(name = "Professor.getAll", query = "SELECT o FROM professor o"),
                @NamedQuery(name = "Professor.getByID", query = "SELECT o FROM professor o WHERE o.id = ?1"),
                @NamedQuery(name = "Professor.getByNameSurname", query = "SELECT o FROM professor o WHERE o.firstName = ?1 and o.lastName = ?1")
        })
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PROFESSOR_ID")
    private Integer id;

    @Column(name="PROFESSOR_NAME")
    private String firstName;

    @Column(name="PROFESSOR_SURNAME")
    private String lastName;

    @Column(name="PROFESSOR_EMAIL")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author")
    private List<Topic> topics;


    public Integer getProfessorId() {
        return id;
    }
    public void setProfessorId(Integer id) {
        this.id = id;
    }

    public String getProfessorFirstName() {
        return firstName;
    }
    public void setProfessorFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getProfessorLastName() {
        return lastName;
    }
    public void setProfessorLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfessorMail() {
        return email;
    }
    public void setProfessorMail(String email) {
        this.email = email;
    }

    public List<Topic> getProfessorTopics() { return topics; }
    public void setProfessorTopics(List<Topic> topics) { this.topics = topics; }

}