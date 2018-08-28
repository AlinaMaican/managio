package ro.esolutions.eipl.exceptions;

public class UserEmailAlreadyExists extends RuntimeException {
    private static final String MESSAGE = "User with email %s already exists!";

    public UserEmailAlreadyExists(String email) {
        super(String.format(MESSAGE, email));
    }
}
