package ro.esolutions.eipl.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User with given id does not exists!");
    }
}
