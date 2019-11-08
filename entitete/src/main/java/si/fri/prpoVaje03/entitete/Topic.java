package si.fri.prpoVaje03.entitete;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    private Professor author;

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

}
