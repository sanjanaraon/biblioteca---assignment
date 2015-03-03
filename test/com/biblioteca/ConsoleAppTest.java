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
    BibliotecaApp bibliotecaApp;


    @Before
    public void setUp() {
        testReaderWriter = new TestReaderWriter();
        consoleApp = new ConsoleApp(testReaderWriter);
        bibliotecaApp = new BibliotecaApp();

    }

    @Test
    public void shouldDisplayWelcomeMessage() throws Exception {
        consoleApp.printMessage(bibliotecaApp.displayWelcomeMessage());
        String expectedString = "Welcome to Biblioteca \n" +
                " The app to borrow and return the books to the library";
        assertEquals(expectedString, testReaderWriter.consoleOutput());
    }

    @Test
    public void shouldDisplayMainMenu() throws Exception {
        consoleApp.printMessage(bibliotecaApp.displayMainMenu());
        String expectedString = "Main menu \n" +
                " 1 ---- Book Details \n" +
                " 2 ---- Check Out a book\n" +
                " 3 ---- Return a book\n" +
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
        consoleApp.firstMessage();
        BibliotecaApp mockBiblioteca = mock(BibliotecaApp.class);
        String excepted = "Welcome to Biblioteca \n" +
                " The app to borrow and return the books to the library";
        when(mockBiblioteca.displayWelcomeMessage()).thenReturn("Welcome to Biblioteca \n" +
                " The app to borrow and return the books to the library");

        assertEquals(excepted, testReaderWriter.consoleOutput());

    }

    @Test
    public void shouldCallDisplayDetailsWhen1IsPassed() throws Exception {
        bibliotecaApp = mock(BibliotecaApp.class);

        consoleApp=new ConsoleApp(testReaderWriter,bibliotecaApp);
        when(bibliotecaApp.displayWelcomeMessage()).thenReturn("welcome ");
        when(bibliotecaApp.displayMainMenu()).thenReturn("Main menu ");
        when(bibliotecaApp.displayBookDetails()).thenReturn("Book Details ");
       // when(bibliotecaApp.displayBookDetails()).thenReturn("Books");
        testReaderWriter.consoleInput("1\n4");
        String expected = "welcome Main menu Books available for borrowingBook Details Main menu Successful Exit";
        consoleApp.mainMenu();
        assertEquals(expected, testReaderWriter.consoleOutput());

    }

    @Test
    public void shouldCallCheckOutBookMenuActionWhen2IsPassed() throws Exception {
        bibliotecaApp = mock(BibliotecaApp.class);
        consoleApp=new ConsoleApp(testReaderWriter,bibliotecaApp);

        when(bibliotecaApp.displayWelcomeMessage()).thenReturn("welcome ");
        when(bibliotecaApp.displayMainMenu()).thenReturn("Main menu ");

        when(bibliotecaApp.getBooks()).thenReturn("Book Details ");
        when(bibliotecaApp.validTitle("S C J P")).thenReturn(true);
        testReaderWriter.consoleInput("2\nS C J P\n4");

        String expected = "welcome Main menu Book Details Select a book by entering the titleMain menu Successful Exit";

        consoleApp.mainMenu();

        assertEquals(expected,testReaderWriter.consoleOutput());

    }

    @Test
    public void shouldCallReturnBookMenuActionWhen3IsPassed() throws Exception {
        bibliotecaApp = mock(BibliotecaApp.class);
        consoleApp=new ConsoleApp(testReaderWriter,bibliotecaApp);

        when(bibliotecaApp.displayWelcomeMessage()).thenReturn("welcome");
        when(bibliotecaApp.displayMainMenu()).thenReturn("main menu");
        when(bibliotecaApp.borrowedBooks()).thenReturn("Book Details");
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        when(bibliotecaApp.getBook("s c j p")).thenReturn(book1);

        when(bibliotecaApp.returnBookToLibrary(book1)).thenReturn(true);
        testReaderWriter.consoleInput("3\nS C J P\n4");
        String expected = "welcomemain menuBook DetailsSelect a book by entering the titlemain menuSuccessful Exit";
        consoleApp.mainMenu();
        assertEquals(expected,testReaderWriter.consoleOutput());

    }

    @Test
    public void shouldReturnWhen4IsPassed() throws Exception {
        testReaderWriter.consoleInput("4");
        consoleApp.mainMenu();
        assertEquals("Welcome to Biblioteca \n" +
                " The app to borrow and return the books to the libraryMain menu \n" +
                " 1 ---- Book Details \n" +
                " 2 ---- Check Out a book\n" +
                " 3 ---- Return a book\n" +
                " 4 ---- Exit\n" +
                "Enter your choiceSuccessful Exit",testReaderWriter.consoleOutput());
    }
}
