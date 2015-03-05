package com.biblioteca;



/**
 * Created by sanjanar on 03/03/15.
 */
public class Movie implements Item{
    private String title;
    private int year;
    private String director;
    private String rating;
    private boolean checkedOut;
    private String category;

    public Movie(String name, int year, String director, String rating) {
        this.title = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
        this.checkedOut=false;
        this.category="movie";
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", director='" + director + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object newMovie){
        Item movie=(Item)newMovie;
        if(this.title ==movie.getTitle() && this.year==movie.getYear() && this.director==movie.getDirector() && this.rating==movie.getRating()){
            return true;
        }
        return false;
    }

    @Override
    public boolean isCheckedOut() {
        return checkedOut;
    }

    @Override
    public void setCheckedOut(boolean flag) {
        this.checkedOut = flag;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public String getDirector() {
        return director;
    }

    @Override
    public String getRating() {
        return rating;
    }

    @Override
    public String getAuthor() {
        return null;
    }
}
