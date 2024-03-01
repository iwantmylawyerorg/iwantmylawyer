package com.thesis.iwantmylawyer.exception;

import com.thesis.iwantmylawyer.address.AddressNotFoundException;
import com.thesis.iwantmylawyer.article.ArticleNotFoundException;
import com.thesis.iwantmylawyer.city.CityAlreadyExistsException;
import com.thesis.iwantmylawyer.city.CityNotFoundException;
import com.thesis.iwantmylawyer.commonquestion.CommonQuestionNotFoundException;
import com.thesis.iwantmylawyer.expertisefield.ExpertiseFieldAlreadyExistsException;
import com.thesis.iwantmylawyer.user.client.ClientAlreadyExistsException;
import com.thesis.iwantmylawyer.user.client.ClientNotFoundException;
import com.thesis.iwantmylawyer.user.lawyer.LawyerAlreadyExistsException;
import com.thesis.iwantmylawyer.user.lawyer.LawyerNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode status,
                                                                  @NonNull WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpertiseFieldAlreadyExistsException.class)
    public ResponseEntity<?> expertiseFieldAlreadyExistsExceptionHandler(ExpertiseFieldAlreadyExistsException exception) {
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.CONFLICT);

    }
    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<?> clientAlreadyExistsExceptionHandler(ClientAlreadyExistsException exception) {
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.CONFLICT);

    }
    @ExceptionHandler(CityAlreadyExistsException.class)
    public ResponseEntity<?> cityAlreadyExistsExceptionHandler(CityAlreadyExistsException exception) {
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.CONFLICT);

    }
    @ExceptionHandler(LawyerAlreadyExistsException.class)
    public ResponseEntity<?> lawyerAlreadyExistsExceptionHandler(LawyerAlreadyExistsException exception) {
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.CONFLICT);

    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<?> clientNotFoundExceptionHandler(ClientNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(LawyerNotFoundException.class)
    public ResponseEntity<?> lawyerNotFoundExceptionHandler(LawyerNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<?> cityNotFoundExceptionHandler(CityNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<?> addressNotFoundExceptionHandler(AddressNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<?> articleNotFoundExceptionHandler(ArticleNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(CommonQuestionNotFoundException.class)
    public ResponseEntity<?> commonQuestionNotFoundExceptionHandler(CommonQuestionNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);

    }
}
