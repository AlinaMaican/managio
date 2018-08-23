package ro.esolutions.eipl.advices;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.esolutions.eipl.exceptions.UserAlreadyExistsException;
import ro.esolutions.eipl.exceptions.UserEmailAlreadyExists;
import ro.esolutions.eipl.models.FieldErrorModel;

@RestControllerAdvice
public class UserAdvices {

    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    public ResponseEntity<Object> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        return ResponseEntity.badRequest().body(new FieldErrorModel("username", ex.getMessage()));
    }

    @ExceptionHandler(value = {UserEmailAlreadyExists.class})
    public ResponseEntity<Object> handleUserEmailAlreadyExists(UserEmailAlreadyExists ex) {
        return ResponseEntity.badRequest().body(new FieldErrorModel("email", ex.getMessage()));
    }
}
