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
        BibliotecaApp bibliotecaApp=new BibliotecaApp();
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        bibliotecaApp.checkOutFromLibrary(book1);
    }
}
