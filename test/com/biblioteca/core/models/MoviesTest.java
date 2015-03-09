package com.biblioteca.core.models;

import com.biblioteca.core.models.Movie;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by sanjanar on 03/03/15.
 */
public class MoviesTest {
    @Test
    public void shouldReturnTrueWhenTwoMoviesAreEqual() throws Exception {
        Movie movie = new Movie("Star wars",1977,"George Lucas","9");
        assertEquals(movie, new Movie("Star wars", 1977, "George Lucas", "9"));
    }

    @Test
    public void shouldReturnFalseWhenTwoMoviesAreNotEqual() throws Exception {
        Movie movie = new Movie("Star wars",1977,"George Lucas","9");
        assertNotEquals(movie, new Movie("The Terminator", 1984, "James Cameron", "8"));
    }
}
