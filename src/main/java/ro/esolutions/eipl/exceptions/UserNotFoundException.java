package ro.esolutions.eipl.exceptions;

public class UserNotFoundException extends RuntimeException {

    private static final String MESSAGE = "User with given id %s does not exists!";

    public UserNotFoundException(final Long userId) {
        super(String.format(MESSAGE, userId));
    }
}
