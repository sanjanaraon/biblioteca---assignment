package com.biblioteca;

/**
 * Created by sanjanar on 04/03/15.
 */
public class UserInfo {
    private String libraryNumber;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private boolean loggedIn;

    public UserInfo(String libraryNumber, String password, String name, String email, String phoneNumber) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.loggedIn = false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    @Override
    public boolean equals(Object o) {
        UserInfo newUser = (UserInfo) o;
        if (this.libraryNumber == newUser.libraryNumber && this.password == newUser.password &&
                this.name == newUser.name && this.email == newUser.email && this.phoneNumber == newUser.phoneNumber){
            return true;
        }
            return false;
    }

}
