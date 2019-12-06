package si.fri.prpoVaje03.exceptions;

public class RequestArgumentException extends Exception {

    private String argument;

    public RequestArgumentException(String argument, String message) {
        super(message);
        this.argument = argument;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }
}
