package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanjanar on 26/02/15.
 */
public class BibliotecaApp {
//    Library bookLibrary=createLibraryWithBooks();
//     Library movieLibrary=createLibraryWithMovies();
    Library library;
    public BibliotecaApp() {

            this.library = new Library();
    }

    public BibliotecaApp(Library library){
        this.library=library;
    }

//    public BibliotecaApp(Library library) {
//        if (type.equalsIgnoreCase("book"))
//            this.library = createLibraryWithBooks();
//        else if(type.equalsIgnoreCase("movie"))
//            this.library=createLibraryWithMovies();
//        else
//            throw new InvalidAttributesException("library of specified class cannot be created");
////        this.bookLibrary = Library.createLibraryWithBooks();
////        this.movieLibrary= Library.createLibraryWithMovies();
//    }

    public static Library createLibraryWithBooks(){
        Library library=new Library();
        library.initializeBookList();
        return library;
    }

    public static Library createLibraryWithMovies(){
        Library library=new Library();
        library.initializeMovieList();
        return library;
    }

    public String displayWelcomeMessage() {
        return "Welcome to Biblioteca \n The app to borrow and return the books/movies to the library \n";
    }

    public String displayMainMenu() {
        return "Main menu \n" +
                " 1 ---- Item Details \n" +
                " 2 ---- Check Out a Item\n" +
                " 3 ---- Return a Item\n" +
                " 4 ---- Exit\n"+
                "Enter your choice";
    }

    public String displayItemDetails() {
        List<Item> itemList=library.getAvailableItems();
        String result="";
        for(Item b:itemList){
            result+=b+"\n";
        }
        return result;
    }


    public List<Item> getItemList(){
        return library.getItems();
    }


    public String getItems() {
        List<Item> itemList=library.getItems();
        String result="";
        for(Item b:itemList){
            result+=b+"\n";
        }
        return result;
    }

    public boolean checkOutFromLibrary(Item item) throws InvalidItemException {
        if (library.checkOutItem(item)){
            return true;
        }
        return false;
    }

    public boolean returnBookToLibrary(Item item) throws InvalidItemException {
        if(library.returnItem(item)){
            return true;
        }
        return false;
    }

    public Item getItem(String title) {
        for (Item item:library.itemList){
            if(item.getTitle().equalsIgnoreCase(title)){
                return item;
            }
        }
        return null;
    }


    public String borrowedItems() {
        String result="";
        for(Item item:library.getItems()){
            if(item.isCheckedOut()==true){
                result+=item;
            }
        }

        return result;
    }

    public boolean validTitle(String title) {
        Item item = getItem(title);
        if (item != null) {
            return true;
        }
        return false;
    }


    public List<Item> getItemListByCategory(String category){
        List<Item> items=new ArrayList<Item>();
        for(Item i:library.itemList){
            if(i.getCategory().equalsIgnoreCase(category)){
                items.add(i);
            }
        }
        return items;
    }

    public String displaySpecificItemListDetails(List<? extends Item> list) {
        String result="";
        for(Item i:list){
            result+=i+"\n";
        }
        return result;
    }
}

