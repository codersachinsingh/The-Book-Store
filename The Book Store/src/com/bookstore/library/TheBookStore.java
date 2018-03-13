package com.bookstore.library;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

public class TheBookStore {
    public static void main(String[] args) {
        // initialise available resource
        ResourcesManager resourcesManager = new ResourcesManager();

        /* ------------------------------------------------------
        * PROCEDURE
        * ***********IF SOFTWARE IS USED FOR FIRST TIME.***************
        * -------------------------------------------------------------
        * */

        /*
        * checking for master password file.
        * if not found , reconfigure the software with new admin password
        * */

        if (!resourcesManager.getMasterPswdFile().exists()) {
            //Login details for admin
            Login adminLogin = new Login();
            System.out.println("************WELCOME TO THE BOOK STORE**********");
            System.out.println("------------Initial Software Setup-------------");


            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                System.out.println("Enter Administrator Name : ");
                adminLogin.setUserName(br.readLine());
                System.out.println("Enter Password : ");
                Console console = System.console();

                /*check for console is available not
                 * if available the readPassword() method will not echo the password on screen
                 * */

                if (console!=null) {
                    adminLogin.setPassword(String.valueOf(console.readPassword()));
                }

                //password will visible
                else {
                    adminLogin.setPassword(br.readLine());
                }

                System.out.println("Enter something that we'll display in case you forger your password");
                adminLogin.setForgerPasswordText(br.readLine());
            }
            catch (IOException e) {
                System.out.println("Error occurred while reading input");
            }

            //save master admin password to file.
            resourcesManager.saveMasterPassword(adminLogin);
        }

        /*
        * NORMAL ROUTINE OF PROGRAM
        * */


        Administration admin = new Administration(resourcesManager.loadMasterPassword());
        // LOAD THE RESOURCES IF AVAILABLE
        resourcesManager.loadResources();
        BookManagementTools booksManager = new BookManagementTools(resourcesManager);
        CustomerManager customerManager = new CustomerManager(resourcesManager);
        UserInterface userInterface = new UserInterface(resourcesManager,booksManager,customerManager,admin);
        userInterface.showMainMenu();

    }
}
