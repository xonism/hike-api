package com.api.hikes.exceptions;

public class InvalidSeasonException extends RuntimeException {

    public InvalidSeasonException(String season) {
        super(String.format("Invalid season '%s' provided", season));
    }
}
