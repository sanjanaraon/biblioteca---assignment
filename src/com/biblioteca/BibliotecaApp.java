package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanjanar on 26/02/15.
 */
public class BibliotecaApp {
    private Library library;

    public BibliotecaApp(Library library) {
        this.library = library;
        this.library.initializeBookList();
    }

    public BibliotecaApp() {
        this.library=new Library();
        library.initializeBookList();
    }

    public String displayWelcomeMessage() {
        return "Welcome to Biblioteca \n The app to borrow and return the books to the library";
    }

    public String displayMainMenu() {
        return "Main menu \n" +
                " 1 ---- Book Details \n" +
                " 2 ---- Check Out a book\n" +
                " 3 ---- Return a book\n" +
                " 4 ---- Exit\n"+
                "Enter your choice";
    }

    public String displayBookDetails() {
        List<Book> bookList=library.getAvailableBooks();
        String result=" ";
        for(Book b:bookList){
            result+=b+"\n";
        }
        return result;
    }

    public List<Book> getBooksList(){
        return library.getBooks();
    }
    public String getBooks() {
        List<Book> bookList=library.getBooks();
        String result=" ";
        for(Book b:bookList){
           result+=b+"\n";
        }
        return result;
    }

    public boolean checkOutFromLibrary(Book book) throws InvalidBookException {
        if (library.checkOut(book)){
            return true;
        }
        return false;
    }

    public boolean returnBookToLibrary(Book book) throws InvalidBookException {
        if(library.returnBook(book)){
            return true;
        }
        return false;
    }

    public Book getBook(String title) {
        for(Book b:library.books){
            if(title.equalsIgnoreCase(b.getTitle())){
                return b;
            }
        }
        return null;
    }


    public String borrowedBooks() {
        String result="";
        for(Book b:library.getBorrowedBooks()){
            if(b.isCheckedOut()==true){
                result+=b;
            }
        }

        return result;
    }

    public boolean validTitle(String title) {
        Book book = getBook(title);
        if (book != null) {
            return true;
        }
        return false;
    }
}
