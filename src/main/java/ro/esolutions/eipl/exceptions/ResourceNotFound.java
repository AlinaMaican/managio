package ro.esolutions.eipl.exceptions;

import java.util.Objects;

public class ResourceNotFound extends RuntimeException {
    private final Long id;
    private final String className;

    public ResourceNotFound(final Long id, final String className) {
        super(className + " with id " + id.toString() + " not found");
        Objects.requireNonNull(id);
        Objects.requireNonNull(className);
        this.id = id;
        this.className = className;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceNotFound that = (ResourceNotFound) o;

        if (!id.equals(that.id)) return false;
        return className.equals(that.className);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + className.hashCode();
        return result;
    }
}
