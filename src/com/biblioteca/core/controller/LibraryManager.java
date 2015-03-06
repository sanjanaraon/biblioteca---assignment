package com.biblioteca.core.controller;

import com.biblioteca.Item;
import com.biblioteca.core.models.Book;
import com.biblioteca.core.models.Library;
import com.biblioteca.core.models.Movie;
import com.biblioteca.exceptions.InvalidItemException;

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

    public LibraryManager(Library library) {
        this.library = library;
    }


    public static Library createLibraryWithBooks() {
        Library library = new Library();
        library.initializeBookList();
        return library;
    }

    public static Library createLibraryWithMovies() {
        Library library = new Library();
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
                " 4 ---- Exit\n" +
                "Enter your choice";
    }

    public String displayItemDetails(List<? extends Item> list) {
        String result = "";
        List<Item> itemList = library.getAvailableItems();
        for (Item availableItemList : itemList) {
            for (Item specificItemList : list) {
                if (availableItemList == specificItemList)
                    result += specificItemList + "\n";
            }
        }
        return result;
    }

    public List<Item> borrowedItems(List<? extends Item> list) {
        List<Item> borrowedList = new ArrayList<Item>();
        for (Item borrowedItem : library.getBorrowedItems()) {
            for (Item specificItem : list) {
                if (borrowedItem == specificItem) {
                    borrowedList.add(specificItem);
                }
            }
        }
        return borrowedList;
    }


    public List<Item> getItemList() {
        return library.getItems();
    }


    public void checkOutFromLibrary(Item item) throws InvalidItemException {
        library.checkOutItem(item);
    }

    public void returnBookToLibrary(Item item) throws InvalidItemException {
        library.returnItem(item);
    }

    public Item getItem(String title) {
        for (Item item : library.itemList) {
            if (item.getTitle().equalsIgnoreCase(title)) {
                return item;
            }
        }
        return null;
    }


//    private List<Item> getSpecificItemList(List<? extends Item> list, List<Item> itemList) {
//        List<Item> specificItem=new ArrayList<Item>();
//        for(Item i:itemList){
//            if(list instanceof Book){
//                specificItem.add(i);
//            }
//        }
//        return specificItem;
//    }

    public boolean validTitle(String title) {
        Item item = getItem(title);
        if (item != null) {
            return true;
        }
        return false;
    }


    public List<Item> getItemListByCategory(String category) {
        List<Item> items = new ArrayList<Item>();
        for (Item i : library.itemList) {
            if (i.getCategory().equalsIgnoreCase(category)) {
                items.add(i);
            }
        }
        return items;
    }

    public String displaySpecificItemListDetails(List<? extends Item> list) {
        String result = "";
        for (Item specificItemList : list) {
            result += specificItemList + "\n";
        }
        return result;
    }

    public String displayItemDetailsInTableForm(List<? extends Item> items) {
        String result = "";
        int firstBook=0;
        int firstMovie=0;
        for (Item item : items) {
            if (item instanceof Book) {
                if(firstBook==0){
                    result=bookOutputFormatter(result,item,true);
                    firstBook++;
                }else
                result = bookOutputFormatter(result, item,false);
            } else if (item instanceof Movie) {
                if(firstMovie==0){
                result = movieOutputFormatter(result, item,true);
                    firstMovie++;
                }else
                result=movieOutputFormatter(result,item,false);
            }
        }
        return result;
    }

    private String movieOutputFormatter(String result, Item item, boolean isFirstCall) {
        if(isFirstCall){
            result+="| Movie Title                           | Movie Release Year           | Movie Director        " +
                    "                  | Movie Rating                   |\n";
        }
        result += "|" + item.getTitle() + "                  |" + item.getYear() + "        |" + item.getDirector() +
                "                     |" + item.getRating() + "        |\n";
        return result;
    }

    private String bookOutputFormatter(String result, Item item, boolean firstCall) {
        if(firstCall){
            result+="| Book Title                                          | Book Author                           | Year Of Publish          |\n";
        }
        result += "|" + item.getTitle() + "                             |" + item.getAuthor() + "                |" + item.getYear() + "   |\n";
        return result;
    }
}

