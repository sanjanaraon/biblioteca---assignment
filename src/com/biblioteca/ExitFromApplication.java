package com.biblioteca;

import java.io.IOException;

/**
 * Created by sanjanar on 02/03/15.
 */
public class ExitFromApplication implements MenuAction {
    @Override
    public void actionPerformed( BibliotecaApp bibliotecaApp, InputOutput readerWriter) throws IOException, InvalidBookException {
        readerWriter.writeValue("Successful Exit");
        return;
    }
}
