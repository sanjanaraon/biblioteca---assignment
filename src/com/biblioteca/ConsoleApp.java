package com.biblioteca;

import java.io.IOException;

/**
* Created by sanjanar on 26/02/15.
*/
public class ConsoleApp {

    private InputOutput inputOutput;
    BibliotecaApp app = new BibliotecaApp();

    public ConsoleApp(InputOutput inputOutput) {
        this.inputOutput = inputOutput;
    }

    static int count = 0;

    public void appFlow() throws IOException {
        printMessage(app.displayWelcomeMessage());
        boolean flag = true;
        while (flag) {
            printMessage(app.displayMainMenu());
            int choice = Integer.parseInt(acceptInput());
            switch (choice) {
                case 1:
                    actionForChoice1();
                    break;
                case 2:
                    flag = false;
                    break;
                default:
                    System.out.println("Enter a valid choice");
                    break;
            }
            if (flag == false) {
                break;
            }
        }
    }

    public void actionForChoice1() throws IOException {
        displayBookDetails();
        if (count > 0) {
            printMessage("Books that have to be returned");
            printMessage(app.borrowedBooks());
        }
        printMessage("Select a book by entering the title");
        String title = acceptInput();
        if (app.validTitle(title)) {
            subMenu(title);
        } else
            printMessage("There seems to be a mistake in book title entered \n OR The book may not exist");
    }

    public void displayBookDetails() throws IOException {
        printMessage("Books available for borrowing");
        printMessage(app.displayBookDetails());
    }

    public void printMessage(String message) throws IOException {
        inputOutput.writeValue(message);
    }


    public String acceptInput() throws IOException {
        return inputOutput.readValue();
    }


    private void subMenu(String title) throws IOException {
        Book book = app.getBook(title);
        printMessage("you selected " + book.getTitle());
        printMessage("1 --- checking out the book \n2 --- returning the book \n3 --- previous menu \nEnter your choice");
        int choice = Integer.parseInt(acceptInput());
        switch (choice) {
            case 1:
                try {
                    app.checkOutFromLibrary(book);
                    printMessage(title + " is checked out successfully");
                    count++;
                } catch (InvalidBookException e) {
                    printMessage(e.toString());
                }
                break;
            case 2:
                try {
                    app.returnBookFromLibrary(book);
                    printMessage(title + " returned to the library");
                    count--;
                } catch (InvalidBookException e) {
                    printMessage(e.toString());
                }
                break;
            case 3:
                return;
        }
    }

}
