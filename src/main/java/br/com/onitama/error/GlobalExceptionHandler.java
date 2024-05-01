package br.com.onitama.error;

import br.com.onitama.error.exception.BadRequestException;
import br.com.onitama.error.exception.UnauthorizedException;
import br.com.onitama.error.exception.UnprocessableEntityException;
import br.com.onitama.error.model.ApiError;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal server error",
                ex.getLocalizedMessage());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.UNAUTHORIZED.value(),
                "Error from AuthenticationException",
                ex.getLocalizedMessage());
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequestException(BadRequestException ex, WebRequest request) {
        ApiError apiError = new ApiError(
                ex.getStatus().value(),
                "Error from BadRequestException",
                ex.getLocalizedMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<ApiError> handleUnprocessableEntityException(UnprocessableEntityException ex, WebRequest request) {
        ApiError apiError = new ApiError(
                ex.getStatus().value(),
                "Error from UnprocessableEntityException",
                ex.getLocalizedMessage());
        return new ResponseEntity<>(apiError, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<?> handleJwtException(JwtException ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.FORBIDDEN.value(),
                "Error from JwtException",
                ex.getLocalizedMessage());
        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
    }
}
