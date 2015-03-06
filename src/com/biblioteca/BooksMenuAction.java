package com.biblioteca;

import com.biblioteca.core.controller.LibraryManager;
import com.biblioteca.security.AccountManager;

import java.io.IOException;

/**
 * Created by sanjanar on 05/03/15.
 */
public class BooksMenuAction implements SubMenuAction {
    @Override
    public void subMenuActionPerformed(LibraryManager libraryManager, InputOutput readerWriter, AccountManager manager) throws IOException {
        SubMenu subMenu=new SubMenu(readerWriter);
        subMenu.menu(libraryManager.getItemListByCategory("book"),libraryManager,readerWriter,manager);
    }
}
