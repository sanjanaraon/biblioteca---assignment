package com.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by sanjanar on 27/02/15.
 */
public class ConsoleAppTest {
    TestReaderWriter testReaderWriter;
    ConsoleApp app;
    BibliotecaApp bibliotecaApp;


    @Before
    public void setUp(){
        testReaderWriter = new TestReaderWriter();
        app = new ConsoleApp(testReaderWriter);
        bibliotecaApp = new BibliotecaApp();

    }

    @Test
    public void shouldDisplayWelcomeMessage() throws Exception {
        app.printMessage(bibliotecaApp.displayWelcomeMessage());
        String expectedString = "Welcome to Biblioteca \n" +
                " The app to borrow and return the books to the library";
        assertThat(testReaderWriter.consoleOutput(), is(expectedString));
    }

    @Test
    public void shouldDisplayMainMenu() throws Exception {
        app.printMessage(bibliotecaApp.displayMainMenu());
        String expectedString="Main menu \n" +
                " 1 ---- Book Details \n" +
                " 2 ---- Exit\n" +
                "Enter your choice";
        assertEquals(testReaderWriter.consoleOutput(),expectedString);
    }

    @Test
    public void shouldAcceptUsersChoice() throws Exception {
        String  excepted= testReaderWriter.consoleInput("1");
        String choice= app.acceptInput();
        assertEquals(excepted, choice);
    }

    @Test
    public void shouldReturnTrueWhenAValidBookIsPassed() throws Exception {
        assertTrue(app.validTitle("s c j p"));
    }

    @Test
    public void shouldReturnFalseWhenInvalidBookIsPassed() throws Exception {
        assertFalse(app.validTitle("s a c d"));
    }

    @Test
    public void shouldDoActionRelatedToChoiceOf1stOptionWhenSelected() throws Exception {
        app=mock(ConsoleApp.class);
        when(app.acceptInput()).thenReturn(String.valueOf(1));
        doNothing().when(app).actionForChoice1();
        int choice= Integer.parseInt(app.acceptInput());
        if(choice==1){
            app.actionForChoice1();
        }

        verify(app).acceptInput();
        verify(app).actionForChoice1();
    }

    @Test
    public void checkControlFlow() throws Exception {
       // app.actionForChoice1();

    }
}