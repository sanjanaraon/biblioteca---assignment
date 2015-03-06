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
public class CheckOutBookMenuAction implements MenuAction {

    @Override
    public void actionPerformed(LibraryManager libraryManager, InputOutput readerWriter, List<? extends Item> list, AccountManager manager)
            throws IOException {
        UserInfo loggedInUser = manager.checkForLoggedInUser();
        if (loggedInUser != null) {
            accessCheckOutMenu(libraryManager, readerWriter, list);
        } else {
            printMessage("Enter library number", readerWriter);
            String number = acceptInput(readerWriter);
            printMessage("Enter password", readerWriter);
            String pswd = acceptInput(readerWriter);
            if (manager.checkCredentials(number, pswd)) {
                accessCheckOutMenu(libraryManager, readerWriter, list);
            } else {
                printMessage("Not a valid user", readerWriter);
                return;
            }
        }
    }

    private void accessCheckOutMenu(LibraryManager libraryManager, InputOutput readerWriter, List<? extends Item> list) throws IOException {
        printMessage(libraryManager.displaySpecificItemListDetails(list), readerWriter);
//        String title;
//        Item item;
        printMessage("Select a Item by entering the title", readerWriter);
        String title = acceptInput(readerWriter);
        if (libraryManager.validTitle(title)) {
            Item item = libraryManager.getItem(title);
            try {
                libraryManager.checkOutFromLibrary(item);
                printMessage(item.getTitle() + " is checked out successfully", readerWriter);
            } catch (InvalidItemException e) {
                System.out.println("The item can't be checked out as it is already checked out");
            }
        } else {
            printMessage("There seems to be a mistake in the title ", readerWriter);
        }
    }

    private void printMessage(String message, InputOutput inputOutput) throws IOException {
        inputOutput.writeValue(message);
    }

    private String acceptInput(InputOutput inputOutput) throws IOException {
        return inputOutput.readValue();
    }

}
