package com.biblioteca;

import java.io.IOException;

/**
 * Created by sanjanar on 27/02/15.
 */
public class AppStarter {
    public static void main(String[] args) throws IOException {
        ConsoleReaderWriter consoleReaderWriter = new ConsoleReaderWriter();
        ConsoleApp app = new ConsoleApp(consoleReaderWriter);
        app.mainMenu();

    }
}
