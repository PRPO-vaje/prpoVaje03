package si.fri.prpoVaje03.lib;

public class ExceptionDTO {
    private String parameter;
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getMessage() {
        return message;
    }

    public String getParameter() {
        return parameter;
    }
}
