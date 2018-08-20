package ro.esolutions.eipl.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE = "User with id %s already exists!";

    public UserAlreadyExistsException(final String  username) {

        super(String.format(MESSAGE, username));
    }
}
