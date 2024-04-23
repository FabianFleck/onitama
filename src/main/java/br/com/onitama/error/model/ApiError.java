package br.com.onitama.error.model;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

public class ApiError {

    private HttpStatus status;
    private int statusCode;
    private String message;
    private List<String> errors;

    public ApiError(HttpStatus status, int statusCode, String message, List<String> errors) {
        super();
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, int statusCode, String message, String error) {
        super();
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.errors = Collections.singletonList(error);
    }


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
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
