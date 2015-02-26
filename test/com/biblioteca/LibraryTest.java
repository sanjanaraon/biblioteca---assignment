package com.biblioteca;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Test;
import org.mockito.Mockito;

//import static org.hamcrest.MatcherAssert.assertThat;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.*;

/**
 * Created by sanjanar on 25/02/15.
 */
public class LibraryTest {

    @Test
    public void shouldHaveEmptyBookListUponCreation() throws Exception {
        Library library=new Library();
        assertEquals(0,library.getBooks().size());
    }

    @Test
    public void shouldABookToListOfBooks() throws Exception {
        Library library = new Library();
        Book book = new Book("S C J P", "Kathy Serra", 2006);
        library.addBook(book);
        assertThat(library.getBooks(), hasItem(book));
    }

    @Test
    public void shouldRemoveBookFromListWhenItIsCheckedOut() throws Exception {
        Library library = new Library();
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        library.addBook(book1);
        Book book2 = new Book("Let us C", "Yeshwanth", 2000);
        library.addBook(book2);
        library.checkOut(book1);
        assertTrue(book1.isCheckedOut());
        assertFalse(book2.isCheckedOut());
    }

    @Test(expected = InvalidBookException.class)
    public void shouldFailToRemoveBookWhenThereIsSomeMistakeInBookDetails()  throws Exception{
        Library library = new Library();
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        library.addBook(book1);
        Book book2 = new Book("Let us C", "Yeshwanth", 2000);
        library.addBook(book2);
        Book book3=new Book("S C Q T", "Kathy Serra", 2006);
        library.checkOut(book3);
    }

    @Test(expected = InvalidBookException.class)
    public void shouldFailToRemoveBookWhenTheBookIsAlreadyCheckedOut()  throws Exception{
        Library library = new Library();
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        library.addBook(book1);
        book1.setCheckedOut(true);
        Book book2 = new Book("Let us C", "Yeshwanth", 2000);
        library.addBook(book2);
        library.checkOut(book1);
    }

    @Test
    public void shouldAddTheBookReturnedToBooksList() throws Exception {
        Library library=new Library();
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        library.addBook(book1);
        Book book2 = new Book("Let us C", "Yeshwanth", 2000);
        library.addBook(book2);
        book1.setCheckedOut(true);
        library.returnBook(book1);
    }

    @Test(expected = InvalidBookException.class)
    public void shouldThrowAExceptionWhenInvalidBookIsAddedToBooksList() throws Exception {
        Library library=new Library();
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        library.addBook(book1);
        Book book2 = new Book("Let us C", "Yeshwanth", 2000);
        library.addBook(book2);
        library.returnBook(new Book("S C J P", "Kathy ", 2010));
    }

    @Test(expected = InvalidBookException.class)
    public void shouldThrowExceptionWhenUnCheckedBookIsAddedToBookList() throws Exception {
        Library library=new Library();
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        library.addBook(book1);
        Book book2 = new Book("Let us C", "Yeshwanth", 2000);
        library.addBook(book2);
        library.returnBook(book1);
    }

    @Test
    public void shouldReturnListOfAvailableBooksToBeCheckedOut() throws Exception {
        Library library = new Library();
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        Book book2 = new Book("Let us C", "Yeshwanth", 2000);
        Book book3 = new Book("The art of computer programming", "Donald ", 1968);
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        assertThat(library.getAvailableBooks(), is(asList(book1,book2,book3)));
        library.checkOut(book2);
        assertThat(library.getAvailableBooks(), is(asList(book1, book3)));
    }
}
