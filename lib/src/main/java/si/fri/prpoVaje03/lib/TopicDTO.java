package si.fri.prpoVaje03.lib;

import javax.json.bind.annotation.JsonbTransient;

public class TopicDTO {
    public String id;
    public String title;
    public String description;
    @JsonbTransient
    public ProfessorDTO author;

    public String getProfID() {
        return this.id;
    };
    public void setProfID(String id) {
        this.id = id;
    }

    public String getProfFirstName() {
        return this.title;
    }
    public void setProfFirstName(String firstName) {
        this.title = firstName;
    }

    public String getProfLastName() {
        return this.description;
    }
    public void setProfLastName(String lastName) {
        this.description = lastName;
    }

    //Causes cyclic dependency, do not use or change
    public ProfessorDTO getProfEmail() {
        return this.author;
    }
    public void setProfEmail(ProfessorDTO email) {
        this.author = email;
    }
}
