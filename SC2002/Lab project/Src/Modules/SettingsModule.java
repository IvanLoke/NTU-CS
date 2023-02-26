package Modules;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;

import Databases.CineplexDB;
import Databases.SettingsDB;

import Enums.AgeType;
import Enums.CinemaType;
import Enums.DayType;
import Enums.MovieType;
import Enums.SeatType;
import Interfaces.ModuleInterface;

import Objects.Settings;
import Objects.Cineplex;
import Objects.Cinema;
import Objects.Showing;
/**
 * Represents the entry point for admin users to edit settings.
 * 
 * @author Aaron Chua
 * @version 1.0
 * @since 2022-11-11
 */
public class SettingsModule implements ModuleInterface {

    /**
     * Scanner to query users for inputs.
     */
    private Scanner sc;

    /**
     * The actual settings object to be edited.
     */
    private Settings settingsObj;

    /**
     * Creates a new SettingsModule with a scanner to receive user input.
     * 
     * @param sc The Scanner.
     */
    public SettingsModule(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Runs the SettingsModule.
     */
    public void run() {
        SettingsDB settingsDB = new SettingsDB();
        settingsObj = settingsDB.read();

        if (settingsObj == null) {
            settingsObj = new Settings();
        }

        boolean running = true;
        while (running) {
            System.out.println("***********************************************");
            System.out.println("MOBLIMA -- Admin -- Settings Module:");
            System.out.println("[1] Display All Prices");
            System.out.println("[2] Display Holiday Dates");
            System.out.println("[3] Edit Prices for Movie Type");
            System.out.println("[4] Edit Prices for Cinema Class");
            System.out.println("[5] Edit Prices for Movie Goer Age");
            System.out.println("[6] Edit Prices for Day Type");
            System.out.println("[7] Edit Prices for Seat Type");
            System.out.println("[8] Edit Holiday Dates");
            System.out.println("[9] Back");
            System.out.print("Please Select Option: ");
            int choice = sc.nextInt();
            sc.nextLine();
            System.out.println("***********************************************");
            switch (choice) {
                case 1:
                    System.out.println("MOBLIMA -- Admin -- Settings Module (Display All Prices):");
                    System.out.println("Movie Types \t\t| Cinema Classes \t| Movie Goer Age\t| Days\t\t\t| Seat Types");
                    for (int i = 0; i < 3; i++) {
                        String movieType = MovieType.values()[i].name();
                        String cinemaClass = CinemaType.values()[i].name();
                        String ageType = AgeType.values()[i].name();
                        String dayType = DayType.values()[i].name();
                        String seatType = SeatType.values()[i].name();
                        if (i == 0) {
                            System.out.println(
                                movieType + ": $" + settingsObj.getMovieTypePrice(movieType) + "\t\t| " +
                                    cinemaClass + ": x" + settingsObj.getCinemaClassPrice(cinemaClass) + "\t| " +
                                    ageType + ": x" + settingsObj.getAgeTypePrice(ageType) + "\t\t| " +
                                    dayType + ": x" + settingsObj.getDayTypePrice(dayType) + "\t| " +
                                    seatType + ": x" + settingsObj.getSeatTypePrice(seatType));
                        } else if (i == 1) {
                            System.out.println(
                                movieType + ": $" + settingsObj.getMovieTypePrice(movieType) + "\t\t| " +
                                    cinemaClass + ": x" + settingsObj.getCinemaClassPrice(cinemaClass) + "\t\t| " +
                                    ageType + ": x" + settingsObj.getAgeTypePrice(ageType) + "\t\t| " +
                                    dayType + ": x" + settingsObj.getDayTypePrice(dayType) + "\t\t| " +
                                    seatType + ": x" + settingsObj.getSeatTypePrice(seatType));
                        } else {
                            System.out.println(
                                movieType + ": $" + settingsObj.getMovieTypePrice(movieType) + "\t| " +
                                    cinemaClass + ": x" + settingsObj.getCinemaClassPrice(cinemaClass) + "\t\t| " +
                                    ageType + ": x" + settingsObj.getAgeTypePrice(ageType) + "\t\t| " +
                                    dayType + ": x" + settingsObj.getDayTypePrice(dayType) + "\t| " +
                                    seatType + ": x" + settingsObj.getSeatTypePrice(seatType));
                        }
                    }
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t| " + SeatType.values()[3].name() + ": x"
                        + settingsObj.getSeatTypePrice(SeatType.values()[3].name()));
                    break;

                case 2:
                    System.out.println("MOBLIMA -- Admin -- Settings Module (Display Holiday Dates):");
                    displayHolidayDates();
                    break;

                case 3:
                    System.out.println("MOBLIMA -- Admin -- Settings Module (Edit Movie Type Prices):");
                    int movieTypeChoice = 0;
                    while (movieTypeChoice < 1 || movieTypeChoice > 3) {
                        System.out.println("\n[1] Regular");
                        System.out.println("[2] 3D");
                        System.out.println("[3] Blockbuster");

                        System.out.print("\nPlease enter Movie Type: ");
                        movieTypeChoice = sc.nextInt();
                        sc.nextLine();
                    }
                    askNewPriceAndWriteToDB(settingsDB, choice - 2, MovieType.values()[movieTypeChoice - 1].name());
                    break;

                case 4:
                    System.out.println("MOBLIMA -- Admin -- Settings Module (Edit Cinema Class Prices):");
                    int cinemaClassChoice = 0;
                    while (cinemaClassChoice < 1 || cinemaClassChoice > 3) {
                        System.out.println("\n[1] Gold Class");
                        System.out.println("[2] Deluxe");
                        System.out.println("[3] Regular");

                        System.out.print("\nPlease enter Cinema Class: ");
                        cinemaClassChoice = sc.nextInt();
                        sc.nextLine();
                    }
                    askNewPriceAndWriteToDB(settingsDB, choice - 2, CinemaType.values()[cinemaClassChoice - 1].name());
                    break;

                case 5:
                    System.out.println("MOBLIMA -- Admin -- Settings Module (Edit Movie Goer Age Prices):");
                    int movieGoerAgeChoice = 0;
                    while (movieGoerAgeChoice < 1 || movieGoerAgeChoice > 3) {
                        System.out.println("\n[1] Child");
                        System.out.println("[2] Adult");
                        System.out.println("[3] Senior");
                        System.out.print("\nPlease enter Age Type: ");
                        movieGoerAgeChoice = sc.nextInt();
                        sc.nextLine();
                    }
                    askNewPriceAndWriteToDB(settingsDB, choice - 2, AgeType.values()[movieGoerAgeChoice - 1].name());
                    break;

                case 6:
                    System.out.println("MOBLIMA -- Admin -- Settings Module (Edit Day Type Prices):");
                    int dayTypeChoice = 0;
                    while (dayTypeChoice < 1 || dayTypeChoice > 3) {
                        System.out.println("\n[1] Weekend");
                        System.out.println("[2] Weekday");
                        System.out.println("[3] Public Holiday");
                        System.out.print("\nPlease enter Day Type: ");
                        dayTypeChoice = sc.nextInt();
                        sc.nextLine();
                    }
                    askNewPriceAndWriteToDB(settingsDB, choice - 2, DayType.values()[dayTypeChoice - 1].name());
                    break;

                case 7:
                    System.out.println("MOBLIMA -- Admin -- Settings Module (Edit Seat Type Prices):");
                    int seatTypeChoice = 0;
                    while (seatTypeChoice < 1 || seatTypeChoice > 4) {
                        System.out.println("\n[1] Regular");
                        System.out.println("[2] Couple");
                        System.out.println("[3] Elite");
                        System.out.println("[4] Ultima");
                        System.out.print("Please enter Seat Type: ");
                        seatTypeChoice = sc.nextInt();
                        sc.nextLine();
                    }
                    askNewPriceAndWriteToDB(settingsDB, choice - 2, SeatType.values()[seatTypeChoice - 1].name());
                    break;

                case 8:
                    System.out.println("MOBLIMA -- Admin -- Settings Module (Edit Holiday Dates):");
                    CineplexDB cineplexDB = new CineplexDB();
                    ArrayList<Cineplex> cineplexList = cineplexDB.read();
                    displayHolidayDates();
                    System.out.print(
                        "\nEnter Holiday Date (dd-MM-yyyy, eg. 01-01-2022).\nIf input date is not in list, then input date will be added to list\nIf input date is already in list, then input date will be removed from list: ");
                    String inputDateString = sc.nextLine();

                    int dateAlreadyExistsAtPosition = -1;
                    ArrayList<LocalDate> holidayDates = settingsObj.getHolidayDates();
                    DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate inputDate = LocalDate.parse(inputDateString, dFormatter);

                    for (int i = 0; i < holidayDates.size(); i++) {
                        if (holidayDates.get(i).equals(inputDate)) {
                        dateAlreadyExistsAtPosition = i;
                        break;
                        }
                    }

                    if (dateAlreadyExistsAtPosition != -1) {
                        holidayDates.remove(dateAlreadyExistsAtPosition);
                        dateAlreadyExistsAtPosition = -1;
                        for (Cineplex c : cineplexList) {
                            for (Cinema cin : c.getListOfCinemas()) {
                                for (Showing s : cin.getShowList()) {
                                    LocalDateTime showingTime = s.getShowTime();
                                    LocalDate showingDay = showingTime.toLocalDate();
                                    if (showingDay.equals(inputDate)) {
                                        DayOfWeek dayofWeek = DayOfWeek.from(showingTime);
                                        int day = dayofWeek.getValue();
                                        if (day == 6 || day == 7) {
                                            s.setDayType(DayType.WEEKEND);
                                        } else {
                                            s.setDayType(DayType.WEEKDAY);
                                        }
                                    }
                                }
                            }
                        }
                        System.out.println("Holiday Date successfully removed.");
                    } else {
                        holidayDates.add(inputDate);
                        for (Cineplex c : cineplexList) {
                            for (Cinema cin : c.getListOfCinemas()) {
                                for (Showing s : cin.getShowList()) {
                                    LocalDateTime showingTime = s.getShowTime();
                                    LocalDate showingDay = showingTime.toLocalDate();
                                    if (showingDay.equals(inputDate)) {
                                        s.setDayType(DayType.PUBLIC_HOLIDAY);
                                    }
                                }
                            }
                        }
                        System.out.println("Holiday Date successfully added.");
                    }
                    Collections.sort(holidayDates);
                    settingsObj.setHolidayDates(holidayDates);
                    settingsDB.write(settingsObj);
                    cineplexDB.write(cineplexList);
                    break;

                case 9:
                    running = false;
                    break;

                default:
                    System.out.println("Error: Invalid Choice, Please try again.\n");
                    break;
            }
        }
    }

