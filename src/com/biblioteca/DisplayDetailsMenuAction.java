package com.biblioteca;

import java.io.IOException;

/**
 * Created by sanjanar on 02/03/15.
 */
public class DisplayDetailsMenuAction implements MenuAction {
    @Override
    public void actionPerformed(BibliotecaApp app,InputOutput readerWriter) throws IOException {
        printMessage("Books available for borrowing",readerWriter);
        printMessage(app.displayBookDetails(),readerWriter);
    }

    public void printMessage(String message, InputOutput readerWriter) throws IOException {
        readerWriter.writeValue(message);
    }
}
