package com.biblioteca;

import java.io.IOException;
import java.util.List;

/**
 * Created by sanjanar on 02/03/15.
 */
public class ExitFromApplication implements MenuAction {
//    @Override
//    public void actionPerformed( BibliotecaApp bibliotecaApp, InputOutput readerWriter) throws IOException, InvalidItemException {
//    }

    @Override
    public void actionPerformed(BibliotecaApp bibliotecaApp, InputOutput readerWriter, List<? extends Item> list,AccountManager manager)
            throws IOException, InvalidItemException {
        readerWriter.writeValue("Successful Exit");
        return;

    }
}
