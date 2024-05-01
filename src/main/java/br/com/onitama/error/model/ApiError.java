package br.com.onitama.error.model;

import java.util.Collections;
import java.util.List;

public class ApiError {

    private int statusCode;
    private String message;
    private List<String> errors;

    public ApiError(int statusCode, String message, List<String> errors) {
        super();
        this.statusCode = statusCode;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(int statusCode, String message, String error) {
        super();
        this.statusCode = statusCode;
        this.message = message;
        this.errors = Collections.singletonList(error);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
