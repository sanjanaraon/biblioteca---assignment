package com.biblioteca.ui.controller;

import com.biblioteca.core.exceptions.InvalidItemException;
import com.biblioteca.core.models.*;
import com.biblioteca.ui.controller.LibraryManager;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by sanjanar on 26/02/15.
 */
public class LibraryManagerTest {
    LibraryManager bookLibraryManager;
     LibraryManager movieLibraryManager;
    UserInfo user;


    @Before
    public void setUp() {

            bookLibraryManager = new LibraryManager(LibraryManager.createLibraryWithBooks());
            movieLibraryManager = new LibraryManager(LibraryManager.createLibraryWithMovies());
        user = new UserInfo("lib-1000","user1","anu","anu@ymail.com","8921679023","user");

    }

    @Test
    public void shouldDisplayWelcomeMessageForBook() throws Exception {
        String expected = "Welcome to Biblioteca \n The libraryManager to borrow and return the books/movies to the library \n";
        assertEquals(expected, bookLibraryManager.displayWelcomeMessage());
    }

    @Test
    public void shouldDisplayWelcomeMessageForMovie() throws Exception {
        String expected = "Welcome to Biblioteca \n The libraryManager to borrow and return the books/movies to the library \n";
        assertEquals(expected, movieLibraryManager.displayWelcomeMessage());
    }

    @Test
    public void shouldDisplayMainMenuForBook() throws Exception {
        String expected = "Main menu \n" +
                " 1 ---- Item Details \n" +
                " 2 ---- Check Out a Item\n" +
                " 3 ---- Return a Item\n" +
                " 4 ---- Exit\n" +
                "Enter your choice";
        assertEquals(expected, bookLibraryManager.displayMainMenu());
    }

    @Test
    public void shouldDisplayMainMenuForMovie() throws Exception {
        String expected = "Main menu \n" +
                " 1 ---- Item Details \n" +
                " 2 ---- Check Out a Item\n" +
                " 3 ---- Return a Item\n" +
                " 4 ---- Exit\n" +
                "Enter your choice";
        assertEquals(expected, movieLibraryManager.displayMainMenu());
    }

    @Test
    public void shouldHaveAListOfBooksWhenTheAppIsInitialized() throws Exception {
        Library bookLibrary = new Library();
        List<Item> expectedBooks = bookLibrary.initializeBookList();
        assertEquals(expectedBooks, bookLibraryManager.getItemList());
    }

    @Test
    public void shouldHaveAListOfMoviesWhenTheAppIsInitialized() throws Exception {
        Library movieLibrary = new Library();
        List<Item> expectedBooks = movieLibrary.initializeMovieList();
        assertEquals(expectedBooks, movieLibraryManager.getItemList());
    }


    @Test
    public void shouldCheckoutABook() throws Exception {
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        Library mockLibrary = mock(Library.class);

        doNothing().when(mockLibrary).checkOutItem(book1, user);

        bookLibraryManager = new LibraryManager(mockLibrary);
        bookLibraryManager.checkOutFromLibrary(book1, user);

        verify(mockLibrary).checkOutItem(book1, user);
    }

    @Test
    public void shouldCheckoutAMovie() throws Exception {
        Movie movie1 = new Movie("Star wars",1977,"George Lucas","9");
        Library mockLibrary = mock(Library.class);

        doNothing().when(mockLibrary).checkOutItem(movie1, user);

        movieLibraryManager = new LibraryManager(mockLibrary);
        movieLibraryManager.checkOutFromLibrary(movie1, user);

        verify(mockLibrary).checkOutItem(movie1, user);
    }

    @Test(expected = InvalidItemException.class)
    public void shouldThrowExceptionWhenInvalidBookIsCheckedOut() throws Exception {
        Book book1 = new Book("S C Q A D", "Kathy Serra", 2006);
        Library mockLibrary = mock(Library.class);

        doThrow(new InvalidItemException()).when(mockLibrary).checkOutItem(book1, user);

        bookLibraryManager = new LibraryManager(mockLibrary);
        bookLibraryManager.checkOutFromLibrary(book1, user);

        verify(mockLibrary).checkOutItem(book1, user);
    }

    @Test(expected = InvalidItemException.class)
    public void shouldThrowExceptionWhenInvalidMovieIsCheckedOut() throws Exception {
        Movie movie1 = new Movie("Star wars",1977,"George Lucas","9");

        Library mockLibrary = mock(Library.class);

        doThrow(new InvalidItemException()).when(mockLibrary).checkOutItem(movie1, user);

        movieLibraryManager = new LibraryManager(mockLibrary);
        movieLibraryManager.checkOutFromLibrary(movie1, user);

        verify(mockLibrary).checkOutItem(movie1, user);
    }

    @Test
    public void shouldReturnBookBackToLibrary() throws Exception {
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        Library mockLibrary = mock(Library.class);

        doNothing().when(mockLibrary).returnItem(book1,user);

        bookLibraryManager = new LibraryManager(mockLibrary);
        bookLibraryManager.returnBookToLibrary(book1, user);

        verify(mockLibrary).returnItem(book1,user);
    }

    @Test
    public void shouldReturnMovieBackToLibrary() throws Exception {
        Movie movie1 = new Movie("Star wars",1977,"George Lucas","9");
        Library mockLibrary = mock(Library.class);

        doNothing().when(mockLibrary).returnItem(movie1,user);

        movieLibraryManager = new LibraryManager(mockLibrary);
        movieLibraryManager.returnBookToLibrary(movie1, user);

        verify(mockLibrary).returnItem(movie1,user);
    }

