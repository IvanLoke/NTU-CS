package Modules;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

import Databases.CineplexDB;
import Databases.MovieDB;
import Databases.SettingsDB;

import Enums.AgeType;
import Enums.CinemaType;
import Enums.MovieType;
import Enums.DayType;
import Enums.MovieStatusType;
import Enums.SeatType;

import Interfaces.ModuleInterface;

import Objects.Cinema;
import Objects.Cineplex;
import Objects.Movie;
import Objects.MovieGoer;
import Objects.MovieTicket;
import Objects.Settings;
import Objects.Showing;

/**
 * Represents the Module for MovieGoers to book seats.
 * 
 * @author Chay Hui Xiang
 * @version 1.0
 * @since 2022-11-11
 */
public class BookingModule implements ModuleInterface {

    /**
     * Scanner to query users for inputs.
     */
    private Scanner sc;

    /**
     * Stores the current Application Settings.
     */
    private Settings settingsObj;

    /**
     * List of all Cineplexes.
     */
    private ArrayList<Cineplex> cineplexList;

    /**
     * List of Cinemas within the selected Cineplex.
     */
    private ArrayList<Cinema> cinemaList;

    /**
     * Cineplex that the user has selected for booking.
     */
    private Cineplex cineplexObj;

    /**
     * MovieGoer who is booking a Seat.
     */
    private MovieGoer movieGoerObj;

    /**
     * Creates a new BookingModule to query the user for their booking options.
     * 
     * @param sc        Scanner to query for user's inputs.
     * @param movieGoer MovieGoer who is booking a Seat with the BookingModule.
     */
    public BookingModule(Scanner sc, MovieGoer movieGoer) {
        this.sc = sc;
        this.movieGoerObj = movieGoer;
    }

