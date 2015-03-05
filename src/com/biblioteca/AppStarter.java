package com.biblioteca;

import java.io.IOException;

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
