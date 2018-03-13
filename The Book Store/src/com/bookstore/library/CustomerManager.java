package com.bookstore.library;

import java.io.Serializable;
import java.util.ArrayList;

class Customer implements Serializable {
    //General Details
    private String name;
    private String address;
    private String mobileNo;
    private double balance = 0.0;
    private Book[] rentBooks = null;
    private boolean isRestricted = false;
    private Login login;

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
    //Constructor


    public Customer(String name, String address , String mobileNo) {
        this.name = name;
        this.address = address;
        this.mobileNo = mobileNo;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getMobileNo() {
        return mobileNo;
    }
    public double getBalance() {
        return balance;
    }
    public Book[] getRentBooks() {
        return rentBooks;
    }
    public boolean isRestricted() {
        return isRestricted;
    }

}



public class CustomerManager {
    private ArrayList<Customer> allCustomer;
    private ResourcesManager resourcesManager;
    public CustomerManager(ResourcesManager resourcesManager) {
        this.resourcesManager = resourcesManager;
        allCustomer = resourcesManager.getAllCustomers();
    }

}
