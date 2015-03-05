package com.biblioteca;

import java.io.IOException;
import java.util.List;

/**
 * Created by sanjanar on 02/03/15.
 */
public class ExitFromSubMenu implements MenuAction {
//    @Override
//    public void actionPerformed( BibliotecaApp bibliotecaApp, InputOutput readerWriter) throws IOException, InvalidItemException {
//    }

    @Override
    public void actionPerformed(LibraryManager libraryManager, InputOutput readerWriter, List<? extends Item> list,AccountManager manager)
            throws IOException {
        readerWriter.writeValue("Successful Exit");
        return;

    }
}
