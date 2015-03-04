package com.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by sanjanar on 04/03/15.
 */
public class SubMenuTest {
    TestReaderWriter testReaderWriter;
    SubMenu subMenu;
    BibliotecaApp bookBibliotecaApp;
    BibliotecaApp movieBibliotecaApp;


    @Before
    public void setUp() {
        testReaderWriter = new TestReaderWriter();
        subMenu = new SubMenu(testReaderWriter);
        bookBibliotecaApp = new BibliotecaApp(BibliotecaApp.createLibraryWithBooks());
        movieBibliotecaApp=new BibliotecaApp(BibliotecaApp.createLibraryWithMovies());

    }
    @Test
    public void shouldCallDisplayDetailsWhen1IsPassed() throws Exception {
        bookBibliotecaApp = mock(BibliotecaApp.class);
        List<Item> list=bookBibliotecaApp.getItemListByCategory("book");
        subMenu =new SubMenu(testReaderWriter,bookBibliotecaApp);
        when(bookBibliotecaApp.displayMainMenu()).thenReturn("Main menu ");
        when(bookBibliotecaApp.displayItemDetails(list)).thenReturn("Book Details ");
        testReaderWriter.consoleInput("1\n4");
        String expected = "Main menu Items available for borrowingBook Details Main menu Successful Exit";
       subMenu.menu(list, bookBibliotecaApp, testReaderWriter);
        assertEquals(expected, testReaderWriter.consoleOutput());

    }

    @Test
    public void shouldCallCheckOutBookMenuActionWhen2IsPassed() throws Exception {
        bookBibliotecaApp = mock(BibliotecaApp.class);
        List<Item> list=bookBibliotecaApp.getItemListByCategory("book");
        subMenu =new SubMenu(testReaderWriter,bookBibliotecaApp);

        when(bookBibliotecaApp.displayMainMenu()).thenReturn("Main menu ");
        when(bookBibliotecaApp.displaySpecificItemListDetails(list)).thenReturn("Book Details ");
        when(bookBibliotecaApp.validTitle("S C J P")).thenReturn(true);
        testReaderWriter.consoleInput("2\nS C J P\n4");

        String expected = "Main menu Book Details Select a Item by entering the titleMain menu Successful Exit";

        subMenu.menu(list,bookBibliotecaApp,testReaderWriter);

        assertEquals(expected,testReaderWriter.consoleOutput());

    }

    @Test
    public void shouldCallReturnBookMenuActionWhen3IsPassed() throws Exception {
        bookBibliotecaApp = mock(BibliotecaApp.class);
        List<Item> list=bookBibliotecaApp.getItemListByCategory("book");

        subMenu =new SubMenu(testReaderWriter,bookBibliotecaApp);

        when(bookBibliotecaApp.displayMainMenu()).thenReturn("main menu");
        when(bookBibliotecaApp.borrowedItems()).thenReturn("Book Details");
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        when(bookBibliotecaApp.getItem("s c j p")).thenReturn(book1);

        when(bookBibliotecaApp.returnBookToLibrary(book1)).thenReturn(true);
        testReaderWriter.consoleInput("3\nS C J P\n4");
        String expected = "main menuBook DetailsSelect a Item by entering the titlemain menuSuccessful Exit";
        subMenu.menu(list,bookBibliotecaApp,testReaderWriter);
        assertEquals(expected,testReaderWriter.consoleOutput());

    }

    @Test
    public void shouldReturnWhen4IsPassed() throws Exception {
        testReaderWriter.consoleInput("4");
        List<Item> list=bookBibliotecaApp.getItemListByCategory("book");
        subMenu =new SubMenu(testReaderWriter,bookBibliotecaApp);

        subMenu.menu(list,bookBibliotecaApp,testReaderWriter);
        assertEquals("Main menu \n" +
                " 1 ---- Item Details \n" +
                " 2 ---- Check Out a Item\n" +
                " 3 ---- Return a Item\n" +
                " 4 ---- Exit\n" +
                "Enter your choiceSuccessful Exit",testReaderWriter.consoleOutput());
    }
}
