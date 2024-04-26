package br.com.onitama.error.exception;

import org.springframework.http.HttpStatus;

public class UnprocessableEntityException extends OnitamaException {
    public UnprocessableEntityException(String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, message);
    }
}
