package si.fri.prpoVaje03.lib;

public class StudentDTO {
    public String id;
    public String firstName;
    public String lastName;
    public String email;

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
}
