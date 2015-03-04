package com.biblioteca;

import java.io.IOException;
import java.util.List;

/**
 * Created by sanjanar on 02/03/15.
 */
public class ReturnBookMenuAction implements MenuAction{
//    @Override
//    public void actionPerformed( BibliotecaApp bibliotecaApp, InputOutput readerWriter) throws IOException {
//        printMessage(bibliotecaApp.borrowedItems(), readerWriter);
//        printMessage("Select a book by entering the title",readerWriter);
//        String title;
//        title = acceptInput(readerWriter);
//        Item book = bibliotecaApp.getItem(title);
//        try {
//            if(bibliotecaApp.returnBookToLibrary(book)){
//            printMessage(book.getTitle() + " returned to the library", readerWriter);
//            }
//        } catch (InvalidItemException e) {
//            System.out.println(e);
//        }
//    }

    public void printMessage(String message, InputOutput readerWriter) throws IOException {
        readerWriter.writeValue(message);
    }

    public String acceptInput(InputOutput readerWriter) throws IOException {
        return readerWriter.readValue();
    }

    @Override
    public void actionPerformed(BibliotecaApp bibliotecaApp, InputOutput readerWriter, List<? extends Item> list) throws IOException, InvalidItemException {
        printMessage(bibliotecaApp.borrowedItems(), readerWriter);
        printMessage("Select a Item by entering the title",readerWriter);
        String title;
        title = acceptInput(readerWriter);
        Item book = bibliotecaApp.getItem(title);
        try {
            if(bibliotecaApp.returnBookToLibrary(book)){
                printMessage(book.getTitle() + " returned to the library", readerWriter);
            }
        } catch (InvalidItemException e) {
            System.out.println(e);
        }
    }
}
