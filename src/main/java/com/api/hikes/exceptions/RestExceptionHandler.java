package com.api.hikes.exceptions;

import com.api.hikes.records.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {InvalidSeasonException.class})
    public ResponseEntity<Error> handleInvalidSeasonException(
            InvalidSeasonException exception
    ) {
        return new ResponseEntity<>(
                new Error(exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
