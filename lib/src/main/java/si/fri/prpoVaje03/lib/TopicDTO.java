package si.fri.prpoVaje03.lib;

import javax.json.bind.annotation.JsonbTransient;

public class TopicDTO {
    private String id;
    private String title;
    private String description;
    private ProfessorDTO author;

    public String getTopicID() {
        return this.id;
    };
    public void setTopicID(String id) {
        this.id = id;
    }

    public String getTopicTitle() {
        return this.title;
    }
    public void setTopicTitle(String firstName) {
        this.title = firstName;
    }

    public String getTopicDescription() {
        return this.description;
    }
    public void setPTopicDescription(String lastName) {
        this.description = lastName;
    }

    //Causes cyclic dependency, do not use or change
    public ProfessorDTO getTopicAuthor() {
        return this.author;
    }
    public void setTopicAuthor(ProfessorDTO email) {
        this.author = email;
    }
}
