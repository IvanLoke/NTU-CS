package Modules;

import java.util.Scanner;

/**
 * Represents the main entry point into the entire MOBLIMA application.
 * @author Chay Hui Xiang
 * @version 1.0
 * @since 2022-11-11
 */
public class MOBLIMA {

    /**
     * Main Function of the MOBLIMA class. Entry point of the entire MOBLIMA
     * application.
     * Requests for the user's target role (ie. Admin/MovieGoer) and
     * calls the respective module (ie. AdminModule and MovieGoerModule).
     * @param args String arguments when the entry point of the application is called.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n***********************************************");

        System.out.println(" __  __  ____  ____  _      _____ __  __");
        System.out.println("|  \\/  |/ __ \\|  _ \\| |    |_   _|  \\/  |   /\\");
        System.out.println("| \\  / | |  | | |_) | |      | | | \\  / |  /  \\");
        System.out.println("| |\\/| | |  | |  _ <| |      | | | |\\/| | / /\\ \\");
        System.out.println("| |  | | |__| | |_) | |____ _| |_| |  | |/ ____ \\");
        System.out.println("|_|  |_|\\____/|____/|______|_____|_|  |_/_/    \\_\\");
        System.out.println();
        int choice;
        boolean running = true;
        while (running) {
            System.out.println("***********************************************");
            System.out.println("MOBLIMA -- Main Menu:");
            System.out.println("[1] Movie Goer");
            System.out.println("[2] Admin");
            System.out.println("[3] Guest");
            System.out.println("[4] Exit");
            System.out.print("Please Select Target Role: ");
            try {
                choice = sc.nextInt();
                sc.nextLine();
                System.out.println("***********************************************");
                switch (choice) {
                    case 1:
                        MovieGoerModule movieGoerModule = new MovieGoerModule(sc, false);
                        movieGoerModule.run();
                        break;

                    case 2:
                        AdminModule adminModule = new AdminModule(sc);
                        adminModule.run();
                        break;

                    case 3:
                        MovieGoerModule guestModule = new MovieGoerModule(sc, true);
                        guestModule.run();
                        break;

                    case 4:
                        System.out.println("Bye Bye!");
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
        sc.close();
    }
}
