package com.biblioteca.ui.controller;

import com.biblioteca.TestReaderWriter;
import com.biblioteca.ui.controller.ConsoleApp;
import com.biblioteca.ui.controller.LibraryManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by sanjanar on 27/02/15.
 */
public class ConsoleAppTest {
    TestReaderWriter testReaderWriter;
    ConsoleApp consoleApp;
    LibraryManager bookLibraryManager;
    LibraryManager movieLibraryManager;


    @Before
    public void setUp() {
        testReaderWriter = new TestReaderWriter();
        consoleApp = new ConsoleApp(testReaderWriter);
        bookLibraryManager = new LibraryManager(LibraryManager.createLibraryWithBooks());
        movieLibraryManager =new LibraryManager(LibraryManager.createLibraryWithMovies());

    }

    @Test
    public void shouldDisplayWelcomeMessageForBook() throws Exception {
        consoleApp.printMessage(bookLibraryManager.displayWelcomeMessage());
        String expectedString = "Welcome to Biblioteca \n" +
                " The libraryManager to borrow and return the books/movies to the library \n";
        assertEquals(expectedString, testReaderWriter.consoleOutput());
    }

    @Test
    public void shouldDisplayWelcomeMessageForMovie() throws Exception {
        String expectedString = "Welcome to Biblioteca \n" +
                " The libraryManager to borrow and return the books/movies to the library \n";
        consoleApp.printMessage(movieLibraryManager.displayWelcomeMessage());
        assertEquals(expectedString, testReaderWriter.consoleOutput());

    }

    @Test
    public void shouldDisplayMainMenu() throws Exception {
        consoleApp.printMessage(bookLibraryManager.displayMainMenu());
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
        LibraryManager mockBiblioteca = mock(LibraryManager.class);
        consoleApp=new ConsoleApp(testReaderWriter,mockBiblioteca);
        when(mockBiblioteca.displayWelcomeMessage()).thenReturn("Welcome to Biblioteca \n");
        String excepted = "Welcome to Biblioteca \n";

        consoleApp.firstMessage();

        assertEquals(excepted, testReaderWriter.consoleOutput());

    }

//    @Test
//    public void testForMainMenu() throws Exception {
//        LibraryManager mockBiblioteca = mock(LibraryManager.class);
//        consoleApp=new ConsoleApp(testReaderWriter,mockBiblioteca);
//        when(mockBiblioteca.displayWelcomeMessage()).thenReturn("Welcome to Biblioteca ");
//        when(mockBiblioteca.displayMainMenu()).thenReturn("main menu ");
//        String excepted = "Welcome to Biblioteca Which library you want to use book(0)/movie(1)/Exit(2)\n" +
//                " Enter 0/1/2??main menu Successful ExitWhich library you want to use book(0)/movie(1)/Exit(2)\n" +
//                " Enter 0/1/2??";
//        testReaderWriter.consoleInput("0\n4\n2");
//
//        consoleApp.mainMenu();
//
//        assertEquals(excepted, testReaderWriter.consoleOutput());
//    }
}
