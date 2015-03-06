package com.biblioteca;

import com.biblioteca.core.models.UserInfo;
import com.biblioteca.security.AccountManager;
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
    public void shouldLoginWithProperCredentials() throws Exception {
        assertTrue(manager.login("lib-1000", "user1"));
    }

    @Test
    public void shouldNotLoginWithInValidCredentials() throws Exception {
        assertFalse(manager.login("lib-1010", "user"));
    }

    @Test
    public void shouldCheckIfAnyoneIsLoggedIn() throws Exception {
        UserInfo userInfo = manager.users.get(0);
        userInfo.setLoggedIn(true);
        assertEquals(new UserInfo("lib-1000", "user1", "anu", "anu@ymail.com", "8921679023"), manager.checkForLoggedInUser());
    }

    @Test
    public void shouldReturnNullWhenNoUserIsLoggedIn() throws Exception {
        assertNull(manager.checkForLoggedInUser());
    }

    @Test
    public void shouldAcceptInputAndLogin() throws Exception {
        assertTrue(manager.checkCredentials("lib-1000", "user1"));
    }
}
