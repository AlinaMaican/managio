package ro.esolutions.eipl.mappers;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ro.esolutions.eipl.models.FieldErrorModel;

import java.util.*;

public final class BindingResultMapper {
    private BindingResultMapper() {
    }

    public static Map<String, FieldErrorModel> fromBindingResultToMap(BindingResult bindingResult) {
        Map<String, FieldErrorModel> fieldErrorMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach(fieldError -> handleFieldError(fieldError, fieldErrorMap));
        return fieldErrorMap;
    }

    private static void handleFieldError(FieldError fieldError, Map<String, FieldErrorModel> fieldErrorModelMap) {
        if (fieldErrorModelMap.containsKey(fieldError.getField())) {
            fieldErrorModelMap.get(fieldError.getField()).getMessages().add(fieldError.getDefaultMessage());
            return;
        }
        ArrayList<String> messages = new ArrayList<>();
        messages.add(fieldError.getDefaultMessage());
        Objects.requireNonNull(fieldError.getRejectedValue());
        fieldErrorModelMap.put(fieldError.getField(), new FieldErrorModel(fieldError.getRejectedValue().toString(), messages));
    }
}
