package com.twu.biblioteca;


import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class BibliotecaTest {

    @Test
    public void shouldDisplayWelcomeMessage() {
        String expectedMessage="Namaste Welcome to Biblioteca Application \n" +
                " The app to borrow book and return books";
        Assert.assertEquals(expectedMessage,BibliotecaApp.displayWelcomeMessage());
    }

    @Test
    public void shouldDisplayMainMenu() throws Exception {
        String expectedMessage="Main Menu \nEnter your choice \n 1)List Books";
        Assert.assertEquals(expectedMessage,BibliotecaApp.displayMainMenu());
    }
}
