package com.biblioteca;

/**
 * Created by sanjanar on 25/02/15.
 */
public class Book {
    private String title;
    private String author;
    private int year;
    private boolean checkedOut;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.checkedOut=false;
    }


    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
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
                ", checkedOut=" + checkedOut +
                '}';
    }

    @Override
    public boolean equals(Object newBook) {
        Book book=(Book)newBook;
        if(this.title==book.title && this.author==book.author && this.year==book.year){
            return true;
        }
        return false;
    }
}
