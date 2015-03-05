package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanjanar on 26/02/15.
 */
public class LibraryManager {
    Library library;
    public LibraryManager() {

            this.library = new Library();
    }

    public LibraryManager(Library library){
        this.library=library;
    }


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
        return "Welcome to Biblioteca \n The libraryManager to borrow and return the books/movies to the library \n";
    }

    public String displayMainMenu() {
        return "Main menu \n" +
                " 1 ---- Item Details \n" +
                " 2 ---- Check Out a Item\n" +
                " 3 ---- Return a Item\n" +
                " 4 ---- Exit\n"+
                "Enter your choice";
    }

    public String displayItemDetails(List<? extends Item> list) {
        String result="";
        List<Item> itemList=library.getAvailableItems();
        for(Item availableItemList:itemList){
            for(Item specificItemList:list){
                if(availableItemList==specificItemList)
                    result+=specificItemList+"\n";
            }
        }
        return result;
    }


    public List<Item> getItemList(){
        return library.getItems();
    }


//    public String displayListOfBorrowedItems(List<? extends Item> list ){
//        List<Item> itemList=library.getBorrowedItems();
//        List<Item> specificItem = getSpecificItemList(list, itemList);
//        String result="";
//        for(Item b:specificItem){
//            result+=b+"\n";
//        }
//        return result;
//    }

//    private List<Item> getSpecificItemList(List<? extends Item> list, List<Item> itemList) {
//        List<Item> specificItem=new ArrayList<Item>();
//        for(Item i:itemList){
//            if(list.getClass().equals(i.getClass())){
//                specificItem.add(i);
//            }
//        }
//        return specificItem;
//    }

    public void checkOutFromLibrary(Item item) throws InvalidItemException {
        library.checkOutItem(item);
    }

    public void returnBookToLibrary(Item item) throws InvalidItemException {
        library.returnItem(item) ;
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
            if(item.isCheckedOut()){
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
        for(Item specificItemList:list){
            result+=specificItemList+"\n";
        }
        return result;
    }
}

