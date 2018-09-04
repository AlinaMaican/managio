package ro.esolutions.eipl.exceptions;

public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(final Long id, String className) {
        super(className + " with id " + id.toString() + " not found");
    }
}
