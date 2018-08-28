package ro.esolutions.eipl.models.validators;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import ro.esolutions.eipl.models.constraints.UniqueEmail;
import ro.esolutions.eipl.repositories.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    public static final String MESSAGE = "Email already exists";

    private final UserRepository userRepository;

    public void initialize(final UniqueEmail constraint) {
    }

    public boolean isValid(final String email, final ConstraintValidatorContext context) {
        if (Strings.isBlank(email)) throw new RuntimeException("Email field shouldn't be be blank");
        return !userRepository.findFirstByEmail(email).isPresent();
    }
}
