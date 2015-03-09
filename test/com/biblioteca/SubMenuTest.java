package com.biblioteca;

import com.biblioteca.core.models.Book;
import com.biblioteca.core.models.Item;
import com.biblioteca.core.models.UserInfo;
import com.biblioteca.core.security.AccountManager;
import com.biblioteca.ui.controller.LibraryManager;
import com.biblioteca.ui.menuaction.SubMenu;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by sanjanar on 04/03/15.
 */
public class SubMenuTest {
    TestReaderWriter testReaderWriter;
    SubMenu subMenu;
    LibraryManager bookLibraryManager;
    LibraryManager movieLibraryManager;
    AccountManager manager;

    @Before
    public void setUp() {
        testReaderWriter = new TestReaderWriter();
        subMenu = new SubMenu(testReaderWriter);
        bookLibraryManager = new LibraryManager(LibraryManager.createLibraryWithBooks());
        movieLibraryManager =new LibraryManager(LibraryManager.createLibraryWithMovies());
        manager = new AccountManager();


    }
    @Test
    public void shouldCallDisplayDetailsWhen1IsPassed() throws Exception {
        bookLibraryManager = mock(LibraryManager.class);
        List<Item> list= bookLibraryManager.getItemListByCategory("book");
        subMenu =new SubMenu(testReaderWriter, bookLibraryManager);
        when(bookLibraryManager.displayMainMenu()).thenReturn("Main menu ");
        when(bookLibraryManager.displayItemDetails(list)).thenReturn("Book Details ");
        testReaderWriter.consoleInput("1\n4");
        String expected = "Main menu Items available for borrowingBook Details Main menu Successful Exit from Menu";
       subMenu.menu(list, bookLibraryManager, testReaderWriter, manager);
        assertEquals(expected, testReaderWriter.consoleOutput());

    }

    @Test
    public void shouldCallCheckOutBookMenuActionWhen2IsPassedWithValidCredentials() throws Exception {
        Book book = new Book("S C J P", "Kathy Serra", 2006);

       bookLibraryManager = mock(LibraryManager.class);
        Item newBook=bookLibraryManager.getItem("s c j p");
       subMenu =new SubMenu(testReaderWriter, bookLibraryManager);
       UserInfo user=new UserInfo("lib-1000","user1","anu","anu@ymail.com","8921679023","user");

       when(bookLibraryManager.displayMainMenu()).thenReturn("Main menu ");
        when(bookLibraryManager.displaySpecificItemListDetails(bookLibraryManager.getItemListByCategory("book"))).thenReturn("Book Details ");
        when(bookLibraryManager.validTitle("S C J P")).thenReturn(true);
        when(bookLibraryManager.getItem("S C J P")).thenReturn(book);
        doNothing().when(bookLibraryManager).checkOutFromLibrary(book, user);
        testReaderWriter.consoleInput("2\nlib-1000\nuser1\nS C J P\n4");

        String expected = "Main menu Enter library numberEnter passwordBook Details Select a Item by entering the titleS C J P is checked out successfullyMain menu Successful Exit from Menu";

        subMenu.menu(bookLibraryManager.getItemListByCategory("book"), bookLibraryManager,testReaderWriter, manager);

        assertEquals(expected,testReaderWriter.consoleOutput());

    }

    @Test
    public void shouldCallCheckOutBookMenuActionWhen2IsPassedWithoutValidCredentials() throws Exception {
        bookLibraryManager = mock(LibraryManager.class);
        List<Item> list= bookLibraryManager.getItemListByCategory("book");
        subMenu =new SubMenu(testReaderWriter, bookLibraryManager);

        when(bookLibraryManager.displayMainMenu()).thenReturn("Main menu ");
        when(bookLibraryManager.displaySpecificItemListDetails(list)).thenReturn("Book Details ");
        when(bookLibraryManager.validTitle("S C J P")).thenReturn(true);
        testReaderWriter.consoleInput("2\nlib-1001\nasds\n4");

        String expected = "Main menu Enter library numberEnter passwordNot a valid userMain menu Successful Exit from Menu";

        subMenu.menu(list, bookLibraryManager,testReaderWriter, manager);

        assertEquals(expected,testReaderWriter.consoleOutput());

    }

    @Test
    public void shouldCallReturnBookMenuActionWhen3IsPassedWithValidCredentials() throws Exception {
        bookLibraryManager = mock(LibraryManager.class);
        List<Item> list= bookLibraryManager.getItemListByCategory("book");
        UserInfo user=new UserInfo("lib-1000","user1","anu","anu@ymail.com","8921679023","user");

        subMenu =new SubMenu(testReaderWriter, bookLibraryManager);

        when(bookLibraryManager.displayMainMenu()).thenReturn("main menu");
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        when(bookLibraryManager.getItem("S C J P")).thenReturn(book1);
        when(bookLibraryManager.displayItemDetailsInTableForm(list)).thenReturn("books");
        doNothing().when(bookLibraryManager).returnBookToLibrary(book1, user);
        testReaderWriter.consoleInput("3\nlib-1000\nuser1\nS C J P\n4");
        String expected = "main menuEnter library numberEnter passwordbooksSelect a Item by entering the titleS C J P returned to the librarymain menuSuccessful Exit from Menu";
        subMenu.menu(list, bookLibraryManager,testReaderWriter, manager);
        assertEquals(expected,testReaderWriter.consoleOutput());

    }

    @Test
    public void shouldCallReturnBookMenuActionWhen3IsPassedWithoutValidCredentials() throws Exception {
        bookLibraryManager = mock(LibraryManager.class);
        List<Item> list= bookLibraryManager.getItemListByCategory("book");

        subMenu =new SubMenu(testReaderWriter, bookLibraryManager);
        UserInfo user=new UserInfo("lib-1000","user1","anu","anu@ymail.com","8921679023","user");

        when(bookLibraryManager.displayMainMenu()).thenReturn("main menu");
        Book book1 = new Book("S C J P", "Kathy Serra", 2006);
        when(bookLibraryManager.getItem("S C J P")).thenReturn(book1);
        when(bookLibraryManager.displaySpecificItemListDetails(list)).thenReturn("books");
        doNothing().when(bookLibraryManager).returnBookToLibrary(book1, user);
        testReaderWriter.consoleInput("3\nlib-1000\nuasd\n4");
        String expected = "main menuEnter library numberEnter passwordNot a valid usermain menuSuccessful Exit from Menu";
        subMenu.menu(list, bookLibraryManager,testReaderWriter, manager);
        assertEquals(expected,testReaderWriter.consoleOutput());

    }

    @Test
    public void shouldReturnWhen4IsPassed() throws Exception {
        testReaderWriter.consoleInput("4");
        List<Item> list= bookLibraryManager.getItemListByCategory("book");
        subMenu =new SubMenu(testReaderWriter, bookLibraryManager);

        subMenu.menu(list, bookLibraryManager,testReaderWriter, manager);
        assertEquals("Main menu \n" +
                " 1 ---- Item Details \n" +
                " 2 ---- Check Out a Item\n" +
                " 3 ---- Return a Item\n" +
                " 4 ---- Exit\n" +
                "Enter your choiceSuccessful Exit from Menu",testReaderWriter.consoleOutput());
    }
}
