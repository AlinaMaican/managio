package ro.esolutions.eipl.exceptions;

import org.springframework.validation.BindingResult;

public class BindingValidationException extends RuntimeException {
    final BindingResult bindingResult;

    public BindingValidationException(final BindingResult bindingResult) {
        super("BindingValidationException handled");
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
