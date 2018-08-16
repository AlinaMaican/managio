package ro.esolutions.eipl.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE = "User with id %s already exists!";

    public UserAlreadyExistsException(final Long userId) {

        super(String.format(MESSAGE, userId));
    }
}
