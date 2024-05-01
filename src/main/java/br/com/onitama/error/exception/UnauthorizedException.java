package br.com.onitama.error.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends OnitamaException {
    public UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
