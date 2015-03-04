package com.biblioteca;

import java.io.IOException;
import java.util.List;

/**
 * Created by sanjanar on 02/03/15.
 */
public class ReturnBookMenuAction implements MenuAction {
        AccountManager manager = new AccountManager();

    @Override
    public void actionPerformed(BibliotecaApp bibliotecaApp, InputOutput readerWriter, List<? extends Item> list,AccountManager manager)
            throws IOException, InvalidItemException {
        UserInfo loggedInUser = manager.checkForLoggedInUser();
        if (loggedInUser != null) {
            accessReturnItem(bibliotecaApp, readerWriter,list);
        } else {
            printMessage("Enter library number", readerWriter);
            String number = acceptInput(readerWriter);
            printMessage("Enter password", readerWriter);
            String pswd = acceptInput(readerWriter);
            if (manager.checkCredentials(number, pswd)) {
                accessReturnItem(bibliotecaApp, readerWriter, list);
            } else {
                printMessage("Not a valid user", readerWriter);
                return;
            }
        }
    }

    private void accessReturnItem(BibliotecaApp bibliotecaApp, InputOutput readerWriter, List<? extends Item> list) throws IOException {
        printMessage(bibliotecaApp.displaySpecificItemListDetails(list),readerWriter);
        printMessage("Select a Item by entering the title", readerWriter);
        String title;
        title = acceptInput(readerWriter);
        Item book = bibliotecaApp.getItem(title);
        try {
            if (bibliotecaApp.returnBookToLibrary(book)) {
                printMessage(book.getTitle() + " returned to the library", readerWriter);
            }
        } catch (InvalidItemException e) {
            System.out.println(e);
        }
    }

    public void printMessage(String message, InputOutput readerWriter) throws IOException {
        readerWriter.writeValue(message);
    }

    public String acceptInput(InputOutput readerWriter) throws IOException {
        return readerWriter.readValue();
    }
}
