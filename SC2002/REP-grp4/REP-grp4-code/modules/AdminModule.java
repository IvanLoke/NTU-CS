package modules;

import java.util.Scanner;
import java.util.ArrayList;

import databases.AdminDB;
import interfaces.LoginInterface;
import interfaces.ModuleInterface;
import objects.Admin;

/**
 * Represents the Module for Admin functions.
 * @author Chay Hui Xiang
 * @version 1.0
 * @since 2022-11-11
 */
public class AdminModule implements ModuleInterface, LoginInterface {

    /**
     * Scanner to query users for inputs.
     */
    private Scanner sc;

    /**
     * Stores whether the Admin is currently logged in.
     */
    private boolean isLoggedIn;

    /**
     * List of all Admins in the database.
     */
    private ArrayList<Admin> adminList;

    /**
     * Creates a new AdminModule for Admin functionality.
     * 
     * @param sc Scanner to query for user's inputs.
     */
    public AdminModule(Scanner sc) {
        this.sc = sc;
        this.isLoggedIn = false;
    }

    /**
     * Runs the AdminModule.
     */
    public void run() {
        AdminDB adminDB = new AdminDB();
        adminList = (ArrayList<Admin>) adminDB.read();

        login();

        boolean running = true;
        while (running) {
            System.out.println("***********************************************");
            System.out.println("MOBLIMA -- Admin Module:");
            System.out.println("[1] Create/Update/Remove movie listing");
            System.out.println("[2] Create/Update/Remove cinema showtimes and the movies to be shown");
            System.out.println("[3] Configure system settings");
            System.out.println("[4] Back");
            System.out.print("Please Select Option: ");
            try {
                int choice = sc.nextInt();
                System.out.println("***********************************************");
                switch (choice) {
                    case 1:
                        MovieListingModule movieListingModule = new MovieListingModule(sc);
                        movieListingModule.run();
                        break;

                    case 2:
                        CineplexModule cineplexModule = new CineplexModule(sc);
                        cineplexModule.run();
                        break;

                    case 3:
                        SettingsModule settingsModule = new SettingsModule(sc);
                        settingsModule.run();
                        break;

                    case 4:
                        running = false;
                        break;

                    default:
                        System.out.println("Error: Invalid Choice, Please try again.\n");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: Invalid Choice, Please try again.\n");
                sc.nextLine();
            }
        }
    }

    /**
     * Prompts the user to login as Admin.
     */
    public void login() {
        while (!isLoggedIn) {
            System.out.println("***********************************************");
            System.out.println("MOBLIMA -- Admin Module:\n");
            System.out.print("Please enter your username: ");
            String username = sc.next();
            System.out.print("Please enter your password: ");
            String password = sc.next();

            for (Admin admin : adminList) {
                if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                    isLoggedIn = true;
                    System.out.println("\nWelcome, " + username + "!\n");
                    break;
                }
            }
            if (!isLoggedIn) {
                System.out.println("Error: Invalid Credentials, Please try again.\n");
            }
        }
    }
}
