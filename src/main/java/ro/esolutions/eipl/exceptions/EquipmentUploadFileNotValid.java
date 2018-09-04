package ro.esolutions.eipl.exceptions;

public class EquipmentUploadFileNotValid extends RuntimeException {

    private static final String MESSAGE = "File to be uploaded is not valid!";

    public EquipmentUploadFileNotValid() {

        super(String.format(MESSAGE));
    }
}
