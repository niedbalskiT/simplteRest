package com.niedbalski.simpleRest.exceptions_handling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Slf4j
@ControllerAdvice
public class GithubResponseErrorHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<GithubErrorResponse> handleException(HttpClientErrorException ex) {
        log.error("Client error thrown while invoking rest template!", ex);
        return new ResponseEntity<>(new GithubErrorResponse(ex.getRawStatusCode(), ex.getStatusText(), ex.getLocalizedMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<GithubErrorResponse> handleException(HttpServerErrorException ex) {
        log.error("Server error thrown while invoking rest template!", ex);
        return new ResponseEntity<>(new GithubErrorResponse(ex.getRawStatusCode(), ex.getStatusText(), ex.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<GithubErrorResponse> handleException(HttpClientErrorException.NotFound ex) {
        log.error("Bad request. User not found!", ex);
        return new ResponseEntity<>(new GithubErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), "Bad request. User not found!"), HttpStatus.BAD_REQUEST);
    }

}
