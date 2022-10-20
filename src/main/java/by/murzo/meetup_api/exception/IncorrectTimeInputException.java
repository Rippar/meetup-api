package by.murzo.meetup_api.exception;

public class IncorrectTimeInputException extends RuntimeException {
    public IncorrectTimeInputException() {
        super();
    }

    public IncorrectTimeInputException(String message) {
        super(message);
    }

    public IncorrectTimeInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectTimeInputException(Throwable cause) {
        super(cause);
    }
}
