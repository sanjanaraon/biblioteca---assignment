package com.biblioteca;
import com.biblioteca.Book;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by sanjanar on 25/02/15.
 */
public class BookTest {
    @Test
    public void shouldPrintBookDetails() throws Exception {
        Book book=new Book("S C J P","Kathy Serra",2006);
        assertEquals(new Book("S C J P","Kathy Serra",2006).toString() ,book.toString());
    }
}
