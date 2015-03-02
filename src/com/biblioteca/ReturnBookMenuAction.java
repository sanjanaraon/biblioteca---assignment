package com.biblioteca;

import java.io.IOException;

/**
 * Created by sanjanar on 02/03/15.
 */
public class ReturnBookMenuAction implements MenuAction{
    @Override
    public void actionPerformed( BibliotecaApp bibliotecaApp, InputOutput readerWriter) throws IOException {
        printMessage(bibliotecaApp.borrowedBooks(), readerWriter);
        printMessage("Select a book by entering the title",readerWriter);
        String title;
        title = acceptInput(readerWriter);
        Book book = bibliotecaApp.getBook(title);
        try {
            if(bibliotecaApp.returnBookToLibrary(book)){
            printMessage(book.getTitle() + " returned to the library", readerWriter);
            }
        } catch (InvalidBookException e) {
            System.out.println(e);
        }
    }

    public void printMessage(String message, InputOutput readerWriter) throws IOException {
        readerWriter.writeValue(message);
    }

    public String acceptInput(InputOutput readerWriter) throws IOException {
        return readerWriter.readValue();
    }

}
