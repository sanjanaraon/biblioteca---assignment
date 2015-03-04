package com.biblioteca;

import java.io.IOException;
import java.util.List;

/**
 * Created by sanjanar on 02/03/15.
 */
public interface MenuAction {
    void actionPerformed(BibliotecaApp bibliotecaApp,InputOutput readerWriter,List<? extends Item> list) throws IOException, InvalidItemException;
}
