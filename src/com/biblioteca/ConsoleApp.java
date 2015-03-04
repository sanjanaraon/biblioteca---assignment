package com.biblioteca;

import java.io.IOException;

/**
 * Created by sanjanar on 26/02/15.
 */
public class ConsoleApp {

    private String input;
    private InputOutput inputOutput;
    BibliotecaApp app;


    public ConsoleApp(InputOutput inputOutput) {
        this.inputOutput = inputOutput;
        this.app=new BibliotecaApp();
    }


    public ConsoleApp(InputOutput inputOutput, BibliotecaApp mockBiblioteca) {
        this.inputOutput = inputOutput;
        this.app = mockBiblioteca;
    }
    AccountManager manager = new AccountManager();

    public void mainMenu() throws IOException {
        firstMessage();
        while (true) {
            SubMenu subMenu=new SubMenu(inputOutput);
            printMessage("Which library you want to use book(0)/movie(1)/Exit(2)\n Enter 0/1/2??");
                    int number= Integer.parseInt(acceptInput());
                    if(number==0){
                        subMenu.menu(app.getItemListByCategory("book"),app,inputOutput,manager);
                    }else if(number==1){
                       subMenu.menu(app.getItemListByCategory("movie"), app, inputOutput, manager);
                    }else if(number==2){
                       return;
                    }else{
                        printMessage("enter either 0 or 1 or 2");
                    }
        }
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
