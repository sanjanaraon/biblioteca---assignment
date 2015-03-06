package com.biblioteca;

import com.biblioteca.core.controller.LibraryManager;
import com.biblioteca.security.AccountManager;

import java.io.IOException;

/**
 * Created by sanjanar on 05/03/15.
 */
public interface SubMenuAction {

    public void subMenuActionPerformed(LibraryManager libraryManager,InputOutput readerWriter,AccountManager manager) throws IOException;
}
