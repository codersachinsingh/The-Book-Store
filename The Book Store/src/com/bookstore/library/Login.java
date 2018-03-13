package com.bookstore.library;

public class Login {
    private String userName;
    private String password;
    private String forgerPasswordText;

    public Login() {
        userName = null;
        password = null;
        forgerPasswordText = null;
    }

    public Login(String userName, String password , String forgerPasswordText) {
        this.userName = userName;
        this.password = password;
        this.forgerPasswordText = forgerPasswordText;
    }

    public Login(String userName , String password) {
        this.userName = userName;
        this.password = password;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public String getForgerPasswordText() {
        return forgerPasswordText;
    }

    public void setForgerPasswordText(String forgerPasswordText) {
        this.forgerPasswordText = forgerPasswordText;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isMatched(Login l) {
        if (this.userName.equals(l.userName) && this.password.equals(l.password))
            return true;
        else
            return false;
    }
}
