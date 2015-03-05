package com.biblioteca;
import com.biblioteca.Book;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by sanjanar on 25/02/15.
 */
public class BookTest {

    @Test
    public void compare2BooksAndReturnTrue() throws Exception {
        Book book1=new Book("S C J P","Kathy Serra",2006);
        Book book2=new Book("S C J P","Kathy Serra",2006);
        assertTrue(book1.equals(book2));
    }

    @Test
    public void compare2BooksAndReturnFalse() throws Exception {
        Book book1=new Book("S C J P","Kathy Serra",2006);
        Book book2=new Book("Let us C", "Yeshwanth", 2000);
        assertFalse(book1.equals(book2));
    }
}
