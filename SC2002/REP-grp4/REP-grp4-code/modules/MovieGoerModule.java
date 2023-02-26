package modules;

import java.util.Scanner;

import objects.Movie;
import objects.MovieGoer;
import objects.MovieTicket;
import objects.Review;
import objects.Showing;

import java.util.Collections;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import comparators.SortByRating;
import comparators.SortBySales;
import databases.MovieDB;
import databases.MovieGoerDB;
import enums.MovieStatusType;
import interfaces.LoginInterface;
import interfaces.ModuleInterface;

/**
 * Represents the module for MovieGoer functions.
 * 
 * @author Ivan Loke
 * @version 1.0
 * @since 2022-11-11
 */

public class MovieGoerModule implements ModuleInterface, LoginInterface {
    
    /**
     * Scanner to query users for inputs.
     */
    private Scanner sc;

    /**
     * Stores whether the MovieGoer is currently logged in.
     */
    private boolean isLoggedIn;

    /**
     * Stores the instance of a MovieGoer object who is currently logged in.
     */
    private MovieGoer movieGoerObj;

    /**
     * List of all MovieGoers in the database.
     */
    private ArrayList<MovieGoer> movieGoerList;

    /**
     * List of all Movies in the database.
     */
    private ArrayList<Movie> allMovies;

    /**
     * Creates a new MovieGoerModule for MovieGoer functionality.
     * 
     * @param sc        Scanner to query for user's inputs.
     * @param isGuest   boolean on whether to initialise the 
     *                  MovieGoerModule with guest account.
     */
    public MovieGoerModule(Scanner sc, boolean isGuest) {
        this.sc = sc;
        this.isLoggedIn = isGuest;
    }

