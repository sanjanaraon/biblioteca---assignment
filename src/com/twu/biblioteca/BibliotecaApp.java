package com.twu.biblioteca;

public class  BibliotecaApp {

    public static void main(String[] args) {
        displayWelcomeMessage();
        displayMainMenu();
    }

    public static String displayMainMenu() {
        return "Main Menu \n" +
                "Enter your choice \n" +
                " 1)List Books";
    }

    public static String displayWelcomeMessage() {
        return "Namaste Welcome to Biblioteca Application \n The app to borrow book and return books";
    }
}
