package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanjanar on 25/02/15.
 */
public class Library {
    List<Item> itemList=new ArrayList<Item>();

    public Library() {
        rentInitializer();
    }

    private void rentInitializer() {
        initializeBookList();
        initializeMovieList();
    }


    public void addItem(Item item) {
        itemList.add(item);
    }

    public List<Item> getItems() {
        return itemList;
    }

    public void checkOutItem(Item item) throws InvalidItemException {
        if (itemList.contains(item) && !item.isCheckedOut()) {
            resetCheckOut(item, true);
            return;
        }
        throw new InvalidItemException();

    }

    public boolean returnItem(Item item) throws InvalidItemException {
        if (itemList.contains(item) && item.isCheckedOut()){
            resetCheckOut(item, false);
        return true;
        }
        throw new InvalidItemException();
    }

    private void resetCheckOut(Item item, boolean flag) {
        for (Item i : itemList) {
            if (i.equals(item)) {
                i.setCheckedOut(flag);
                break;
            }
        }
    }

    public List<Item> initializeBookList() {
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        itemList.add(book1);
        Book book2 = new Book("Let us C", "Yeshwanth", 2000);
        itemList.add(book2);
        Book book3 = new Book("The art of computer programming", "Donald ", 1968);
        itemList.add(book3);
        Book book4 = new Book("Learning Python", "Mark Lutz", 1999);
        itemList.add(book4);
        return itemList;

    }


    public List<Item> initializeMovieList(){
        Movie movie1 = new Movie("Star wars",1977,"George Lucas","9");
        itemList.add(movie1);
        Movie movie2 = new Movie("The Terminator",1984,"James Cameron","8");
        itemList.add(movie2);
        Movie movie3=new Movie("Frozen",2013,"Jeniffer Lee","7");
        itemList.add(movie3);
        Movie movie4=new Movie("Brave",2012,"Mark Andrews","unrated");
        itemList.add(movie4);
        return itemList;
    }

    public List<Item> getAvailableItems() {
        List<Item> availableBook=new ArrayList<Item>();
        for(Item item:itemList){
            if(item.isCheckedOut()==false){
                availableBook.add(item);
            }
        }
        return availableBook;
    }


    public Item getItem(String title) {
        for(Item item:itemList){
        if(item.getTitle().equalsIgnoreCase(title)){
            return item;
        }
        }
        return null;
    }


    public List<Item> getBorrowedItems(){
        List<Item> borrowed=new ArrayList<Item>();
        for(Item item:itemList){
            if(item.isCheckedOut()==true){
                borrowed.add(item);
            }
        }
        return borrowed;
    }

}
