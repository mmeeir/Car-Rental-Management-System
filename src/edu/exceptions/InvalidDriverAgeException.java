package edu.exceptions;

public class InvalidDriverAgeException extends RuntimeException {
    public InvalidDriverAgeException(String message) {
        super(message);
    }
}
