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
public interface MenuAction {
    UserInfo actionPerformed(LibraryManager libraryManager,InputOutput readerWriter,List<? extends Item> list,AccountManager manager,UserInfo user)
            throws IOException;
}
