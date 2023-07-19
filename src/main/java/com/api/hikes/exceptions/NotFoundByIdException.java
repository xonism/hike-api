package com.api.hikes.exceptions;

public class NotFoundByIdException extends RuntimeException {

    public NotFoundByIdException(String entityName) {
        super(String.format("%s with provided ID not found", entityName));
    }
}
