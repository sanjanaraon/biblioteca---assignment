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
    public UserInfo actionPerformed(LibraryManager libraryManager, InputOutput readerWriter, List<? extends Item> list, AccountManager manager,UserInfo user)
            throws IOException {
        UserInfo loggedInUser = manager.checkForLoggedInUser();
        if (loggedInUser != null ) {
            accessCheckOutMenu(libraryManager, readerWriter, list,user);
        } else {
            printMessage("Enter library number", readerWriter);
            String number = acceptInput(readerWriter);
            printMessage("Enter password", readerWriter);
            String pswd = acceptInput(readerWriter);
            user = manager.checkCredentialsAndReturnUser(number, pswd, libraryManager);
            if (user !=null) {
                accessCheckOutMenu(libraryManager, readerWriter, list, user);
            } else {
                printMessage("Not a valid user", readerWriter);
            }
        }
                return user;
    }

    private void accessCheckOutMenu(LibraryManager libraryManager, InputOutput readerWriter, List<? extends Item> list, UserInfo user) throws IOException {
        printMessage(libraryManager.displayItemDetailsInTableForm(list), readerWriter);
        printMessage("Select a Item by entering the title", readerWriter);
        String title = acceptInput(readerWriter);
        if (libraryManager.validTitle(title)) {
            Item item = libraryManager.getItem(title);
            try {
                libraryManager.checkOutFromLibrary(item,user);
                printMessage(item.getTitle() + " is checked out successfully", readerWriter);
            } catch (InvalidItemException e) {
                System.out.println("The item can't be checked out as it is already checked out");
            }
        } else {
            printMessage("There seems to be a mistake in the title ", readerWriter);
        }
        if(user.getCategory().equals("librarian")){
            printMessage("do you want to see the report yes(1)/no(0)",readerWriter);
            int choice= Integer.parseInt(acceptInput(readerWriter));
            if(choice==1){
                printMessage(libraryManager.generateReport(),readerWriter);
            }
        }
    }

    private void printMessage(String message, InputOutput inputOutput) throws IOException {
        inputOutput.writeValue(message);
    }

    private String acceptInput(InputOutput inputOutput) throws IOException {
        return inputOutput.readValue();
    }

}