    /**
     * Runs the MovieGoerModule.
     */
    public void run() {
        MovieDB movieDB = new MovieDB();
        MovieGoerDB movieGoerDB = new MovieGoerDB();
        System.out.println("***********************************************");
        System.out.println("MOBLIMA -- Movie Goer Module:\n");
        allMovies = movieDB.read();
        movieGoerList = movieGoerDB.read();
        String keywords = "";

        if (!isLoggedIn) {
            login();
            System.out.println("Welcome, " + movieGoerObj.getName() + "!\n");
        } else {
            movieGoerObj = null;
            System.out.println("Welcome, Guest!\n");
        }

        int input = 0;
        while (input != 9) {
            System.out.println("***********************************************");
            if (movieGoerObj == null) {
                System.out.println("MOBLIMA -- Movie Goer Module (Movie Goer: Guest):");
            } else {
                System.out.println("MOBLIMA -- Movie Goer Module (Movie Goer: " + movieGoerObj.getName() + "):");
            }
            System.out.println("[1] Search Movies\n"
                    + "[2] List Movies\n"
                    + "[3] View Movie Details\n"
                    + "[4] Book Seats\n"
                    + "[5] View Booking History\n"
                    + "[6] List Top 5 Movies Based on Sales\n"
                    + "[7] List Top 5 Movies Based on Ratings\n"
                    + "[8] Add Movie Review\n"
                    + "[9] Back");
            System.out.print("Please select an option: ");
            try {
                input = sc.nextInt();
                sc.nextLine();
                System.out.println("***********************************************");
                switch (input) {
                    case 1:
                        System.out.println("MOBLIMA -- Movie Goer Module (Search Movies): ");
                        System.out.print("Please enter the keyword(s): ");
                        keywords = sc.nextLine();
                        System.out.println();
                        printMoviesSearch(keywords, false);
                        keywords = "";
                        break;
                    case 2:
                        System.out.println("MOBLIMA -- Movie Goer Module (List Movies): ");
                        System.out.println();
                        printMoviesSearch(keywords, false);
                        break;
                    case 3:
                        System.out.println("MOBLIMA -- Movie Goer Module (View Movie Details): ");
                        System.out.print("Please enter the keywords: ");
                        keywords = sc.nextLine();
                        System.out.println();
                        printMoviesSearch(keywords, true);
                        break;

                    case 4:
                        if (movieGoerObj == null) {
                            login();
                        }
                        BookingModule bookingModule = new BookingModule(sc, movieGoerObj);
                        bookingModule.run();
                        movieGoerDB.write(movieGoerList);
                        break;

                    case 5:
                        System.out.println("MOBLIMA -- Movie Goer Module (View Booking History): ");
                        System.out.println();
                        if (movieGoerObj == null) {
                            login();
                        }
                        if (!movieGoerObj.getMovieTicketList().isEmpty()) {
                            ArrayList<MovieTicket> mtList = movieGoerObj.getMovieTicketList();
                            for (MovieTicket m : mtList) {
                                m.printTicket();
                                System.out.println();
                            }
                        } else {
                            System.out.println("No Past Bookings.\n");
                        }
                        break;

                    case 6:
                        printMovieListBySales();
                        break;

                    case 7:
                        printMovieListByRating();
                        break;

                    case 8:
                        addMovieReview();
                        break;

                    case 9:
                        break;

                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Error: Invalid Choice, Please try again.\n");
                sc.nextLine();
            }
        }
    }

    /**
     * Prompts the user to login as MovieGoer.
     */
    public void login() {
        while (movieGoerObj == null) {
            System.out.print("Please enter your username: ");
            String username = sc.nextLine();

            boolean validUsername = false;
            for (MovieGoer mg : movieGoerList) {
                String mgUsername = mg.getName();
                if (mgUsername.equals(username)) {
                    validUsername = true;
                    movieGoerObj = mg;
                }
            }
            if (validUsername) {
                isLoggedIn = true;
            } else {
                System.out.println("Error: Username not found. Please try again.");
            }
        }
        System.out.println();
    }

    /**
     * Prints Movie title and description matching input phrase.
     * 
     * @param phrase   all movies in database will be checked if it contains this
     *                 string in its title.
     * @param detailed represnts if other Movie details, apart from title, will be
     *                 printed.
     */
    private void printMoviesSearch(String phrase, boolean detailed) {
        int index = 0;
        System.out.println("Results: ");
        for (Movie m : allMovies) {
            if (m.getStatus() == (MovieStatusType.NOW_SHOWING)
                    && (m.getEndOfShowingDate().compareTo(LocalDateTime.now())) > 0
                    && (m.getTitle().toLowerCase()).contains(phrase.toLowerCase())) {
                if (detailed == false) {
                    System.out.println("(" + (index + 1) + ") " + m.getTitle());
                    index++;
                } else {
                    System.out.println("Title: " + m.getTitle());
                    System.out.println("Movie Status: " + m.getStatus());
                    System.out.println("Movie Synopsis: " + m.getSynopsis());
                    System.out.println("Movie Director: " + m.getDirector());
                    System.out.print("Cast Members: ");
                    ArrayList<String> castMembers = m.getCast();
                    System.out.println(String.join(", ", castMembers));
                    System.out.print("Reviews: ");
                    if (!m.getReviewList().isEmpty()) {
                        for (Review movieReview : m.getReviewList()) {
                            System.out.println();
                            System.out.println("Name: " + movieReview.getName());
                            System.out.println("Rating: " + movieReview.getRating());
                            System.out.println("Review: " + movieReview.getReview());
                        }
                    } else {
                        System.out.println("-");
                    }

                    System.out.println("\nOverall rating: " + m.getOverallRating());
                    System.out.println("Sales Count: " + m.getSaleCount());
                    System.out.println("Movie Type: " + m.getType() + "\n");
                    LocalDate currentEOSD = m.getEndOfShowingDate().toLocalDate();
                    System.out.println(
                            "End of showing: " + currentEOSD.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\n");
                }
            }
        }
    }

    /**
     * Method prints top 5 Movies which are NOW_SHOWING based on Review ratings.
     */
    private void printMovieListByRating() {
        System.out.println("MOBLIMA -- Movie Goer Module (Top 5 Movies Based on Rating): ");
        System.out.println();
        int counter = 1;
        MovieDB movieDB = new MovieDB();
        allMovies = movieDB.read();
        Collections.sort(allMovies, new SortByRating());
        for (Movie m : allMovies) {
            if (m.getStatus() == MovieStatusType.NOW_SHOWING) {
                System.out.print("[" + counter + "] ");
                System.out.print(m.getTitle());
                System.out.print(" - Overall Rating: " + m.getOverallRating() + "\n");
                counter++;
            }

            if (counter >= 6) {
                break;
            }
        }
    }

    /**
     * Method prints top 5 Movies which are NOW_SHOWING based on ticket sales.
     */
    private void printMovieListBySales() {
        System.out.println("MOBLIMA -- Movie Goer Module (Top 5 Movies Based on Sales): ");
        System.out.println();
        int counter = 1;
        MovieDB movieDB = new MovieDB();
        allMovies = movieDB.read();
        Collections.sort(allMovies, new SortBySales());
        for (Movie m : allMovies) {
            if (m.getStatus() == MovieStatusType.NOW_SHOWING) {
                System.out.print("[" + counter + "] ");
                System.out.print(m.getTitle());
                System.out.print(" - Total Sales: " + m.getSaleCount() + "\n");
                counter++;
            }

            if (counter >= 6) {
                break;
            }
        }
    }

    /**
     * Method allows user to add a Reivew object to a Movie Object which they had
     * previously bought tickets for.
     */
    private void addMovieReview() {
        System.out.println("MOBLIMA -- Movie Goer Module (Add Movie Review): \n");
        MovieDB movieDB = new MovieDB();
        allMovies = movieDB.read();

        if (movieGoerObj == null) {
            login();
        }

        ArrayList<MovieTicket> mtList = movieGoerObj.getMovieTicketList();
        ArrayList<Movie> pastMovieList = new ArrayList<Movie>();
        for (MovieTicket mt : mtList) {
            Showing s = mt.getShowing();
            if (LocalDateTime.now().compareTo(s.getShowTime()) < 0) {
                Movie m = s.getMovie();
                if (!pastMovieList.contains(m)) {
                    pastMovieList.add(m);
                }
            }
        }

        if (pastMovieList.isEmpty()) {
            System.out.println("No Past Movies.");
        } else {
            for (int i = 0; i < pastMovieList.size(); i++) {
                Movie m = pastMovieList.get(i);
                System.out.println("[" + (i + 1) + "]: " + m.getTitle());
            }
            System.out.print("Enter the movie which you would like to review: ");
            int choice = sc.nextInt();
            sc.nextLine();

            Movie chosenMovie = pastMovieList.get(choice - 1);
            boolean foundMovie = false;

            for (Movie m : allMovies) {
                if (m.equals(chosenMovie)) {
                    int rating = 0;
                    while (true) {
                        System.out.print("Key in your Movie Rating (1-5): ");
                        rating = sc.nextInt();
                        sc.nextLine();

                        if (rating < 1 || rating > 5) {
                            System.out.println("Error: Invalid Rating. Please try again.");
                        } else {
                            break;
                        }
                    }

                    System.out.print("Key in your Movie Review: ");
                    String reviewString = sc.nextLine();

                    m.addReview(movieGoerObj.getName(), rating, reviewString);
                    movieDB.write(allMovies);
                    System.out.println("Movie Review Added.");
                    foundMovie = true;
                    break;
                }
            }

            if (!foundMovie) {
                System.out.println("No Past Movies.");
            }
        }
    }
}
