package com.biblioteca.ui.menuaction;

import com.biblioteca.core.models.UserInfo;
import com.biblioteca.ui.console.InputOutput;
//import com.biblioteca.TestReaderWriter;
import com.biblioteca.core.models.Item;
import com.biblioteca.ui.controller.LibraryManager;
import com.biblioteca.core.security.AccountManager;

import java.io.IOException;
import java.util.List;

/**
 * Created by sanjanar on 04/03/15.
 */
public class SubMenu {
    MenuAction[] menuMap;
    InputOutput inputOutput;
    LibraryManager libraryManager;


    public SubMenu(InputOutput inputOutput) {
        this.inputOutput = inputOutput;
        this.menuMap = menuInitializer();
    }

    public SubMenu(InputOutput testReaderWriter, LibraryManager mockLibraryManager) {
        this.inputOutput = testReaderWriter;
        this.libraryManager = mockLibraryManager;
        this.menuMap = menuInitializer();
    }

    private MenuAction[] menuInitializer() {

        return new MenuAction[]{
                new DisplayDetailsMenuAction(),
                new CheckOutBookMenuAction(),
                new ReturnBookMenuAction(),
        };
    }

    public void menu(List<? extends Item> list, LibraryManager app, InputOutput inputOutput, AccountManager manager) throws IOException {
        int choice;

        UserInfo user = manager.checkForLoggedInUser();
        do {
            printMessage(app.displayMainMenu(), inputOutput);
            choice = Integer.parseInt(acceptInput(inputOutput));
            if (choice >= 1 && choice <= 3) {
              user=  menuMap[choice - 1].actionPerformed(app, inputOutput, list, manager, user);
            } else if (choice != 4) printMessage("Enter a valid choice", inputOutput);
        } while (choice != 4);
        inputOutput.writeValue("Successful Exit from Menu");
        if (user != null) {
            printMessage("Do you want to log out?? \nYes(1) No(0)", inputOutput);
            choice = Integer.parseInt(acceptInput(inputOutput));
            if (choice == 1) {
                manager.logOut(user);
                printMessage("User logged out", inputOutput);
            }

        }
    }

    public void printMessage(String message, InputOutput inputOutput) throws IOException {
        inputOutput.writeValue(message);
    }


    public String acceptInput(InputOutput inputOutput) throws IOException {
        return inputOutput.readValue();
    }
}
