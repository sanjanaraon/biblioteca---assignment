package com.biblioteca.ui.controller;

import com.biblioteca.ui.menuaction.BooksMenuAction;
import com.biblioteca.ui.console.InputOutput;
import com.biblioteca.ui.menuaction.MoviesMenuAction;
import com.biblioteca.ui.menuaction.SubMenuAction;
import com.biblioteca.core.security.AccountManager;

import java.io.IOException;

/**
 * Created by sanjanar on 26/02/15.
 */
public class ConsoleApp {
    SubMenuAction[] subMenuMap;
    private String input;
    private InputOutput inputOutput;
    LibraryManager app;


    private SubMenuAction[] menuInitializer() {
        return new SubMenuAction[]{
                new BooksMenuAction(),
                new MoviesMenuAction(),
        };
    }

    public ConsoleApp(InputOutput inputOutput) {
        this.inputOutput = inputOutput;
        this.app = new LibraryManager();
        this.subMenuMap = menuInitializer();
    }


    public ConsoleApp(InputOutput inputOutput, LibraryManager mockBiblioteca) {
        this.inputOutput = inputOutput;
        this.app = mockBiblioteca;
        this.subMenuMap = menuInitializer();
    }

    AccountManager manager = new AccountManager();

    public void mainMenu() throws IOException {
        int number;
        firstMessage();
        do {
            printMessage("Which library you want to use book(1)/movie(2)/Exit(3)\n Enter 1/2/3??");
            number = Integer.parseInt(acceptInput());
            if (number >= 1 && number <= 2) subMenuMap[number - 1].subMenuActionPerformed(app, inputOutput, manager);
            else if(number!=3) printMessage("enter either 1 or 2 or 3");
        } while (number!=3);
        inputOutput.writeValue("Successful Exit From Biblioteca App");
    }

    public void firstMessage() {
        try {
            printMessage(app.displayWelcomeMessage());
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    public void printMessage(String message) throws IOException {
        inputOutput.writeValue(message);
    }


    public String acceptInput() throws IOException {
        return inputOutput.readValue();
    }


}
