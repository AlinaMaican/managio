package ro.esolutions.eipl.advices;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.esolutions.eipl.exceptions.UserAlreadyExistsException;
import ro.esolutions.eipl.exceptions.UserEmailAlreadyExists;

@RestControllerAdvice
public class UserAdvices {

    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    public ResponseEntity<Object> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(value = {UserEmailAlreadyExists.class})
    public ResponseEntity<Object> handleUserEmailAlreadyExists(UserEmailAlreadyExists ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
