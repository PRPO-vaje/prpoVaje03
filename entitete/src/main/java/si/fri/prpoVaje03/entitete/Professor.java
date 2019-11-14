package si.fri.prpoVaje03.entitete;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Entity(name="professor")
@Table(name="professors", schema="public")
@NamedQueries(value =
        {
                @NamedQuery(name = "Professor.getAll", query = "SELECT o FROM professor o"),
                @NamedQuery(name = "Professor.getByID", query = "SELECT o FROM professor o WHERE o.id = ?1"),
                @NamedQuery(name = "Professor.getByNameSurname", query = "SELECT o FROM professor o WHERE o.firstName = ?1 and o.lastName = ?2"),
//                @NamedQuery(name = "Professor.update", query = "UPDATE professor o SET o.firstName = ?1, o.lastName = ?2, o.email = ?3 WHERE o.id = ?4"),
//                @NamedQuery(name = "Professor.delete", query = "DELETE FROM professor o WHERE o.id = ?1")
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

    //@JsonBTransient
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<Topic> topics = new ArrayList<>();

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

    @Override
    public String toString() {
        return "\nPROFESSOR:\nid: "+ id.toString() + "\nname: " + firstName + " " + lastName + "\nemail: " + email + "\n" + topics.toString() + "\n";
    }

}
