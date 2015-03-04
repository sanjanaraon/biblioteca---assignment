package com.biblioteca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanjanar on 04/03/15.
 */
public class SubMenu {
    MenuAction[] menuMap = new MenuAction[0];
    private InputOutput inputOutput;
    BibliotecaApp app;

    public SubMenu(InputOutput inputOutput) {
        this.inputOutput=inputOutput;
        this.menuMap = menuInitializer();
    }

    public SubMenu(TestReaderWriter testReaderWriter, BibliotecaApp mockBibliotecaApp) {
        this.inputOutput=testReaderWriter;
        this.app=mockBibliotecaApp;
        this.menuMap=menuInitializer();
    }

    private MenuAction[] menuInitializer() {

        return new MenuAction[]{
                new DisplayDetailsMenuAction(),
                new CheckOutBookMenuAction(),
                new ReturnBookMenuAction(),
                new ExitFromApplication(),

        };
    }

    public int menu(List<? extends Item> list, BibliotecaApp app, InputOutput inputOutput, AccountManager manager) throws IOException {
        int choice=0;
        do {
            List<? extends Item> items=list;
            try {
                printMessage(app.displayMainMenu(),inputOutput);
                choice = Integer.parseInt(acceptInput(inputOutput));
            } catch (IOException e) {
                inputOutput.writeValue(e.toString());
            }

            if (choice >= 1 && choice <= 4) {

                try {
                    menuMap[choice-1].actionPerformed(app, inputOutput,items,manager);
                } catch (IOException e) {
                    System.out.println(e);
                } catch (InvalidItemException e) {
                    System.out.println(e);
                }
            } else {
                System.out.println("Enter a valid choice");
            }
        } while (choice != 4);
        return choice;
    }

    public void printMessage(String message, InputOutput inputOutput) throws IOException {
        inputOutput.writeValue(message);
    }


    public String acceptInput(InputOutput inputOutput) throws IOException {
        return inputOutput.readValue();
    }
}