    @Test(expected = InvalidItemException.class)
    public void shouldThrowExceptionWhenInvalidBookIsReturned() throws Exception {
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        Library mocklibrary = mock(Library.class);

        doThrow(new InvalidItemException()).when(mocklibrary).returnItem(book1,user);

        bookLibraryManager = new LibraryManager(mocklibrary);
        bookLibraryManager.returnBookToLibrary(book1, user);

        verify(mocklibrary).returnItem(book1,user);
    }

    @Test(expected = InvalidItemException.class)
    public void shouldThrowExceptionWhenInvalidMovieIsReturned() throws Exception {
        Movie movie1 = new Movie("Star wars",1977,"George Lucas","9");

        Library mocklibrary = mock(Library.class);

        doThrow(new InvalidItemException()).when(mocklibrary).returnItem(movie1,user);

        movieLibraryManager = new LibraryManager(mocklibrary);
        movieLibraryManager.returnBookToLibrary(movie1, user);

        verify(mocklibrary).returnItem(movie1,user);
    }

    @Test
    public void returnABookWhenTitleIsPassed() throws Exception {
        assertEquals(new Book("S C J P", "Kathy Serra", 2006), bookLibraryManager.getItem("S c j P"));
    }

    @Test
    public void returnAMovieWhenTitleIsPassed() throws Exception {
        assertEquals( new Movie("Star wars",1977,"George Lucas","9"), movieLibraryManager.getItem("star wars"));
    }

    @Test
    public void shouldDisplayListOfBorrowedBooks() throws Exception {
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        bookLibraryManager.checkOutFromLibrary(book1, user);
        List<Item> list = bookLibraryManager.getItemListByCategory("book");
        assertEquals(Arrays.asList(book1), bookLibraryManager.borrowedItems(list));
    }

    @Test
    public void shouldDisplayListOfBorrowedMovies() throws Exception {
        Movie movie1 = new Movie("Star wars",1977,"George Lucas","9");
        movieLibraryManager.checkOutFromLibrary(movie1, user);
        List<Item> list=movieLibraryManager.getItemListByCategory("movie");
       assertEquals(Arrays.asList(movie1), movieLibraryManager.borrowedItems(list));
    }

    @Test
    public void shouldReturnTrueWhenAValidBookTitleIsPassed() throws Exception {
        assertTrue(bookLibraryManager.validTitle("s c j p"));
    }

    @Test
    public void shouldReturnTrueWhenAValidMovieTitleIsPassed() throws Exception {
        assertTrue(movieLibraryManager.validTitle("star wars"));
    }

    @Test
    public void shouldReturnFalseWhenInvalidBookTitleIsPassed() throws Exception {
        assertFalse(bookLibraryManager.validTitle("a b x"));
    }

    @Test
    public void shouldReturnFalseWhenInvalidMovieTitleIsPassed() throws Exception {
        assertFalse(movieLibraryManager.validTitle("a b x"));
    }

    @Test
    public void shouldDisplayListOfBooksWhenBookListIsPassed() throws Exception {
        List<Item> bookList= bookLibraryManager.getItemListByCategory("book");
        String excepted=displayItemsInString(bookList);
        assertEquals(excepted, bookLibraryManager.displaySpecificItemListDetails(bookList));
    }

    @Test
    public void shouldDisplayListOfMoviesWhenMovieListIsPassed() throws Exception {
        List<Item> movieList= movieLibraryManager.getItemListByCategory("movie");
        String excepted=displayItemsInString(movieList);
        assertEquals(excepted, bookLibraryManager.displaySpecificItemListDetails(movieList));
    }

    @Test
    public void shouldReturnBookListWhenCategoryIsSpecifiedAsBook() throws Exception {
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        Book book2 = new Book("Let us C", "Yeshwanth", 2000);
        Book book3 = new Book("The art of computer programming", "Donald ", 1968);
        Book book4 = new Book("Learning Python", "Mark Lutz", 1999);

        LibraryManager libraryManager =new LibraryManager();
        List<Item> bookList= libraryManager.getItemListByCategory("book");

        assertThat(bookList,  is(Arrays.<Item>asList(book1, book2, book3, book4)));
    }

    @Test
    public void shouldReturnMovieListWhenCategoryIsSpecifiedAsMovie() throws Exception {
        Movie movie1 = new Movie("Star wars",1977,"George Lucas","9");
        Movie movie2 = new Movie("The Terminator",1984,"James Cameron","8");
        Movie movie3=new Movie("Frozen",2013,"Jeniffer Lee","7");
        Movie movie4=new Movie("Brave",2012,"Mark Andrews","unrated");

        LibraryManager libraryManager =new LibraryManager();
        List<Item> movieList= libraryManager.getItemListByCategory("movie");

        assertThat(movieList,  is(Arrays.<Item>asList(movie1, movie2, movie3, movie4)));
    }


    private String displayItemsInString(List<Item> list) {
        String result="";
        for(Item i:list){
            result+=i+"\n";
        }
        return result;
    }

    @Test
    public void shouldDisplayListOfBooksInTableForm() throws Exception {
        List<Item> books=bookLibraryManager.getItemListByCategory("book");
        bookLibraryManager.displayItemDetailsInTableForm(books);
    }
}

