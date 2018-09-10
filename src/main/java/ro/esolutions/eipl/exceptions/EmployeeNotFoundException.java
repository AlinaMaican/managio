package ro.esolutions.eipl.exceptions;

public class EmployeeNotFoundException extends RuntimeException{

    private static final String MESSAGE = "Employee not found";

    public EmployeeNotFoundException() {
        super(MESSAGE);
    }
}
