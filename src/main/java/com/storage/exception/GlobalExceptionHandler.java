package com.storage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({DuplicateContentException.class})
    public ResponseEntity<ExceptionMessage> handleDuplicateContentException(DuplicateContentException e) {
        return new ResponseEntity<>(new ExceptionMessage(e.getTitle(), e.getMessage(), e.getData()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ForbiddenAccessException.class})
    public ResponseEntity<ExceptionMessage> handleForbiddenAccessException(ForbiddenAccessException e) {
        return new ResponseEntity<>(new ExceptionMessage(e.getTitle(), e.getMessage(), e.getData()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ExceptionMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ExceptionMessage> listErrors = new ArrayList<>();

        for (ObjectError error : e.getAllErrors()) {
            ExceptionMessage ee = new ExceptionMessage(error.toString().split(" ")[7].substring(1, error.toString().split(" ")[7].length() - 2), error.getDefaultMessage(), null);
            listErrors.add(ee);
        }

        return new ResponseEntity<>(new ExceptionMessage(null, "Validation failed. Error count: " + e.getErrorCount(), listErrors), HttpStatus.BAD_REQUEST);
    }
}
