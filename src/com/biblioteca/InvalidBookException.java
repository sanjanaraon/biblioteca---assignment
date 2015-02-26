package com.biblioteca;

/**
 * Created by sanjanar on 25/02/15.
 */
public class InvalidBookException extends Exception {
    public InvalidBookException() {
        super("The Book is not available or It is not a Valid Book");
    }
}
