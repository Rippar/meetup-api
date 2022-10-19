package by.murzo.meetup_api.exception;

public class MeetupNotFoundException extends RuntimeException{
    public MeetupNotFoundException() {
    }

    public MeetupNotFoundException(String message) {
        super(message);
    }

    public MeetupNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MeetupNotFoundException(Throwable cause) {
        super(cause);
    }
}
