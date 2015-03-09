package com.biblioteca.ui.menuaction;

import com.biblioteca.core.models.UserInfo;
import com.biblioteca.ui.console.InputOutput;
import com.biblioteca.core.models.Item;
import com.biblioteca.ui.controller.LibraryManager;
import com.biblioteca.core.security.AccountManager;

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
    public UserInfo actionPerformed(LibraryManager libraryManager, InputOutput readerWriter, List<? extends Item> list,AccountManager manager,UserInfo userInfo)
            throws IOException {
        printMessage("Items available for borrowing",readerWriter);
        printMessage(libraryManager.displayItemDetails(list),readerWriter);
        return null;
    }

    public void printMessage(String message, InputOutput readerWriter) throws IOException {
        readerWriter.writeValue(message);
    }

}
