package com.biblioteca;

import com.biblioteca.core.exceptions.InvalidItemException;
import com.biblioteca.core.models.Book;
import com.biblioteca.core.models.Item;
import com.biblioteca.core.models.Library;
import com.biblioteca.core.models.Movie;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

//import static org.hamcrest.MatcherAssert.*;

/**
 * Created by sanjanar on 25/02/15.
 */
public class LibraryTest {
    Library library;
    //Library library1;

    @Before
    public void setUp(){
        this.library = new Library();
     //   library1 = BibliotecaApp.createLibraryWithMovies();
    }

    @Test
    public void shouldHaveBookListAndMovieListUponCreation() throws Exception {
        assertEquals(8, library.getItems().size());
    }

    @Test
    public void shouldAddOneBookItemToExistingListOfItems() throws Exception {
        library.itemList.add(new Book("S c J p","Kathy Serra",2006));
        assertEquals(9, library.getItems().size());
    }

    @Test
    public void shouldAddOneMovieItemToExistingListOfItems() throws Exception {
        library.itemList.add(new Movie("Star wars",1977,"George Lucas","9"));
        assertEquals(9, library.getItems().size());
    }



    @Test(expected = InvalidItemException.class)
    public void shouldFailToRemoveBookWhenTheBookDoesNotExistInLibrary()  throws Exception{
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        library.addItem(book1);
        Book book2 = new Book("Let us C", "Yeshwanth", 2000);
        library.addItem(book2);
        Book book3=new Book("S C Q T", "Kathy Serra", 2006);
        library.checkOutItem(book3);
    }

    @Test(expected = InvalidItemException.class)
    public void shouldFailToRemoveMovieWhenTheMovieDoesNotExistInLibrary()  throws Exception{
        Movie movie1 = new Movie("Star wars",1977,"George Lucas","9");
        library.addItem(movie1);
        Movie movie2 = new Movie("The Terminator",1984,"James Cameron","8");
        library.addItem(movie2);
        Movie movie3=new Movie("Stars aaa",1977,"George Lucas","9");
        library.checkOutItem(movie3);
    }

    @Test(expected = InvalidItemException.class)
    public void shouldFailToRemoveBookWhenTheBookIsAlreadyCheckedOut()  throws Exception{
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        library.addItem(book1);
        book1.setCheckedOut(true);
        library.checkOutItem(book1);
    }

    @Test(expected = InvalidItemException.class)
    public void shouldFailToRemoveMovieWhenTheBookIsAlreadyCheckedOut()  throws Exception{
        Movie movie1 = new Movie("Star wars",1977,"George Lucas","9");
        library.addItem(movie1);
        movie1.setCheckedOut(true);
        library.checkOutItem(movie1);
    }

    @Test
    public void shouldAddTheBookReturnedToBookList() throws Exception {
        Book book2 = new Book("Let us C", "Yeshwanth", 2000);
      //  library.addItem(book2);
        book2.setCheckedOut(true);
        library.returnItem(book2);
        assertEquals(library.getItems(), library.getAvailableItems());

    }

    @Test
    public void shouldAddTheMovieReturnedToMovieList() throws Exception {
        Movie movie1 = new Movie("Star wars",1977,"George Lucas","9");
        movie1.setCheckedOut(true);
        library.returnItem(movie1);
        assertEquals(library.getItems(),library.getAvailableItems());

    }



//    @Test
//    public void shouldReturnListOfAvailableItemsToBeCheckedOut() throws Exception {
//        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
//        Book book2 = new Book("Let us C", "Yeshwanth", 2000);
//        Book book3 = new Book("The art of computer programming", "Donald ", 1968);
//        Book book4=new Book("Learning Python", "Mark Lutz", 1999);
//        Movie movie1 = new Movie("Star wars",1977,"George Lucas","9");
//        Movie movie2 = new Movie("The Terminator",1984,"James Cameron","8");
//        Movie movie3=new Movie("Frozen",2013,"Jeniffer Lee","7");
//        Movie movie4=new Movie("Brave",2012,"Mark Andrews","unrated");
//        assertThat(library.getAvailableItems(), is(library.itemList));
//        library.checkOutItem(book2);
//        assertThat(library.getAvailableItems(), is(asList(book1, book3, book4, movie1, movie2, movie3, movie4)));
//    }

//    @Test
//    public void shouldReturnListOfAvailableMoviesToBeCheckedOut() throws Exception {
//        Movie movie1 = new Movie("Star wars",1977,"George Lucas","9");
//        Movie movie2 = new Movie("The Terminator",1984,"James Cameron","8");
//        Movie movie3=new Movie("Frozen",2013,"Jeniffer Lee","7");
//        Movie movie4=new Movie("Brave",2012,"Mark Andrews","unrated");
//        assertThat(library.getAvailableItems(), is(Arrays.<Item>asList(movie1, movie2, movie3,movie4)));
//        library.checkOutItem(movie2);
//        assertThat(library.getAvailableItems(), is(Arrays.<Item>asList(movie1, movie3, movie4)));
//    }

    @Test
    public void shouldReturnListOfBorrowedBooksToBeReturned() throws Exception {
        List<Book> books=new ArrayList<Book>();
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        books.add(book1);
        Book book2 = new Book("Let us C", "Yeshwanth", 2000);
        books.add(book2);

        //List<Item> expected=getTempList();
        //assertEquals(expected,library.getBorrowedItems());
        assertThat(library.getBorrowedItems(), is(Arrays.<Item>asList()));
      //  book2.setCheckedOut(true);
        library.checkOutItem(book2);
       // List<Item> expected=getTempList(book2);
        assertThat(library.getBorrowedItems(), is(Arrays.<Item>asList(book2)));
       // assertEquals(expected,library.getBorrowedItems(books));
    }

    @Test
    public void shouldReturnListOfBorrowedMoviesToBeReturned() throws Exception {
        Movie movie2 = new Movie("The Terminator",1984,"James Cameron","8");
        List<Item> expected=getTempList();
        assertEquals(expected, library.getBorrowedItems());
        //assertThat(library.getBorrowedItems(), is(Arrays.<Item>asList()));
        library.checkOutItem(movie2);
        expected=getTempList(movie2);
       // assertThat(library.getBorrowedItems(), is(Arrays.<Item>asList(book2)));
        assertEquals(expected, library.getBorrowedItems());
    }

    private List<Item> getTempList(Item ... items ) {
        List<Item> list=new ArrayList<Item>();
        for(Item item:items){
            list.add(item);
        }
        return list;
    }


    @Test
    public void shouldReturnListOfAvailableItemsWhenNoItemIsCheckedOut() throws Exception {
        assertEquals(library.getItems(),library.getAvailableItems());
    }

    @Test
    public void shouldReturnListOfAvailableItemsWhenAItemIsCheckedOut() throws Exception {
        Movie movie1 = new Movie("Star wars",1977,"George Lucas","9");
        library.checkOutItem(movie1);
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        Book book2 = new Book("Let us C", "Yeshwanth", 2000);
        Book book3 = new Book("The art of computer programming", "Donald ", 1968);
        Book book4=new Book("Learning Python", "Mark Lutz", 1999);
        Movie movie2 = new Movie("The Terminator",1984,"James Cameron","8");
        Movie movie3=new Movie("Frozen",2013,"Jeniffer Lee","7");
        Movie movie4=new Movie("Brave",2012,"Mark Andrews","unrated");
        assertThat(library.getAvailableItems(),is(Arrays.asList(book1,book2,book3,book4,movie2,movie3,movie4)));
    }
}
