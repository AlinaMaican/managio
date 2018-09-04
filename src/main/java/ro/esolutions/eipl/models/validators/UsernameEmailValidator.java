package ro.esolutions.eipl.models.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ro.esolutions.eipl.entities.User;
import ro.esolutions.eipl.models.HasUsernameAndEmail;

import ro.esolutions.eipl.repositories.UserRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsernameEmailValidator implements Validator {
    private static final String ERROR_FIELD_UNIQUE = "error.field.unique";

    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return HasUsernameAndEmail.class.isAssignableFrom(clazz);
    }

    @Transactional
    @Override
    public void validate(Object validationTarget, Errors errors) {
        HasUsernameAndEmail target = (HasUsernameAndEmail) validationTarget;
        handleUsernameUnique(target, errors);
        handleEmailUnique(target, errors);
    }

    private void handleUsernameUnique(HasUsernameAndEmail target, Errors errors) {
        Optional<User> user = userRepository.findByUsername(target.getUsername());
        if (user.isPresent() && usersHaveDifferentId(user.get(), target)) {
            errors.rejectValue("username", ERROR_FIELD_UNIQUE);
        }
    }

    private void handleEmailUnique(HasUsernameAndEmail target, Errors errors) {
        Optional<User> user = userRepository.findFirstByEmail(target.getEmail());
        if (user.isPresent() && usersHaveDifferentId(user.get(), target)) {
            errors.rejectValue("email", ERROR_FIELD_UNIQUE);
        }
    }

    private boolean usersHaveDifferentId(User user, HasUsernameAndEmail target) {
        return !user.getId().equals(target.getId());
    }
}
