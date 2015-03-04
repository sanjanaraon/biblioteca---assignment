package com.biblioteca;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sanjanar on 04/03/15.
 */
public class LoggerHelperTest {
    LoggerHelper helper;

    @Before
    public void setUp(){
        helper = new LoggerHelper();
    }
    @Test
    public void shouldHaveAInitializedListOfUserDetails() throws Exception {
        assertEquals(2, helper.getUserDetails().size());
    }

    @Test
    public void shouldLoginWithProperCredentials() throws Exception {
        assertTrue(helper.login("lib-1000","user1"));
    }

    @Test
    public void shouldNotLoginWithInValidCredentials() throws Exception {
        assertFalse(helper.login("lib-1010","user"));
    }

    @Test
    public void shouldCheckIfAnyoneIsLoggedIn() throws Exception {
        UserInfo userInfo=helper.users.get(0);
        userInfo.setLoggedIn(true);
        assertEquals(new UserInfo("lib-1000","user1","anu","anu@ymail.com","8921679023"),helper.checkForLoggedInUser());
    }
}
