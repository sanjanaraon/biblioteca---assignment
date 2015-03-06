package com.biblioteca;

import com.biblioteca.ui.console.ConsoleReaderWriter;
import com.biblioteca.ui.controller.ConsoleApp;

/**
 * Created by sanjanar on 27/02/15.
 */
public class AppStarter {
    public static void main(String[] args)  {
        ConsoleReaderWriter consoleReaderWriter = new ConsoleReaderWriter();
        ConsoleApp app = new ConsoleApp(consoleReaderWriter);
        try {
            app.mainMenu();
        } catch (Exception e) {
            System.out.println("There was some input type mismatch exception");
        }
    }
}
