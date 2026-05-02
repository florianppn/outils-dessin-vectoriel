package com.drawing.error;

public class InvalidArgException extends RuntimeException {

    public InvalidArgException(String message) {
        super(message);
    }

}