package com.biblioteca;

import java.io.IOException;
import java.util.List;

/**
 * Created by sanjanar on 05/03/15.
 */
public class MoviesMenuAction implements subMenuAction {
    @Override
    public void subMenuActionPerformed(LibraryManager libraryManager, InputOutput readerWriter, AccountManager manager) throws IOException {
        SubMenu subMenu=new SubMenu(readerWriter);
        subMenu.menu(libraryManager.getItemListByCategory("movie"),libraryManager,readerWriter,manager);
    }
}
