package com.biblioteca;

import java.io.IOException;

/**
 * Created by sanjanar on 02/03/15.
 */
public interface MenuAction {
    void actionPerformed(BibliotecaApp bibliotecaApp,InputOutput readerWriter) throws IOException, InvalidBookException;
}
