package com.biblioteca;

import java.io.IOException;

/**
 * Created by sanjanar on 26/02/15.
 */
public class ConsoleApp {
    subMenuAction[] subMenuMap;
    private String input;
    private InputOutput inputOutput;
    LibraryManager app;


    private subMenuAction[] menuInitializer() {
        return new subMenuAction[]{
                new BooksMenuAction(),
                new MoviesMenuAction(),
                new ExitApplication(),
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
            SubMenu subMenu = new SubMenu(inputOutput);
            printMessage("Which library you want to use book(0)/movie(1)/Exit(2)\n Enter 0/1/2??");
            number = Integer.parseInt(acceptInput());
            if (number >= 0 && number <= 2) {
                subMenuMap[number].subMenuActionPerformed(app, inputOutput, manager);
            } else {
                printMessage("enter either 0 or 1 or 2");
            }
        } while (number!=2);
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
