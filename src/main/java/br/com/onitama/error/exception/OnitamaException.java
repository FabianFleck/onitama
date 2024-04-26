package br.com.onitama.error.exception;

import org.springframework.http.HttpStatus;

public class OnitamaException extends RuntimeException {
    private final HttpStatus status;

    protected OnitamaException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
