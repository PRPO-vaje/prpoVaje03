package si.fri.prpoVaje03.lib;

import java.util.ArrayList;

public class ProfessorDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private ArrayList<TopicDTO> topics = new ArrayList<TopicDTO>();

    public String getProfID() {
        return this.id;
    };
    public void setProfID(String id) {
        this.id = id;
    }

    public String getProfFirstName() {
        return this.firstName;
    }
    public void setProfFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getProfLastName() {
        return this.lastName;
    }
    public void setProfLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfEmail() {
        return this.email;
    }
    public void setProfEmail(String email) {
        this.email = email;
    }

    public ArrayList<TopicDTO> getProfTopics() { return this.topics; };
    public void setProfTopics(ArrayList<TopicDTO> topics) { this.topics = topics; };
}
