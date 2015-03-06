package com.biblioteca.core.models;

/**
 * Created by sanjanar on 03/03/15.
 */
public interface Item {

    @Override
    String toString();

    @Override
    boolean equals(Object newItem);

    boolean isCheckedOut();

    void setCheckedOut(boolean flag);

    String getTitle();

    int getYear();

    String getDirector();

    String getRating();

    String getAuthor();

    String getCategory();
}
