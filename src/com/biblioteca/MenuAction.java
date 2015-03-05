package com.biblioteca;

import java.io.IOException;
import java.util.List;

/**
 * Created by sanjanar on 02/03/15.
 */
public interface MenuAction {
    void actionPerformed(LibraryManager libraryManager,InputOutput readerWriter,List<? extends Item> list,AccountManager manager)
            throws IOException, InvalidItemException;
}
