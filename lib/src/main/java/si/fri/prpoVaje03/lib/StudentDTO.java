package si.fri.prpoVaje03.lib;

import java.util.ArrayList;

public class StudentDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private ArrayList<TopicDTO> topics = new ArrayList<TopicDTO>();

    public String getStudentID() {
        return this.id;
    };
    public void setStudentID(String id) {
        this.id = id;
    }

    public String getStudentFirstName() {
        return this.firstName;
    }
    public void setStudentFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getStudentLastName() {
        return this.lastName;
    }
    public void setStudentLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentEmail() {
        return this.email;
    }
    public void setStudentEmail(String email) {
        this.email = email;
    }

    public ArrayList<TopicDTO> getStudentTopics() {
        return this.topics;
    }
    public void setStudentTopics(ArrayList<TopicDTO> topics) {
        this.topics = topics;
    }
}
