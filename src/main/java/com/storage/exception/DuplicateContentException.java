package com.storage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateContentException extends RuntimeException {

    private String title;
    private List<ExceptionMessage> errors;

    public DuplicateContentException(String message, String title, List<ExceptionMessage> errors) {
        super(message);
        this.title = title;
        this.errors = errors;
    }

    public String getTitle() {
        return title;
    }

    public List<ExceptionMessage> getData() {
        return errors;
    }
}