    /**
     * Runs the BookingModule.
     */
    public void run() {
        System.out.println("***********************************************");
        System.out.println("MOBLIMA -- Movie Goer -- Booking Module:");

        CineplexDB cineplexDB = new CineplexDB();
        cineplexList = cineplexDB.read();

        SettingsDB settingsDB = new SettingsDB();
        settingsObj = settingsDB.read();

        selectCineplex();

        boolean running = true;
        while (running) {
            System.out.println("***********************************************");
            System.out.println("MOBLIMA -- Movie Goer -- Booking Module (Selected Cineplex: "
                    + cineplexObj.getCineplexName() + "):");
            System.out.println("[1] Display All Cinema Showings");
            System.out.println("[2] Display Cinema Showings");
            System.out.println("[3] Check Showing Seat Availability");
            System.out.println("[4] Select, Book & Purchase Tickets");
            System.out.println("[5] Reselect Cineplex");
            System.out.println("[6] Back");
            System.out.print("Please enter your choice: ");

            try {
                int choice = sc.nextInt();
                System.out.println("***********************************************");

                switch (choice) {
                    case 1:
                        displayAllCinemaShowings();
                        break;

                    case 2:
                        displayCinemaShowings();
                        break;

                    case 3:
                        checkSeatAvailability();
                        break;

                    case 4:
                        bookSeats();
                        break;

                    case 5:
                        System.out.println("MOBLIMA -- Movie Goer -- Booking Module (Reselect Cineplex):\n");
                        selectCineplex();
                        break;

                    case 6:
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
     * Displays all Showings from all Cinemas within the selected Cineplex.
     */
    private void displayAllCinemaShowings() {
        System.out.println("MOBLIMA -- Movie Goer -- Booking Module (Display All Cinema Showings):\n");
        for (Cinema cinema : cinemaList) {
            System.out.println("Cinema " + cinema.getCinemaNum() + " (" + cinema.getCinemaType() + ") :");
            cinema.displayAvailableShows();
            System.out.println();
        }
    }

    /**
     * Displays all Showings from a particular Cinema selected by the user.
     */
    private void displayCinemaShowings() {
        System.out.println("MOBLIMA -- Movie Goer -- Booking Module (Display Cinema Showings):\n");
        Cinema cinemaObj = selectCinema();
        System.out.println("***********************************************");
        System.out.println("Cinema " + cinemaObj.getCinemaNum() + " (" + cinemaObj.getCinemaType() + ")");
        System.out.println("***********************************************");
        cinemaObj.displayAvailableShows();
        System.out.println();
    }

    /**
     * Prints all Seats from a particular Showing selected by the user.
     */
    private void checkSeatAvailability() {
        System.out.println("MOBLIMA -- Movie Goer -- Booking Module (Check Seat Availability):\n");
        Cinema cinemaObj = selectCinema();
        System.out.println("***********************************************");
        System.out.println("Cinema " + cinemaObj.getCinemaNum() + " (" + cinemaObj.getCinemaType() + ")");
        System.out.println("***********************************************");
        cinemaObj.displayAvailableShows();

        if (cinemaObj.getShowList().size() == 0) {
            return;
        }

        Showing showingObj = selectShowing(cinemaObj);
        showingObj.printSeating();
    }

    /**
     * Handles all Seat Booking by the user.
     */
    private void bookSeats() {
        System.out.println("MOBLIMA -- Movie Goer -- Booking Module (Book Seats):\n");
        Cinema cinemaObj = selectCinema();
        System.out.println("***********************************************");
        System.out.println("Cinema " + cinemaObj.getCinemaNum() + " (" + cinemaObj.getCinemaType() + ")");
        System.out.println("***********************************************");
        cinemaObj.displayAvailableShows();

        // This is to check if there are showings that are NOW_SHOWING
        // There are showings that are not NOW_SHOWING, which leads to erronous logic
        // flow
        int showsAvailable = 0;
        ArrayList<Showing> showList = cinemaObj.getShowList();
        for (Showing showing : showList) {
            if (showing.getMovie().getStatus() == MovieStatusType.NOW_SHOWING) {
                showsAvailable = 1;
            }
        }
        if (showsAvailable == 0) {
            return;
        }

        MovieDB movieDB = new MovieDB();
        ArrayList<Movie> movieList = movieDB.read();

        Showing showingObj = selectShowing(cinemaObj);
        Movie movieObj = showingObj.getMovie();
        System.out.println("***********************************************");
        System.out.println("Chosen Movie: " + movieObj.getTitle());

        HashMap<String, Double> idPriceMap = new HashMap<String, Double>();
        double totalPrice = 0;
        int ticketCount = 1;
        while (true) {
            int ticketsBooked = 1;
            do {
                showingObj.printSeating();
                System.out.print("Ticket " + ticketCount + " | ");
                System.out.print("Please enter seat to book (eg. A1): ");
                String seatId = sc.next();

                try {
                    if (showingObj.isAvailable(seatId)) {
                        Movie movie = showingObj.getMovie();
                        MovieType movieType = movie.getType();
                        CinemaType cinemaClass = cinemaObj.getCinemaType();
                        AgeType movieGoerAge = movieGoerObj.getAgeType();
                        DayType showingDayType = showingObj.getDayType();
                        LocalDateTime showingTime = showingObj.getShowTime();
                        SeatType seatType = showingObj.getSeatType(seatId);
                        double price = calculatePrice(movieType, cinemaClass, movieGoerAge, showingDayType, seatType,
                                showingTime);

                        totalPrice += price;
                        idPriceMap.put(seatId, price);
                        showingObj.assignSeat(movieGoerObj, seatId);

                        // book adjacent seat if couple / ultima
                        if (seatType == SeatType.COUPLE || seatType == SeatType.ULTIMA) {
                            int col = Character.getNumericValue(seatId.charAt(1));
                            int adjCol = col % 2 == 0 ? col - 1 : col + 1;
                            String adjSeatId = "" + seatId.charAt(0) + Character.forDigit(adjCol, 10);

                            totalPrice += price;
                            idPriceMap.put(adjSeatId, price);
                            showingObj.assignSeat(movieGoerObj, adjSeatId);
                            ticketsBooked++;
                        }
                        break;
                    } else {
                        System.out.print("Ticket " + ticketCount + " | ");
                        System.out.println("Error: Seat already occupied, Please try again.\n");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid Seat. Please try again.");
                }
            } while (true);

            showingObj.printSeating();

            char confirmInput;
            do {
                System.out.print("Ticket " + ticketCount + " | Would you like to book more tickets? (Y/N): ");
                confirmInput = Character.toUpperCase(sc.next().charAt(0));
                if (confirmInput == 'Y' || confirmInput == 'N') {
                    break;
                } else {
                    System.out.println("Error: Invalid input, Please try again.\n");
                }
            } while (true);

            if (confirmInput == 'N') {
                break;
            }

            ticketCount += ticketsBooked;
        }

        String payment = "";
        do {
            System.out.println("[1] Cash");
            System.out.println("[2] Debit Card");
            System.out.println("[3] Credit Card");
            System.out.println("[4] Mobile Transfer");
            System.out.println("[5] Vouchers");
            System.out.print("Ticket " + ticketCount + " | Please key in your preferred payment method: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice < 1 || choice > 5) {
                System.out.println("Error: Invalid Choice. Please try again.");
            } else {
                switch (choice) {
                    case 1:
                        payment = "Cash";
                        break;

                    case 2:
                        payment = "Debit Card";
                        break;

                    case 3:
                        payment = "Credit Card";
                        break;

                    case 4:
                        payment = "Mobile Transfer";
                        break;

                    case 5:
                        payment = "Vouchers";
                        break;

                    default:
                        break;
                }
                break;
            }
        } while (true);

        System.out.println("Please confirm the details of your booking: ");
        System.out.println("Movie:\t\t" + movieObj.getTitle());
        System.out.println("Cineplex:\t" + cineplexObj.getCineplexName());
        System.out.println("Cinema:\t\t" + cinemaObj.getCinemaNum());
        System.out.println("Price:\t\t" + totalPrice);
        System.out.println("Payment: \t" + payment);
        System.out.println("Time:\t\t" + showingObj.getFormattedTime());
        System.out.print("Seats:\t\t");
        for (String seatId : idPriceMap.keySet()) {
            System.out.print(seatId + " ");
        }
        System.out.println();
        char confirmInput;
        do {
            System.out.print("Confirm (Y/N): ");
            confirmInput = Character.toUpperCase(sc.next().charAt(0));
            if (confirmInput == 'Y' || confirmInput == 'N') {
                break;
            } else {
                System.out.println("Error: Invalid input, Please try again.\n");
            }
        } while (true);
        System.out.println("***********************************************");
        if (confirmInput == 'Y') {
            System.out.println("Booking Successful! Here are the details of your Movie Ticket(s): \n");
            for (Map.Entry<String, Double> entry : idPriceMap.entrySet()) {
                String seatId = entry.getKey();
                double price = entry.getValue();
                MovieTicket movieTicket = new MovieTicket(movieGoerObj, price, showingObj, cineplexObj, cinemaObj,
                        seatId);
                movieGoerObj.addMovieTicket(movieTicket);

                // increment movie sales
                movieObj.incrementSaleCount();
                for (Movie m : movieList) {
                    if (m.equals(movieObj)) {
                        m.incrementSaleCount();
                    }
                }

                System.out.println("Movie Ticket " + seatId + ": ");
                movieTicket.printTicket();
                System.out.println();
            }
        } else {
            for (String seatId : idPriceMap.keySet()) {
                showingObj.unassignSeat(seatId);
            }
            System.out.println("Booking Cancelled!\n");
        }
        CineplexDB cineplexDB = new CineplexDB();
        cineplexDB.write(cineplexList);
        movieDB.write(movieList);
    }

    // SELECTION HELPERS
    /**
     * Prompts the user to select a Cineplex.
     */
    private void selectCineplex() {
        int choice;
        do {
            int index = 0;
            for (Cineplex cineplex : cineplexList) {
                index++;
                System.out.println("[" + index + "]: " + cineplex.getCineplexName());
            }
            int cineplexSize = cineplexList.size();
            System.out.print("Please select your cineplex of choice: ");
            choice = sc.nextInt();
            choice = choice < 1 || choice > cineplexSize ? 0 : choice;
            if (choice == 0) {
                System.out.println("Error: Invalid choice, Please try again.\n");
            } else {
                break;
            }
        } while (true);
        cineplexObj = cineplexList.get(choice - 1);
        cinemaList = cineplexObj.getListOfCinemas();
    }

    /**
     * Prompts the user to select a Cinema.
     * 
     * @return the cinema selected by the user
     */
    private Cinema selectCinema() {

        for (Cinema cinema : cinemaList) {
            System.out.println("Cinema " + cinema.getCinemaNum() + " (" + cinema.getCinemaType() + ")");
        }

        int cinemaSize = cinemaList.size();
        int cinemaChoice;

        do {
            System.out.print("Please select your cinema of choice (1-" + cinemaSize + "): ");
            cinemaChoice = sc.nextInt();
            cinemaChoice = cinemaChoice < 1 || cinemaChoice > cinemaSize ? 0 : cinemaChoice;
            if (cinemaChoice == 0) {
                System.out.println("Error: Invalid choice, Please try again.\n");
            } else {
                break;
            }
        } while (true);
        Cinema cinema = cinemaList.get(cinemaChoice - 1);
        return cinema;
    }

    /**
     * Prompts the user to select a Showing.
     * 
     * @param cinema the cinema where the Showings are taking place
     * @return the Showing selected by the user.
     */
    private Showing selectShowing(Cinema cinema) {
        Showing showing;
        do {
            System.out.print("Please enter the show of your choice: ");
            int showingId = sc.nextInt();
            showing = cinema.searchShow(showingId);
            if (showing == null) {
                System.out.println("Error: Invalid choice, Please try again. \n");
            } else {
                break;
            }
        } while (true);

        return showing;
    }

    // PRICE HELPER
    /**
     * Algorithm to determine the price of the MovieTicket.
     * 
     * @param movieType      type of the Movie booked (ie. REGULAR, 3D,
     *                       BLOCKBUSTER).
     * @param cinemaClass    class of the Cinema booked (ie. GOLD_GLASS, DELUXE,
     *                       REGULAR).
     * @param movieGoerAge   age class of the MovieGoer who is booking the
     *                       MovieTicket (ie. CHILD, ADULT, SENIOR).
     * @param showingDayType reflects whether the Movie is aired/shown on a
     *                       WEEKDAY/WEEKEND/PUBLIC_HOLIDAY.
     * @param seatType       type of the Seat booked (ie. REGULAR, COUPLE, ELITE,
     *                       ULTIMA).
     * @param showTime       time for which the Showing will be aired.
     * @return calculated price of the MovieTicket.
     */
    private double calculatePrice(MovieType movieType, CinemaType cinemaClass, AgeType movieGoerAge,
            DayType showingDayType, SeatType seatType, LocalDateTime showTime) {
        String movieTypeChoice = movieType.name();
        String cinemaClassChoice = cinemaClass.name();
        String movieGoerAgeChoice = movieGoerAge.name();
        // String showingDayTypeChoice = showingDayType.name();
        String seatTypeChoice = seatType.name();

        // MovieType
        double price = settingsObj.getMovieTypePrice(movieTypeChoice);

        // CinemaClass
        double cinemaClassPrice = settingsObj.getCinemaClassPrice(cinemaClassChoice);
        price *= cinemaClassPrice;

        // MovieGoer Age + DayType
        double movieGoerAgePrice = settingsObj.getAgeTypePrice(movieGoerAgeChoice);
        DayOfWeek dow = DayOfWeek.from(showTime);
        int day = dow.getValue();
        if (showingDayType == DayType.PUBLIC_HOLIDAY) {
            price *= settingsObj.getDayTypePrice("PUBLIC_HOLIDAY");
        } else if (showingDayType == DayType.WEEKDAY && showTime.getHour() < 18
                && (movieGoerAge == AgeType.SENIOR || movieGoerAge == AgeType.CHILD)) {
            price *= movieGoerAgePrice;
        } else if ((day == 5 && showTime.getHour() >= 18) || showingDayType == DayType.WEEKEND) {
            price *= settingsObj.getDayTypePrice("WEEKEND");
            price *= settingsObj.getAgeTypePrice("ADULT");
        } else {
            price *= settingsObj.getDayTypePrice("WEEKDAY");
            price *= settingsObj.getAgeTypePrice("ADULT");
        }

        // SeatType
        double seatTypePrice = settingsObj.getSeatTypePrice(seatTypeChoice);
        price *= seatTypePrice;

        return Math.round(price * 100) / 100;
    }
}
