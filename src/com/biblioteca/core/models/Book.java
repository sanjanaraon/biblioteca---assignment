package com.biblioteca.core.models;

/**
 * Created by sanjanar on 25/02/15.
 */
public class Book implements Item {
    private String title;
    private String author;
    private int year;
    private boolean checkedOut;
    private String category;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.checkedOut=false;
        this.category="book";
    }

    @Override
    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public String getDirector() {
        return null;
    }

    @Override
    public String getRating() {
        return null;
    }

    @Override
    public String getAuthor(){
        return author;
    }
    public void setCheckedOut(boolean flag) {
        this.checkedOut = flag;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                "}";
    }

    @Override
    public boolean equals(Object newBook) {
        Item book=(Item)newBook;
        if(this.title==book.getTitle() && this.author==book.getAuthor() && this.year==book.getYear()){
            return true;
        }
        return false;
    }

}
