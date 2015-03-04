package com.biblioteca;

import java.io.IOException;
import java.util.List;

/**
 * Created by sanjanar on 02/03/15.
 */
public class CheckOutBookMenuAction implements MenuAction {

    @Override
    public void actionPerformed(BibliotecaApp bibliotecaApp, InputOutput readerWriter, List<? extends Item> list,AccountManager manager)
            throws IOException, InvalidItemException {
        UserInfo loggedInUser = manager.checkForLoggedInUser();
        if (loggedInUser != null) {
            accessCheckOutMenu(bibliotecaApp, readerWriter, list);
        }else {
            printMessage("Enter library number", readerWriter);
            String number = acceptInput(readerWriter);
            printMessage("Enter password", readerWriter);
            String pswd = acceptInput(readerWriter);
            if (manager.checkCredentials(number, pswd)) {
                accessCheckOutMenu(bibliotecaApp, readerWriter, list);
            }else{
                printMessage("Not a valid user",readerWriter);
                return;
            }
        }
    }

    private void accessCheckOutMenu(BibliotecaApp bibliotecaApp, InputOutput readerWriter, List<? extends Item> list) throws IOException {
        printMessage(bibliotecaApp.displaySpecificItemListDetails(list), readerWriter);
        String title;
        Item item;
        printMessage("Select a Item by entering the title", readerWriter);
        title = acceptInput(readerWriter);
        if (bibliotecaApp.validTitle(title)) {
            item = bibliotecaApp.getItem(title);
            try {
                if (bibliotecaApp.checkOutFromLibrary(item)) {
                    printMessage(item.getTitle() + " is checked out successfully", readerWriter);
                }

            } catch (InvalidItemException e) {
                System.out.println(e);
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
