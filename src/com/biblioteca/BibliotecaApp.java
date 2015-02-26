package com.biblioteca;

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
        this(new Library());
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

    public String displayBookDetails() {
        List<Book> bookList=library.getAvailableBooks();
        String result=" ";
        for(Book b:bookList){
            result+= b.getTitle()+"    |"+b.getAuthor()+"   |"+b.getYear()+"\n";
        }
        return result;
    }

    public List<Book> getBooks() {
        return library.getBooks();
    }

    public void checkOutFromLibrary(Book book) throws InvalidBookException {
        library.checkOut(book);
    }

    public void returnBookFromLibrary(Book book) throws InvalidBookException {
        library.returnBook(book);
    }
}
