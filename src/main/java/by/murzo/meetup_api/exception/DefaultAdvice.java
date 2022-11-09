package by.murzo.meetup_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(MeetupNotFoundException.class)
    public ResponseEntity<Response> handleMeetupNotFoundException(MeetupNotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
}
