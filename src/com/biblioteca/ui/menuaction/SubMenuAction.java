package com.biblioteca.ui.menuaction;

import com.biblioteca.ui.console.InputOutput;
import com.biblioteca.ui.controller.LibraryManager;
import com.biblioteca.core.security.AccountManager;

import java.io.IOException;

/**
 * Created by sanjanar on 05/03/15.
 */
public interface SubMenuAction {

    public void subMenuActionPerformed(LibraryManager libraryManager,InputOutput readerWriter,AccountManager manager) throws IOException;
}
