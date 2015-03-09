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
    public UserInfo actionPerformed(LibraryManager libraryManager, InputOutput readerWriter, List<? extends Item> list, AccountManager manager,UserInfo user)
            throws IOException {
        UserInfo loggedInUser = manager.checkForLoggedInUser();

        if (loggedInUser != null ) {
            accessReturnItem(libraryManager, readerWriter, list, user);
        } else {
            printMessage("Enter library number", readerWriter);
            String number = acceptInput(readerWriter);
            printMessage("Enter password", readerWriter);
            String pswd = acceptInput(readerWriter);
            user = manager.checkCredentialsAndReturnUser(number, pswd, libraryManager);
            if (user!=null) {
                accessReturnItem(libraryManager, readerWriter, list,user);
            } else {
                printMessage("Not a valid user", readerWriter);
            }
        }
                return user;
    }

    private void accessReturnItem(LibraryManager libraryManager, InputOutput readerWriter, List<? extends Item> list, UserInfo user) throws IOException {
        List<Item> borrowedItem=libraryManager.borrowedItems(list);
        printMessage(libraryManager.displayItemDetailsInTableForm(borrowedItem), readerWriter);
        printMessage("Select a Item by entering the title", readerWriter);
        String title;
        title = acceptInput(readerWriter);
        Item book = libraryManager.getItem(title);
        try {
            libraryManager.returnBookToLibrary(book,user);
            if(user.getCategory().equals("librarian")){
                printMessage("do you want to see the report yes(1)/no(0)",readerWriter);
                int choice= Integer.parseInt(acceptInput(readerWriter));
                if(choice==1){
                    printMessage(libraryManager.generateReport(),readerWriter);
                }
            }
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
