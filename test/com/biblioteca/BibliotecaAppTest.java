package com.biblioteca;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    public void shouldDisplayTheListOfBooksInTableForm() throws Exception {
        BibliotecaApp bibliotecaApp=new BibliotecaApp();
        String expected=bookListInTable(bibliotecaApp.getBooks());
        assertEquals(expected,bibliotecaApp.displayBookDetails());
    }

    private String bookListInTable(List<Book> books) {
        String result=" ";
        for (Book b:books){
            if(b.isCheckedOut()==false)
            result+= b.getTitle()+"    |"+b.getAuthor()+"   |"+b.getYear()+"\n";
        }
        return result;
    }

    @Test
    public void shouldCheckoutABook() throws Exception {
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        Library mockLibrary = mock(Library.class);

        doNothing().when(mockLibrary).checkOut(book1);

        BibliotecaApp bibliotecaApp = new BibliotecaApp(mockLibrary);
        bibliotecaApp.checkOutFromLibrary(book1);

        verify(mockLibrary).checkOut(book1);
    }
}
