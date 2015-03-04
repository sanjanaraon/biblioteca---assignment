package com.biblioteca;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by sanjanar on 03/03/15.
 */
public class MoviesTest {
    @Test
    public void shouldPrintMovieDetails() throws Exception {
        Movie movie=new Movie("The Terminator",1984,"James Cameron","8");
        assertEquals(new Movie("The Terminator",1984,"James Cameron","8").toString(),movie.toString());
    }
}
