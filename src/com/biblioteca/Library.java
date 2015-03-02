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

    public boolean checkOut(Book book) throws InvalidBookException {
        if (books.contains(book) && book.isCheckedOut() == false) {
            resetCheckOut(book, true);
            return true;
        }
        else
            throw new InvalidBookException();

    }

    public boolean returnBook(Book book) throws InvalidBookException {
        if (books.contains(book) && book.isCheckedOut() == true){
            resetCheckOut(book, false);
        return true;
        }
        else
            throw new InvalidBookException();
    }

    private void resetCheckOut(Book book, boolean flag) {
        for (Book b : books) {
            if (b.equals(book)) {
                b.setCheckedOut(flag);
                break;
            }
        }
    }

    public List<Book> initializeBookList() {
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        addBook(book1);
        Book book2 = new Book("Let us C", "Yeshwanth", 2000);
        addBook(book2);
        Book book3 = new Book("The art of computer programming", "Donald ", 1968);
       addBook(book3);
        Book book4 = new Book("Learning Python", "Mark Lutz", 1999);
        addBook(book4);
        return books;
    }


    public List<Book> getAvailableBooks() {
        List<Book> availableBook=new ArrayList<Book>();
        for(Book b:books){
            if(b.isCheckedOut()==false){
                availableBook.add(b);
            }
        }
        return availableBook;
    }

    public List<Book> getBorrowedBooks() {
        List<Book> borrowedBooks=new ArrayList<Book>();
        for(Book b:books){
            if(b.isCheckedOut()==true){
                borrowedBooks.add(b);
            }
        }
        return borrowedBooks;
    }
}
