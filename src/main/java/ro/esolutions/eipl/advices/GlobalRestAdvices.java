package ro.esolutions.eipl.advices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ro.esolutions.eipl.exceptions.BindingValidationException;
import ro.esolutions.eipl.exceptions.ResourceNotFound;
import ro.esolutions.eipl.models.FieldErrorModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


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

    /**
     * doesn't get handled before {@link BindingValidationException}
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.debug(ex.getMessage(),ex);
        return ResponseEntity.status(status).headers(headers).body(fromBindingResultToMap(ex.getBindingResult()));
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

        Optional<String> message = getLocalisedMessage(code);
        fieldErrorModel.getCodeMessageMap().put(code, message.orElse(code));
        fieldErrorMap.putIfAbsent(fieldError.getField(), fieldErrorModel);
    }

    private Optional<String> getLocalisedMessage(String code) {
        String message = null;
        try {
            message = messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException ex) {
            log.warn(ex.getMessage(), ex);
        }
        return Optional.ofNullable(message);
    }
}
