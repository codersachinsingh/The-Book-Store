package com.bookstore.library;

public class Administration {
    private final Login login;
    private boolean loginStatus = false;
    Administration(Login login) {
        this.login = login;
    }

    //returns true if given login matched with admin login
    boolean getAccessToken(Login l) {
        return this.login.isMatched(l);
    }
    void logout() {
        loginStatus = false;
    }
    boolean getLoginStatus() {
        return loginStatus;
    }
    boolean login(Login l) {
        if (this.login.isMatched(l)) {
            loginStatus = true;
            return loginStatus;
        }
        else {
            return loginStatus;
        }
    }

    String getForgetPasswordText() {
        return login.getForgerPasswordText();
    }
}
