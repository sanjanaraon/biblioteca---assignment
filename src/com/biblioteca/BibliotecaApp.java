package com.biblioteca;

import java.util.List;

/**
 * Created by sanjanar on 26/02/15.
 */
public class BibliotecaApp {
    private Library library= new Library();

    public BibliotecaApp() {
        library.initializeBookList();
    }

    public String displayMessage() {
        return "Welcome to Biblioteca \n The app to borrow and return the books to the library";
    }

    public String displayMainMenu() {
        return "Main menu \n" +
                " 1 ---- Book Details \n" +
                " 2 ---- Exit";
    }


    public List<Book> displayBookDetails() {

        return null;
    }

    public List<Book> getBooks() {
        return library.getBooks();
    }
}
