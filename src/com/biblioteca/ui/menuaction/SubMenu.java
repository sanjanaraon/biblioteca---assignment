package com.biblioteca.ui.menuaction;

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

    public int menu(List<? extends Item> list, LibraryManager app, InputOutput inputOutput, AccountManager manager) throws IOException {
        int choice;
        do {
            printMessage(app.displayMainMenu(), inputOutput);
            choice = Integer.parseInt(acceptInput(inputOutput));
            if (choice >= 1 && choice <= 3) menuMap[choice - 1].actionPerformed(app, inputOutput, list, manager);
            else System.out.println("Enter a valid choice");
        } while (choice != 4);
        inputOutput.writeValue("Successful Exit from Menu");
        return choice;
    }

    public void printMessage(String message, InputOutput inputOutput) throws IOException {
        inputOutput.writeValue(message);
    }


    public String acceptInput(InputOutput inputOutput) throws IOException {
        return inputOutput.readValue();
    }
}