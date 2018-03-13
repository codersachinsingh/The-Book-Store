package com.bookstore.library;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
class Headers {
    void header1(String str,int width , char ch) {
        String result = "";
        for (int i = 0;i< (width-str.length())/2;i++) {
            result += ch;
        }
        result += str;
        for (int i = 0;i<(width-str.length())/2;i++) {
            result += ch;
        }
        System.out.println(result);
    }
    void header2(String str, int width, char ch) {
        String result = "";
        for (int i = 0;i<width;i++) {
            result += ch;
        }
        result+="\n";
        for (int i = 0;i< (width-str.length())/2;i++) {
            result += " ";
        }
        result += str;
        for (int i = 0;i< (width-str.length())/2;i++) {
            result += " ";
        }
        result+="\n";
        for (int i = 0;i<width;i++) {
            result += ch;
        }
        System.out.println(result);
    }

    void line(int width, char ch) {
        String str = "";
        for (int i = 0;i<width;i++) {
            str += ch;
        }
        System.out.println(str);
    }
}
class UIAssistance {
    protected Scanner scanner = new Scanner(System.in);
    protected BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    protected Headers headers = new Headers();

    Login getLoginByUser() {
        String userName=null,password=null;

        try {
            System.out.println("Enter User Name : ");
            userName = reader.readLine();
            System.out.println("Enter Your Password : ");
            Console console = System.console();
            if (console!=null) {
                password = String.valueOf(console.readPassword());
            }
            else {
                System.out.println("Console not found. Your password will visible");
                password = reader.readLine();
            }

        } catch (IOException e) {
            System.out.println("Error while performing IO");
        }
        return new Login(userName,password);
    }
}

public class UserInterface extends UIAssistance {
    //------------------Private Members----------------------------------
    private ResourcesManager resourcesManager;
    private BookManagementTools bookManagementTools;
    private CustomerManager customerManager;
    private Administration admin;
    private int lineWidth = 75;
    private char lineChar = '-';
    //-------------------Constructors-----------------------------------
    public UserInterface(ResourcesManager resourcesManager , BookManagementTools bookManagementTools , CustomerManager customerManager , Administration admin) {
        this.bookManagementTools = bookManagementTools;
        this.resourcesManager = resourcesManager;
        this.customerManager = customerManager;
        this.admin = admin;
        headers = new Headers();
    }


    void adminMainMenu() {
        headers.header2("Administration Menu",lineWidth,lineChar);
        int option;
        do {
            System.out.println("1. Add New Book to Library.\n" +
                    "2. Edit a Book or Update Stock.\n" +
                    "3. Restrict a Customer.\n" +
                    "4. Change Admin Password.\n" +
                    "5. Back.\n");
            headers.line(lineWidth,lineChar);
            System.out.print("==> ");
            option = scanner.nextInt();
            headers.line(lineWidth,lineChar);
            switch (option) {
                case 1:
                    try {
                        bookManagementTools.addNewBook();
                    } catch (IOException e) {
                        System.out.println("Error while adding the book");
                    }
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                    default:
                        System.out.println("Invalid Input");
            }
        }while(option !=5);
    }

    //
    void showMainMenu() {
        int option;
        do {
            headers.header2("The Book Store",lineWidth,lineChar);
            showLastFiveBooks();
            System.out.println("1. View books by Categories." +
                    "\n2. Search Book" +
                    "\n3. Return a Book" +
                    "\n4. Administration" +
                    "\n5. Need Help?" +
                    "\n6. Exit");
        headers.line(lineWidth,lineChar);
        System.out.print("==> Choose your option : ");
        option = scanner.nextInt();
        headers.line(lineWidth,lineChar);


            switch (option) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    {
                     int op;

                        do {
                                headers.header2("Admin Login",lineWidth,lineChar);
                                System.out.print("1. Login\n" +
                                        "2. Logout\n" +
                                        "3. Back\n");
                                headers.line(lineWidth,lineChar);
                                System.out.print("==> ");
                                op = scanner.nextInt();
                                headers.line(lineWidth,lineChar);
                                switch (op) {
                                    case 1: {
                                                if (admin.getLoginStatus()) {
                                                    System.out.println("**Already Logged In. No Need to Enter Password**");
                                                    this.adminMainMenu();
                                                }
                                                else {
                                                    if(admin.login(super.getLoginByUser())) {
                                                        System.out.println("***Login Successful!***");
                                                        this.adminMainMenu();
                                                    }

                                                    else {
                                                        System.out.println("~~You Entered a Wrong Password or User Name~~");
                                                        System.out.println("Password Hint : " + admin.getForgetPasswordText());
                                                    }
                                                }
                                            }
                                        break;
                                    case 2: {
                                                admin.logout();
                                                System.out.println("Your are Logged out");
                                                op = 3;
                                            }
                                        break;
                                    case 3:
                                        break;
                                        default:
                                            System.out.println("Invalid Input");
                                }
                        }while(op != 3);
                    }
                    break;
                case 5:
                    break;
                case 6:
                    break;
                    default:
                        System.out.println("Invalid Input. Try Again");
            }
        }while(option!=6);

        System.out.println("Good Bye");
    }

    void showLastFiveBooks() {
        System.out.println("***Recently Added Books***");
        Book[] s = bookManagementTools.getLastFiveAddedBooks();
        headers.line(lineWidth,lineChar);
        System.out.printf("  %s %35s %25s\n","Title","Author","Date");
        headers.line(lineWidth,lineChar);
        for (int i = 0;i<s.length;i++) {
            System.out.printf("* %s %35s\n",s[i].getTitle(),s[i].getAuthor());
        }
        headers.line(lineWidth,lineChar);
    }

}