package si.fri.prpoVaje03.entitete;

import javax.persistence.*;
import java.util.List;

@Entity(name="topic")
@Table(name="topics", schema="public")
@NamedQueries(value =
        {
                @NamedQuery(name = "Topic.getAll", query = "SELECT o FROM topic o"),
                @NamedQuery(name = "Topic.getByID", query = "SELECT o FROM topic o WHERE o.id = ?1"),
                @NamedQuery(name = "Topic.getByAuthor", query = "SELECT o FROM topic o WHERE o.author = ?1")
        })
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TOPIC_ID")
    private Integer id;

    @Column(name="TOPIC_TITLE")
    private String title;

    @Column(name="TOPIC_DESCRIPTION")
    private String description;

    @javax.json.bind.annotation.JsonbTransient
    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    private Professor author;

    @javax.json.bind.annotation.JsonbTransient
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "APPLIED_STUDENTS",
            joinColumns = {@JoinColumn(name = "TOPIC_ID")},
            inverseJoinColumns = {@JoinColumn(name = "STUDENT_ID")}
    )
    private List<Student> appliedStudents;

    public Integer getTopicId() {
        return id;
    }
    public void setTopicId(Integer id) {
        this.id = id;
    }

    public String getTopicTitle() {
        return title;
    }
    public void setTopicTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Professor getAuthor() {
        return author;
    }
    public void setAuthor(Professor author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "\nTopic: {id: "+ id.toString() + " title: " + title + " description: " + description + "}";
    }
}
