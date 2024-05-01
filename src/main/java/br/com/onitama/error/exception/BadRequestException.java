package br.com.onitama.error.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends OnitamaException {
    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
