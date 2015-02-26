package com.biblioteca;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by sanjanar on 26/02/15.
 */
public class BibliotecaAppTest {
    @Test
    public void shouldDisplayWelcomeMessage() throws Exception {
        BibliotecaApp bibliotecaApp =new BibliotecaApp();
        String expected= "Welcome to Biblioteca \n The app to borrow and return the books to the library";
        assertEquals(expected, bibliotecaApp.displayMessage());
    }

    @Test
    public void shouldDisplayMainMenu() throws Exception {
        BibliotecaApp bibliotecaApp =new BibliotecaApp();
        String expected="Main menu \n 1 ---- Book Details \n 2 ---- Exit";
        assertEquals(expected, bibliotecaApp.displayMainMenu());
    }

    @Test
    public void shouldHaveAListOfBooksWhenTheAppIsInitialized() throws Exception {
        BibliotecaApp bibliotecaApp=new BibliotecaApp();
        Library library=new Library();
        List<Book> expectedBooks=library.initializeBookList();
        assertEquals(expectedBooks, bibliotecaApp.getBooks());
    }
}
