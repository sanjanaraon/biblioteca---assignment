package com.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

/**
 * Created by sanjanar on 26/02/15.
 */
public class BibliotecaAppTest {
    BibliotecaApp bibliotecaApp;

    @Before
    public void setUp(){
        bibliotecaApp = new BibliotecaApp();
    }
    @Test
    public void shouldDisplayWelcomeMessage() throws Exception {
        String expected = "Welcome to Biblioteca \n The app to borrow and return the books to the library";
        assertEquals(expected, bibliotecaApp.displayWelcomeMessage());
    }

    @Test
    public void shouldDisplayMainMenu() throws Exception {
        String expected = "Main menu \n" +
                " 1 ---- Book Details \n" +
                " 2 ---- Check Out a book\n" +
                " 3 ---- Return a book\n" +
                " 4 ---- Exit\n"+
                "Enter your choice";
        assertEquals(expected, bibliotecaApp.displayMainMenu());
    }

    @Test
    public void shouldHaveAListOfBooksWhenTheAppIsInitialized() throws Exception {
        Library library = new Library();
        List<Book> expectedBooks = library.initializeBookList();
        assertEquals(expectedBooks, bibliotecaApp.getBooksList());
    }

    @Test
    public void shouldDisplayTheListOfBooksInTableForm() throws Exception {
        String expected = bibliotecaApp.getBooks();
        assertEquals(expected, bibliotecaApp.displayBookDetails());
    }



    @Test
    public void shouldCheckoutABook() throws Exception {
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        Library mockLibrary = mock(Library.class);

       when(mockLibrary.checkOut(book1)).thenReturn(true);

        bibliotecaApp = new BibliotecaApp(mockLibrary);
        bibliotecaApp.checkOutFromLibrary(book1);

        verify(mockLibrary).checkOut(book1);
    }

    @Test(expected = InvalidBookException.class)
    public void shouldThrowExceptionWhenInvalidBookIsCheckedOut() throws Exception {
        Book book1 = new Book("S C Q A D", "Kathy Serra", 2006);
        Library mockLibrary = mock(Library.class);

        doThrow(new InvalidBookException()).when(mockLibrary).checkOut(book1);

        bibliotecaApp = new BibliotecaApp(mockLibrary);
        bibliotecaApp.checkOutFromLibrary(book1);

        verify(mockLibrary).checkOut(book1);
    }

    @Test
    public void shouldReturnBookBackToLibrary() throws Exception {
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        Library mockLibrary = mock(Library.class);

        when(mockLibrary.returnBook(book1)).thenReturn(true);

        bibliotecaApp = new BibliotecaApp(mockLibrary);
        bibliotecaApp.returnBookToLibrary(book1);

        verify(mockLibrary).returnBook(book1);
    }

    @Test(expected = InvalidBookException.class)
    public void shouldThrowExceptionWhenInvalidBookIsReturned() throws Exception {
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        Library mocklibrary=mock(Library.class);

        doThrow(new InvalidBookException()).when(mocklibrary).returnBook(book1);

        bibliotecaApp=new BibliotecaApp(mocklibrary);
        bibliotecaApp.returnBookToLibrary(book1);

        verify(mocklibrary).returnBook(book1);
    }

    @Test
    public void returnABookWhenTitleIsPassed() throws Exception {
        assertEquals(new Book("S C J P", "Kathy Serra", 2006),bibliotecaApp.getBook("S C J p"));
    }

    @Test
    public void shouldDisplayListOfBorrowedBooks() throws Exception {
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        bibliotecaApp.checkOutFromLibrary(book1);
        assertEquals(book1.toString(), bibliotecaApp.borrowedBooks());
    }

    @Test
    public void shouldReturnTrueWhenAValidBookIsPassed() throws Exception {
        assertTrue(bibliotecaApp.validTitle("s c j p"));
    }

    @Test
    public void shouldReturnFalseWhenInvalidBookIsPassed() throws Exception {
        assertFalse(bibliotecaApp.validTitle("s a c d"));
    }

}
