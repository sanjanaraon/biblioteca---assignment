package com.biblioteca;

/**
 * Created by sanjanar on 25/02/15.
 */
public class InvalidBookException extends Exception {
    public InvalidBookException() {
        super("The Book is not available to be checked out OR It is not a Valid Book to be returned");
    }
}
