package com.biblioteca;

import java.io.IOException;

/**
 * Created by sanjanar on 02/03/15.
 */
public class CheckOutBookMenuAction implements MenuAction {
    @Override
    public void actionPerformed(BibliotecaApp bibliotecaApp, InputOutput readerWriter) throws IOException {
        printMessage(bibliotecaApp.getBooks(), readerWriter);
        String title;
        Book book;
        printMessage("Select a book by entering the title", readerWriter);
        title = acceptInput(readerWriter);
        if (bibliotecaApp.validTitle(title)) {
            book = bibliotecaApp.getBook(title);
            try {
                if (bibliotecaApp.checkOutFromLibrary(book)) {
                    printMessage(book.getTitle() + " is checked out successfully", readerWriter);
                }

            } catch (InvalidBookException e) {
                System.out.println(e);
            }
        }else {
            printMessage("There seems to be a mistake in the title ",readerWriter);
        }
    }

    private void printMessage(String message, InputOutput inputOutput) throws IOException {
        inputOutput.writeValue(message);
    }

    private String acceptInput(InputOutput inputOutput) throws IOException {
        return inputOutput.readValue();
    }
}
