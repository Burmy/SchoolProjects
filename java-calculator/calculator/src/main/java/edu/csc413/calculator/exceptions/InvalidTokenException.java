package edu.csc413.calculator.exceptions;

public class InvalidTokenException extends Exception {
    public InvalidTokenException(String message) {
        super("*** Invalid Token *** : " + message);
    }
}
