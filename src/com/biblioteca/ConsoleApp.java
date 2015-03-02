package com.biblioteca;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by sanjanar on 26/02/15.
 */
public class ConsoleApp {

    private String input;
    private InputOutput inputOutput;
    BibliotecaApp app;
    MenuAction[] menuMap = new MenuAction[0];

//    public ConsoleApp( InputOutput inputOutput, BibliotecaApp app) {
//        this.menuMap = menuMap;
//        this.inputOutput = inputOutput;
//        this.app = app;
//    }





    public ConsoleApp(InputOutput inputOutput) {
        this.inputOutput = inputOutput;
        this.app = new BibliotecaApp();
        this.menuMap = menuInitializer();
    }

    public ConsoleApp(String s) {
        this.input=s;
    }

    private MenuAction[] menuInitializer() {

        return new MenuAction[]{
                new DisplayDetailsMenuAction(),
                new CheckOutBookMenuAction(),
                new ReturnBookMenuAction(),
                new ExitFromApplication(),

        };
    }


    public ConsoleApp(InputOutput inputOutput, BibliotecaApp mockBiblioteca) {
        this.menuMap = menuInitializer();
        this.inputOutput = inputOutput;
        this.app = mockBiblioteca;
    }

    public void mainMenu() throws IOException {
        firstMessage();
        int choice = 0;

        do {
            try {
                printMessage(app.displayMainMenu());
                choice = Integer.parseInt(acceptInput());

            } catch (IOException e) {
                inputOutput.writeValue(e.toString());
            }

            if (choice >= 1 && choice <= 4) {

                try {
                    menuMap[choice-1].actionPerformed(app, inputOutput);
                } catch (IOException e) {
                    System.out.println(e);
                } catch (InvalidBookException e) {
                    System.out.println(e);
                }
            } else {
                System.out.println("Enter a valid choice");
            }
        } while (choice != 4);
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
