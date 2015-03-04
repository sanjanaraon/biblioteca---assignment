package com.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by sanjanar on 26/02/15.
 */
public class BibliotecaAppTest {
    BibliotecaApp bookBibliotecaApp;
     BibliotecaApp movieBibliotecaApp;

    @Before
    public void setUp() {

            bookBibliotecaApp = new BibliotecaApp(BibliotecaApp.createLibraryWithBooks());
            movieBibliotecaApp = new BibliotecaApp(BibliotecaApp.createLibraryWithMovies());

    }

    @Test
    public void shouldDisplayWelcomeMessageForBook() throws Exception {
        String expected = "Welcome to Biblioteca \n The app to borrow and return the books/movies to the library \n";
        assertEquals(expected, bookBibliotecaApp.displayWelcomeMessage());
    }

    @Test
    public void shouldDisplayWelcomeMessageForMovie() throws Exception {
        String expected = "Welcome to Biblioteca \n The app to borrow and return the books/movies to the library \n";
        assertEquals(expected, movieBibliotecaApp.displayWelcomeMessage());
    }

    @Test
    public void shouldDisplayMainMenuForBook() throws Exception {
        String expected = "Main menu \n" +
                " 1 ---- Item Details \n" +
                " 2 ---- Check Out a Item\n" +
                " 3 ---- Return a Item\n" +
                " 4 ---- Exit\n" +
                "Enter your choice";
        assertEquals(expected, bookBibliotecaApp.displayMainMenu());
    }

    @Test
    public void shouldDisplayMainMenuForMovie() throws Exception {
        String expected = "Main menu \n" +
                " 1 ---- Item Details \n" +
                " 2 ---- Check Out a Item\n" +
                " 3 ---- Return a Item\n" +
                " 4 ---- Exit\n" +
                "Enter your choice";
        assertEquals(expected, movieBibliotecaApp.displayMainMenu());
    }

    @Test
    public void shouldHaveAListOfBooksWhenTheAppIsInitialized() throws Exception {
        Library bookLibrary = new Library();
        List<Item> expectedBooks = bookLibrary.initializeBookList();
        assertEquals(expectedBooks, bookBibliotecaApp.getItemList());
    }

    @Test
    public void shouldHaveAListOfMoviesWhenTheAppIsInitialized() throws Exception {
        Library movieLibrary = new Library();
        List<Item> expectedBooks = movieLibrary.initializeMovieList();
        assertEquals(expectedBooks, movieBibliotecaApp.getItemList());
    }

    @Test
    public void shouldDisplayTheListOfBooksInTableForm() throws Exception {
        String expected = bookBibliotecaApp.getItems();
        assertEquals(expected, bookBibliotecaApp.displayItemDetails());
    }

    @Test
    public void shouldDisplayTheListOfMoviesInTableForm() throws Exception {
        String expected = movieBibliotecaApp.getItems();
        assertEquals(expected, movieBibliotecaApp.displayItemDetails());
    }

    @Test
    public void shouldCheckoutABook() throws Exception {
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        Library mockLibrary = mock(Library.class);

        when(mockLibrary.checkOutItem(book1)).thenReturn(true);

        bookBibliotecaApp = new BibliotecaApp(mockLibrary);
        bookBibliotecaApp.checkOutFromLibrary(book1);

        verify(mockLibrary).checkOutItem(book1);
    }

    @Test
    public void shouldCheckoutAMovie() throws Exception {
        Movie movie1 = new Movie("Star wars",1977,"George Lucas","9");
        Library mockLibrary = mock(Library.class);

        when(mockLibrary.checkOutItem(movie1)).thenReturn(true);

        movieBibliotecaApp = new BibliotecaApp(mockLibrary);
        movieBibliotecaApp.checkOutFromLibrary(movie1);

        verify(mockLibrary).checkOutItem(movie1);
    }

    @Test(expected = InvalidItemException.class)
    public void shouldThrowExceptionWhenInvalidBookIsCheckedOut() throws Exception {
        Book book1 = new Book("S C Q A D", "Kathy Serra", 2006);
        Library mockLibrary = mock(Library.class);

        doThrow(new InvalidItemException()).when(mockLibrary).checkOutItem(book1);

        bookBibliotecaApp = new BibliotecaApp(mockLibrary);
        bookBibliotecaApp.checkOutFromLibrary(book1);

        verify(mockLibrary).checkOutItem(book1);
    }

    @Test(expected = InvalidItemException.class)
    public void shouldThrowExceptionWhenInvalidMovieIsCheckedOut() throws Exception {
        Movie movie1 = new Movie("Star wars",1977,"George Lucas","9");

        Library mockLibrary = mock(Library.class);

        doThrow(new InvalidItemException()).when(mockLibrary).checkOutItem(movie1);

        movieBibliotecaApp = new BibliotecaApp(mockLibrary);
        movieBibliotecaApp.checkOutFromLibrary(movie1);

        verify(mockLibrary).checkOutItem(movie1);
    }

    @Test
    public void shouldReturnBookBackToLibrary() throws Exception {
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        Library mockLibrary = mock(Library.class);

        when(mockLibrary.returnItem(book1)).thenReturn(true);

        bookBibliotecaApp = new BibliotecaApp(mockLibrary);
        bookBibliotecaApp.returnBookToLibrary(book1);

        verify(mockLibrary).returnItem(book1);
    }

