package com.biblioteca.core.security;

import com.biblioteca.core.models.UserInfo;
import com.biblioteca.ui.controller.LibraryManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanjanar on 04/03/15.
 */
public class AccountManager {
    public List<UserInfo> users=new ArrayList<UserInfo>();
    public AccountManager() {
        this.users = initializeUserDetails();
    }

    private List<UserInfo> initializeUserDetails() {
        UserInfo user1=new UserInfo("lib-1000","user1","anu","anu@ymail.com","8921679023","user");
        users.add(user1);
        UserInfo user2=new UserInfo("lib-1001","user2","sinu","sinu@gmail.com","7823091287","librarian");
        users.add(user2);
        return users;
    }


    public List<UserInfo> getUserDetails() {
        return users;
    }

    public boolean login(String libraryNumber, String password) {
        for(UserInfo user:users){
            if(user.getLibraryNumber().equals(libraryNumber) && user.getPassword().equals(password)){
                user.setLoggedIn(true);
                return true;
            }
        }
        return false;
    }

    public UserInfo checkForLoggedInUser() {
        for (UserInfo user:users){
            if(user.isLoggedIn()==true){
                return user;
            }
        }
        return null;
    }

    public UserInfo checkCredentialsAndReturnUser(String libraryNumber, String pswd, LibraryManager manager) {
        List<UserInfo> users= getUserDetails();
        for(UserInfo user:users){
            if(user.getLibraryNumber().equals(libraryNumber) && user.getPassword().equals(pswd)){
                if(user.getCategory()=="librarian"){
                    manager.generateReport();
                }
                user.setLoggedIn(true);
                return user;
            }
        }
        return null;
    }
}
