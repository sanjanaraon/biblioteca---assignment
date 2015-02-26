package com.biblioteca;

import java.util.Scanner;

/**
 * Created by sanjanar on 26/02/15.
 */
public class AppStarter {

    public static void main(String[] args) {
        BibliotecaApp app;
        app = new BibliotecaApp();
        System.out.println(app.displayMessage());
        boolean flag = true;
        while (flag){
            Scanner input=new Scanner(System.in);
            System.out.println(app.displayMainMenu());
            int choice=input.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Books available for borrowing");
                    System.out.println(app.displayBookDetails());
                    System.out.println("Books that have to be returned");
                    System.out.println(app.borrowedBooks());
                    System.out.println("Select a book by entering the title");
                    Scanner scanner=new Scanner(System.in);
                    String title=scanner.nextLine();
                    if(validTitle(title)){
                    subMenu(title,app);
                    }else
                        System.out.println("There seems to be a mistake in book title entered \n OR The book may not exist");
                    break;
                case 2:
                    flag = false;
                    break;
                default:
                    System.out.println("Enter a valid choice");
                    break;
            }
            if(flag==false){
                break;
            }
        }
    }

    private static boolean validTitle(String title) {
        BibliotecaApp app=new BibliotecaApp();
        Book book=app.getBook(title);
        if(book!=null){
            return true;
        }
        return false;
    }

    private static void subMenu(String title, BibliotecaApp app) {
        Scanner input=new Scanner(System.in);
        Book book=app.getBook(title);
        System.out.println("you selected "+book.getTitle());
        System.out.println("1 --- checking out the book \n2 --- returning the book \n3 --- previous menu \nEnter your choice");
        int choice=input.nextInt();
        switch (choice){
            case 1:
                try {
                    app.checkOutFromLibrary(book);
                    System.out.println(title+" is checked out successfully");
                } catch (InvalidBookException e) {
                    System.out.println(e);
                }
                break;
            case 2:
                try {
                    app.returnBookFromLibrary(book);
                    System.out.println(title+" returned to the library");
                } catch (InvalidBookException e) {
                    System.out.println(e);
                }
                break;
            case 3:return;
        }
    }

}
