package com.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by sanjanar on 27/02/15.
 */
public class ConsoleAppTest {
    TestReaderWriter testReaderWriter;
    ConsoleApp consoleApp;
    BibliotecaApp bookBibliotecaApp;
    BibliotecaApp movieBibliotecaApp;


    @Before
    public void setUp() {
        testReaderWriter = new TestReaderWriter();
        consoleApp = new ConsoleApp(testReaderWriter);
        bookBibliotecaApp = new BibliotecaApp(BibliotecaApp.createLibraryWithBooks());
        movieBibliotecaApp=new BibliotecaApp(BibliotecaApp.createLibraryWithMovies());

    }

    @Test
    public void shouldDisplayWelcomeMessageForBook() throws Exception {
        consoleApp.printMessage(bookBibliotecaApp.displayWelcomeMessage());
        String expectedString = "Welcome to Biblioteca \n" +
                " The app to borrow and return the books/movies to the library \n";
        assertEquals(expectedString, testReaderWriter.consoleOutput());
    }

    @Test
    public void shouldDisplayWelcomeMessageForMovie() throws Exception {
        String expectedString = "Welcome to Biblioteca \n" +
                " The app to borrow and return the books/movies to the library \n";
        consoleApp.printMessage(movieBibliotecaApp.displayWelcomeMessage());
        assertEquals(expectedString, testReaderWriter.consoleOutput());

    }

    @Test
    public void shouldDisplayMainMenu() throws Exception {
        consoleApp.printMessage(bookBibliotecaApp.displayMainMenu());
        String expectedString = "Main menu \n" +
                " 1 ---- Item Details \n" +
                " 2 ---- Check Out a Item\n" +
                " 3 ---- Return a Item\n" +
                " 4 ---- Exit\n" +
                "Enter your choice";
        assertEquals(expectedString, testReaderWriter.consoleOutput());
    }

    @Test
    public void shouldAcceptUsersChoice() throws Exception {
        testReaderWriter.consoleInput("12");
        String choice = consoleApp.acceptInput();
        assertEquals("12", choice);
    }


    @Test
    public void checkFirstMessagePrintedIsWelcomeMessage() throws Exception {
        BibliotecaApp mockBiblioteca = mock(BibliotecaApp.class);
        consoleApp=new ConsoleApp(testReaderWriter,mockBiblioteca);
        when(mockBiblioteca.displayWelcomeMessage()).thenReturn("Welcome to Biblioteca \n");
        String excepted = "Welcome to Biblioteca \n";

        consoleApp.firstMessage();

        assertEquals(excepted, testReaderWriter.consoleOutput());

    }

    @Test
    public void testForMainMenu() throws Exception {
        BibliotecaApp mockBiblioteca = mock(BibliotecaApp.class);
        consoleApp=new ConsoleApp(testReaderWriter,mockBiblioteca);
        when(mockBiblioteca.displayWelcomeMessage()).thenReturn("Welcome to Biblioteca ");
        String excepted = "Welcome to Biblioteca Which library you want to use book(0)/movie(1)/Exit(2)\n" +
                " Enter 0/1/2??nullSuccessful ExitWhich library you want to use book(0)/movie(1)/Exit(2)\n" +
                " Enter 0/1/2??";
        testReaderWriter.consoleInput("0\n4\n2");

        consoleApp.mainMenu();

        assertEquals(excepted, testReaderWriter.consoleOutput());
    }
}
