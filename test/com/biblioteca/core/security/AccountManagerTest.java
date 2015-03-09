package com.biblioteca.core.security;

import com.biblioteca.core.models.UserInfo;
import com.biblioteca.core.security.AccountManager;
import com.biblioteca.ui.controller.LibraryManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sanjanar on 04/03/15.
 */
public class AccountManagerTest {
    AccountManager manager;

    @Before
    public void setUp() {
        manager = new AccountManager();
    }

    @Test
    public void shouldHaveAInitializedListOfUserDetails() throws Exception {
        assertEquals(2, manager.getUserDetails().size());
    }

    @Test
    public void shouldCheckIfAnyoneIsLoggedIn() throws Exception {
        UserInfo userInfo = manager.users.get(0);
        userInfo.setLoggedIn(true);
        assertEquals(new UserInfo("lib-1000", "user1", "anu", "anu@ymail.com", "8921679023", "user"), manager.checkForLoggedInUser());
    }

    @Test
    public void shouldReturnNullWhenNoUserIsLoggedIn() throws Exception {
        assertNull(manager.checkForLoggedInUser());
    }

    @Test
    public void shouldLogOutALoggedInUser() throws Exception {
        UserInfo userInfo=manager.users.get(0);
        userInfo.setLoggedIn(true);
        manager.logOut(userInfo);
        assertFalse(userInfo.isLoggedIn());
    }
}
