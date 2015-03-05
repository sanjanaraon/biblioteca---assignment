package com.biblioteca;

import java.io.IOException;
import java.util.List;

/**
 * Created by sanjanar on 02/03/15.
 */
public class DisplayDetailsMenuAction implements MenuAction {
//    @Override
//    public void actionPerformed(BibliotecaApp libraryManager,InputOutput readerWriter) throws IOException {
//        printMessage("Books available for borrowing",readerWriter);
//        printMessage(libraryManager.displayItemDetails(),readerWriter);
//    }
    @Override
    public void actionPerformed(LibraryManager libraryManager, InputOutput readerWriter, List<? extends Item> list,AccountManager manager)
            throws IOException {
        printMessage("Items available for borrowing",readerWriter);
        printMessage(libraryManager.displayItemDetails(list),readerWriter);
    }

    public void printMessage(String message, InputOutput readerWriter) throws IOException {
        readerWriter.writeValue(message);
    }

}
