package com.biblioteca.ui.menuaction;

import com.biblioteca.ui.console.InputOutput;
import com.biblioteca.core.models.Item;
import com.biblioteca.ui.controller.LibraryManager;
import com.biblioteca.core.models.UserInfo;
import com.biblioteca.core.exceptions.InvalidItemException;
import com.biblioteca.core.security.AccountManager;

import java.io.IOException;
import java.util.List;

/**
 * Created by sanjanar on 02/03/15.
 */
public class ReturnBookMenuAction implements MenuAction {
    AccountManager manager = new AccountManager();

    @Override
    public void actionPerformed(LibraryManager libraryManager, InputOutput readerWriter, List<? extends Item> list, AccountManager manager)
            throws IOException {
        UserInfo loggedInUser = manager.checkForLoggedInUser();
        if (loggedInUser != null) {
            accessReturnItem(libraryManager, readerWriter, list);
        } else {
            printMessage("Enter library number", readerWriter);
            String number = acceptInput(readerWriter);
            printMessage("Enter password", readerWriter);
            String pswd = acceptInput(readerWriter);
            if (manager.checkCredentials(number, pswd)) {
                accessReturnItem(libraryManager, readerWriter, list);
            } else {
                printMessage("Not a valid user", readerWriter);
                return;
            }
        }
    }

    private void accessReturnItem(LibraryManager libraryManager, InputOutput readerWriter, List<? extends Item> list) throws IOException {
        List<Item> borrowedItem=libraryManager.borrowedItems(list);
        printMessage(libraryManager.displayItemDetailsInTableForm(borrowedItem), readerWriter);
        printMessage("Select a Item by entering the title", readerWriter);
        String title;
        title = acceptInput(readerWriter);
        Item book = libraryManager.getItem(title);
        try {
            libraryManager.returnBookToLibrary(book);
            printMessage(book.getTitle() + " returned to the library", readerWriter);
        } catch (InvalidItemException e) {
            System.out.println("That is a invalid Item");
        }
    }

    public void printMessage(String message, InputOutput readerWriter) throws IOException {
        readerWriter.writeValue(message);
    }

    public String acceptInput(InputOutput readerWriter) throws IOException {
        return readerWriter.readValue();
    }
}