    /**
     * Prints all holidays dates.
     */
    private void displayHolidayDates() {
        System.out.println("Current Holiday Dates are: ");
        ArrayList<LocalDate> holidayDates = settingsObj.getHolidayDates();
        DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (LocalDate date : holidayDates) {
            System.out.println(date.format(dFormatter));
        }
    }

    /**
     * Prints old prices of and allows user to set new prices.
     * 
     * @param settingsDB an object instance of the settingsDB class.
     * @param choice     the user's selection of which price factor.
     * @param typeChoice the user's selection of which price factor option.
     */
    private void askNewPriceAndWriteToDB(SettingsDB settingsDB, int choice, String typeChoice) {
        double price = 0;
        switch (choice) {
            case 1:
                price = settingsObj.getMovieTypePrice(typeChoice);
                break;

            case 2:
                price = settingsObj.getCinemaClassPrice(typeChoice);
                break;

            case 3:
                price = settingsObj.getAgeTypePrice(typeChoice);
                break;

            case 4:
                price = settingsObj.getDayTypePrice(typeChoice);
                break;

            case 5:
                price = settingsObj.getSeatTypePrice(typeChoice);
                break;

            default:
                break;
        }
        System.out.println("Old Price was: " + price);

        System.out.print("Set New Price: ");
        double newPrice = sc.nextDouble();
        sc.nextLine();

        System.out.println("Price successfully updated!");
        switch (choice) {
            case 1:
                settingsObj.setMovieTypePrice(typeChoice, newPrice);
                break;

            case 2:
                settingsObj.setCinemaClassPrice(typeChoice, newPrice);
                break;

            case 3:
                settingsObj.setAgeTypePrice(typeChoice, newPrice);
                break;

            case 4:
                settingsObj.setDayTypePrice(typeChoice, newPrice);
                break;

            case 5:
                settingsObj.setSeatTypePrice(typeChoice, newPrice);

            default:
                break;
        }
        settingsDB.write(settingsObj);
    }
}
