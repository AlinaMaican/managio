package ro.esolutions.eipl.advices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ro.esolutions.eipl.exceptions.BindingValidationException;
import ro.esolutions.eipl.exceptions.ResourceNotFound;
import ro.esolutions.eipl.models.FieldErrorModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalRestAdvices extends ResponseEntityExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler({ResourceNotFound.class})
    protected ResponseEntity<Object> handleResourceNotFound(ResourceNotFound ex) {
        log.debug(ex.getMessage(), ex);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({BindingValidationException.class})
    protected ResponseEntity<Object> handleBindingValidationException(BindingValidationException ex) {
        log.debug(ex.getMessage(), ex);
        return ResponseEntity.badRequest().body(fromBindingResultToMap(ex.getBindingResult()));
    }

    private Map<String, FieldErrorModel> fromBindingResultToMap(BindingResult bindingResult) {
        Map<String, FieldErrorModel> fieldErrorMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach(fieldError -> insertFieldError(fieldErrorMap, fieldError));
        return fieldErrorMap;
    }

    private void insertFieldError(Map<String, FieldErrorModel> fieldErrorMap, FieldError fieldError) {
        String rejectedValue = fieldError.getRejectedValue() == null ? null : fieldError.getRejectedValue().toString();

        FieldErrorModel fieldErrorModel = fieldErrorMap.getOrDefault(
                fieldError.getField(), new FieldErrorModel(rejectedValue, new HashMap<>()));

        String code = fieldError.getCode();
        Objects.requireNonNull(code);

        String message = null;
        try {
            message = messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException ex) {
            log.warn(ex.getMessage(), ex);
        }
        message = message == null ? code : message;
        fieldErrorModel.getCodeMessageMap().put(code, message);
        fieldErrorMap.putIfAbsent(fieldError.getField(), fieldErrorModel);
    }
}