    @Test
    public void shouldReturnMovieBackToLibrary() throws Exception {
        Movie movie1 = new Movie("Star wars",1977,"George Lucas","9");
        Library mockLibrary = mock(Library.class);

        when(mockLibrary.returnItem(movie1)).thenReturn(true);

        movieBibliotecaApp = new BibliotecaApp(mockLibrary);
        movieBibliotecaApp.returnBookToLibrary(movie1);

        verify(mockLibrary).returnItem(movie1);
    }

    @Test(expected = InvalidItemException.class)
    public void shouldThrowExceptionWhenInvalidBookIsReturned() throws Exception {
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        Library mocklibrary = mock(Library.class);

        doThrow(new InvalidItemException()).when(mocklibrary).returnItem(book1);

        bookBibliotecaApp = new BibliotecaApp(mocklibrary);
        bookBibliotecaApp.returnBookToLibrary(book1);

        verify(mocklibrary).returnItem(book1);
    }

    @Test(expected = InvalidItemException.class)
    public void shouldThrowExceptionWhenInvalidMovieIsReturned() throws Exception {
        Movie movie1 = new Movie("Star wars",1977,"George Lucas","9");

        Library mocklibrary = mock(Library.class);

        doThrow(new InvalidItemException()).when(mocklibrary).returnItem(movie1);

        movieBibliotecaApp = new BibliotecaApp(mocklibrary);
        movieBibliotecaApp.returnBookToLibrary(movie1);

        verify(mocklibrary).returnItem(movie1);
    }

    @Test
    public void returnABookWhenTitleIsPassed() throws Exception {
        assertEquals(new Book("S C J P", "Kathy Serra", 2006), bookBibliotecaApp.getItem("S c j P"));
    }

    @Test
    public void returnAMovieWhenTitleIsPassed() throws Exception {
        assertEquals( new Movie("Star wars",1977,"George Lucas","9"), movieBibliotecaApp.getItem("star wars"));
    }

    @Test
    public void shouldDisplayListOfBorrowedBooks() throws Exception {
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        bookBibliotecaApp.checkOutFromLibrary(book1);
        assertEquals(book1.toString(), bookBibliotecaApp.borrowedItems());
    }

    @Test
    public void shouldDisplayListOfBorrowedMovies() throws Exception {
        Movie movie1 = new Movie("Star wars",1977,"George Lucas","9");
        movieBibliotecaApp.checkOutFromLibrary(movie1);
        assertEquals(movie1.toString(), movieBibliotecaApp.borrowedItems());
    }

    @Test
    public void shouldReturnTrueWhenAValidBookTitleIsPassed() throws Exception {
        assertTrue(bookBibliotecaApp.validTitle("s c j p"));
    }

    @Test
    public void shouldReturnTrueWhenAValidMovieTitleIsPassed() throws Exception {
        assertTrue(movieBibliotecaApp.validTitle("star wars"));
    }

    @Test
    public void shouldReturnFalseWhenInvalidBookTitleIsPassed() throws Exception {
        assertFalse(bookBibliotecaApp.validTitle("a b x"));
    }

    @Test
    public void shouldReturnFalseWhenInvalidMovieTitleIsPassed() throws Exception {
        assertFalse(movieBibliotecaApp.validTitle("a b x"));
    }


    @Test
    public void shouldReturnBookListWhenCategoryIsSpecifiedAsBook() throws Exception {
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        Book book2 = new Book("Let us C", "Yeshwanth", 2000);
        Book book3 = new Book("The art of computer programming", "Donald ", 1968);
        Book book4 = new Book("Learning Python", "Mark Lutz", 1999);

        BibliotecaApp bibliotecaApp=new BibliotecaApp();
        List<Item> bookList=bibliotecaApp.getItemListByCategory("book");

        assertThat(bookList,  is(Arrays.<Item>asList(book1, book2, book3, book4)));
    }

    @Test
    public void shouldReturnMovieListWhenCategoryIsSpecifiedAsMovie() throws Exception {
        Movie movie1 = new Movie("Star wars",1977,"George Lucas","9");
        Movie movie2 = new Movie("The Terminator",1984,"James Cameron","8");
        Movie movie3=new Movie("Frozen",2013,"Jeniffer Lee","7");
        Movie movie4=new Movie("Brave",2012,"Mark Andrews","unrated");

        BibliotecaApp bibliotecaApp=new BibliotecaApp();
        List<Item> movieList=bibliotecaApp.getItemListByCategory("movie");

        assertThat(movieList,  is(Arrays.<Item>asList(movie1, movie2, movie3, movie4)));
    }

    @Test
    public void shouldDisplayListOfBooksWhenBookListIsPassed() throws Exception {
        List<Item> bookList=bookBibliotecaApp.getItemListByCategory("book");
        String excepted=displayItemsInString(bookList);
        assertEquals(excepted,bookBibliotecaApp.displaySpecificItemListDetails(bookList));
    }

    @Test
    public void shouldDisplayListOfMoviesWhenMovieListIsPassed() throws Exception {
        List<Item> movieList=movieBibliotecaApp.getItemListByCategory("movie");
        String excepted=displayItemsInString(movieList);
        assertEquals(excepted,bookBibliotecaApp.displaySpecificItemListDetails(movieList));
    }

    private String displayItemsInString(List<Item> list) {
        String result="";
        for(Item i:list){
            result+=i+"\n";
        }
        return result;
    }
}

