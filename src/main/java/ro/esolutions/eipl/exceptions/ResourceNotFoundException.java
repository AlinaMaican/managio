package ro.esolutions.eipl.exceptions;

import java.util.Objects;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(final Long id, final String className) {
        super(className + " with id " + id.toString() + " not found");
        Objects.requireNonNull(id);
        Objects.requireNonNull(className);
    }
}
