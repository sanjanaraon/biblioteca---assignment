package com.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
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
        String expected = "Main menu \n 1 ---- Book Details \n 2 ---- Exit\nEnter your choice";
        assertEquals(expected, bibliotecaApp.displayMainMenu());
    }

    @Test
    public void shouldHaveAListOfBooksWhenTheAppIsInitialized() throws Exception {
        Library library = new Library();
        List<Book> expectedBooks = library.initializeBookList();
        assertEquals(expectedBooks, bibliotecaApp.getBooks());
    }

    @Test
    public void shouldDisplayTheListOfBooksInTableForm() throws Exception {
        String expected = bookListInTable(bibliotecaApp.getBooks());
        assertEquals(expected, bibliotecaApp.displayBookDetails());
    }

    private String bookListInTable(List<Book> books) {
        String result = " ";
        for (Book b : books) {
            if (b.isCheckedOut() == false)
                result+= b.getTitle()+"                                                  |"+b.getAuthor()+"                |"+b.getYear()+"\n";
        }
        return result;
    }

    @Test
    public void shouldCheckoutABook() throws Exception {
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        Library mockLibrary = mock(Library.class);

        doNothing().when(mockLibrary).checkOut(book1);

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

        doNothing().when(mockLibrary).returnBook(book1);

        bibliotecaApp = new BibliotecaApp(mockLibrary);
        bibliotecaApp.returnBookFromLibrary(book1);

        verify(mockLibrary).returnBook(book1);
    }

    @Test(expected = InvalidBookException.class)
    public void shouldThrowExceptionWhenInvalidBookIsReturned() throws Exception {
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        Library mocklibrary=mock(Library.class);

        doThrow(new InvalidBookException()).when(mocklibrary).returnBook(book1);

        bibliotecaApp=new BibliotecaApp(mocklibrary);
        bibliotecaApp.returnBookFromLibrary(book1);

        verify(mocklibrary).returnBook(book1);
    }

    @Test
    public void returnABookWhenTitleIsPassed() throws Exception {
        assertEquals(new Book("S C J P", "Kathy Serra", 2006),bibliotecaApp.getBook("S C J p"));
    }

    @Test
    public void shouldDisplayListOfBorrowedBooks() throws Exception {
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        String expected = " "+book1.getTitle()+"                                                  |"+book1.getAuthor()+"                |"+book1.getYear()+"\n";
        bibliotecaApp.checkOutFromLibrary(book1);
        assertEquals(expected, bibliotecaApp.borrowedBooks());
    }
}
