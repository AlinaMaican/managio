package ro.esolutions.eipl.advices;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.esolutions.eipl.exceptions.UserAlreadyExistsException;
import ro.esolutions.eipl.exceptions.UserEmailAlreadyExists;
import ro.esolutions.eipl.models.FieldErrorModel;

@RestControllerAdvice
public class UserAdvices {
}
