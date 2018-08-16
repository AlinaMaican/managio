package ro.esolutions.eipl.exceptions;

public class UserWithGivenIdDoesNotExistException extends RuntimeException {
    public UserWithGivenIdDoesNotExistException() {
        super("User with given id does not exists!");
    }
}
