package ro.esolutions.eipl.exceptions;

public class EmployeeUploadFileNotValid extends RuntimeException {

    private static final String MESSAGE = "File to be uploaded is not valid!";

    public EmployeeUploadFileNotValid() {
        super(String.format(MESSAGE));
    }
}