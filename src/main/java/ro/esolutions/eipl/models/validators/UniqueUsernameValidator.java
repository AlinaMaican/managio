package ro.esolutions.eipl.models.validators;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import ro.esolutions.eipl.models.constraints.UniqueUsername;
import ro.esolutions.eipl.repositories.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    public static final String MESSAGE = "Username already exists";

    private final UserRepository userRepository;

    public void initialize(final UniqueUsername constraint) {
    }

    public boolean isValid(final String username, final ConstraintValidatorContext context) {
        if (Strings.isBlank(username)) throw new RuntimeException("Username field shouldn't be be blank");
        return !userRepository.findByUsername(username).isPresent();
    }
}
