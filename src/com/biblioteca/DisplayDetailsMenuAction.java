package com.biblioteca;

import java.io.IOException;
import java.util.List;

/**
 * Created by sanjanar on 02/03/15.
 */
public class DisplayDetailsMenuAction implements MenuAction {
//    @Override
//    public void actionPerformed(BibliotecaApp app,InputOutput readerWriter) throws IOException {
//        printMessage("Books available for borrowing",readerWriter);
//        printMessage(app.displayItemDetails(),readerWriter);
//    }
    @Override
    public void actionPerformed(BibliotecaApp bibliotecaApp, InputOutput readerWriter, List<? extends Item> list) throws IOException, InvalidItemException {
        printMessage("Items available for borrowing",readerWriter);
        printMessage(bibliotecaApp.displaySpecificItemListDetails(list),readerWriter);
    }

    public void printMessage(String message, InputOutput readerWriter) throws IOException {
        readerWriter.writeValue(message);
    }

}
