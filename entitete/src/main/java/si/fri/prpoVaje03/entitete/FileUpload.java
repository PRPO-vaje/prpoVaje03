package si.fri.prpoVaje03.entitete;

import javax.persistence.*;
import java.util.List;

@Entity(name="file")
@Table(name="files", schema="public")
public class FileUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="FILE_ID")
    private int id;

    @Column(name="FILE_NAME")
    private String name;

    @Column(name="FILE_KEY")
    private String key;

    @Column(name="URL")
    private String url;

    @javax.json.bind.annotation.JsonbTransient
    @ManyToOne
    @JoinColumn(name = "TOPIC_ID")
    private Topic topic;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "FileUpload{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", topic=" + topic +
                '}';
    }
}
