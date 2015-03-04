package com.biblioteca;

/**
 * Created by sanjanar on 25/02/15.
 */
public class InvalidItemException extends Exception {
    public InvalidItemException() {
        super("The Item is not available to be checked out OR It is not a Valid Item to be returned");
    }
}
