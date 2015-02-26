package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanjanar on 25/02/15.
 */
public class Library {
    List<Book> books = new ArrayList<Book>();

    public Library() {

    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void checkOut(Book book) throws InvalidBookException {
        if (books.contains(book) && book.isCheckedOut() == false)
            resetCheckOut(book, true);
        else
            throw new InvalidBookException();

    }

    public void returnBook(Book book) throws InvalidBookException {
        if (books.contains(book) && book.isCheckedOut() == true)
            resetCheckOut(book, false);
        else
            throw new InvalidBookException();
    }

    private void resetCheckOut(Book book, boolean flag) {
        for (Book b : books) {
            if (b == book) {
                b.setCheckedOut(flag);
            }
        }
    }
}
