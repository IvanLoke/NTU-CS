package modules;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import objects.Cinema;
import objects.Cineplex;
import objects.Movie;
import objects.Showing;

import databases.CineplexDB;
import databases.MovieDB;
import enums.MovieStatusType;
import enums.MovieType;
import interfaces.ModuleInterface;

/**
 * Represents the entry point for Admins to create/ update / remove
 * movieListings.
 * 
 * @author Ang Kai Jun
 * @version 1.0
 * @since 2022-11-11
 */
public class MovieListingModule implements ModuleInterface {

    /**
     * Scanner to query users for inputs.
     */
    private Scanner sc;

    /**
     * The list of Movies stored retrieved from the MovieDB.
     */
    private ArrayList<Movie> movieList;

    /**
     * Creates a new MovieListingModule with a scanner to receive user input.
     * 
     * @param sc The Scanner.
     */
    public MovieListingModule(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Runs the MovieListingModule.
     */
    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("***********************************************");
            System.out.println("MOBLIMA -- Admin -- Movie Listing Module:");
            MovieDB movieDB = new MovieDB();
            movieList = (ArrayList<Movie>) movieDB.read();
            if (movieList == null) {
                movieList = new ArrayList<Movie>();
            }
            System.out.println("[1] Display All Movie Listings");
            System.out.println("[2] Create New Movie Listing");
            System.out.println("[3] Update Movie Listing");
            System.out.println("[4] Remove Movie Listing");
            System.out.println("[5] Back");
            System.out.print("Please enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                System.out.println("***********************************************");
                System.out.println("MOBLIMA -- Movie Listing Module (Display All Movie Listings):");
                ArrayList<String> movieNames = new ArrayList<String>();
                for (Movie m : movieList) {
                    if (m.getStatus() != MovieStatusType.END_OF_SHOWING) {
                        String name = m.getTitle();
                        movieNames.add(name);
                    }
                }

                if (movieNames.isEmpty()) {
                    System.out.println("No Movies Found. ");
                } else {
                    System.out.println("List of movies: ");
                    int index = 1;
                    for (String name : movieNames) {
                        System.out.println("(" + index + "): " + name);
                        index++;
                    }
                }
                break;

                case 2:
                    createNewMovieListing(movieDB);
                    break;

                case 3:
                    updateMovieListing(movieDB);
                    break;

                case 4:
                    removeMovieListing(movieDB);
                    break;

                case 5:
                    running = false;
                    break;
            }
        }
    }

    /**
     * Create a new Movie Listing.
     * 
     * @param movieDB movieDB object which is used to write the new MovieList to the
     *                movie.dat file.
     */
    private void createNewMovieListing(MovieDB movieDB) {
        System.out.println("***********************************************");
        System.out.println("MOBLIMA -- Movie Listing Module (Create New Movie Listing):");
        System.out.print("Input Movie Title: ");
        String title = sc.nextLine();
        int choice = 0;
        while (true) {
            System.out.println("\n[1] COMING SOON");
            System.out.println("[2] PREVIEW");
            System.out.println("[3] NOW SHOWING");
            System.out.println("[4] END OF SHOWING");
            System.out.print("\nInput Movie Status: ");
            choice = sc.nextInt();
            sc.nextLine();

            if (choice <= 4 && choice >= 1) {
                break;
            } else {
                System.out.println("Error: Invalid Movie Status. Please try again.\n");
            }
        }
        MovieStatusType status = MovieStatusType.values()[choice - 1];

        System.out.print("\nInput Movie Synopsis: ");
        String synopsis = sc.nextLine();

        System.out.print("\nInput Movie Director: ");
        String director = sc.nextLine();

        ArrayList<String> cast = getCast();

        while (true) {
            System.out.println("\n[1] Regular");
            System.out.println("[2] 3D");
            System.out.println("[3] Blockbuster");
            System.out.print("\nInput Movie Type: ");
            choice = sc.nextInt();
            sc.nextLine();
            if (choice <= 3 && choice >= 1) {
                break;
            } else {
                System.out.println("Error: Invalid Movie Type. Please try again.");
            }
        }
        MovieType type = MovieType.values()[choice - 1];

        while (true) {
            try {
                System.out.print("\nInput Movie End Of Showing Date (dd-MM-yyyy): ");
                String dateString = sc.nextLine();
                DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                LocalDateTime endOfShowingDate = LocalDateTime.parse(dateString + " 00:00", dtFormatter);

                Movie newMovie = new Movie(title, status, synopsis, director, cast, type, endOfShowingDate);
                movieList.add(newMovie);
                movieDB.write(movieList);
                break;
            } catch (Exception e) {
                System.out.println("Error: Invalid Date Format. Please try again.");
            }
        }

        System.out.println("New Movie Listing successfully added.");
        System.out.println("***********************************************");
    }

    /**
     * Update infomation about the MovieListing (Status, Sale Count, Type, End Of
     * Showing Date).
     * 
     * @param movieDB MovieDB object which is used to write the new MovieListing to
     *                the movie.dat file.
     */
    private void updateMovieListing(MovieDB movieDB) {
        System.out.println("***********************************************");
        System.out.println("MOBLIMA -- Movie Listing Module (Update Existing Movie Listing):");

        Movie movieToUpdate = selectMovie();
        if (movieToUpdate == null) {
            System.out.println("No Movies Available.");
            return;
        }

        for (Movie m : movieList) {
            if (m.equals(movieToUpdate)) {
                updateMovie(m);
            }
        }
        movieDB.write(movieList);
        System.out.println("Movie Listing successfully updated.");
        System.out.println("***********************************************");
    }

    /**
     * Removes a MovieListing by updating the MovieStatus as "END_OF_SHOWING".
     * 
     * @param movieDB movieDB object which is used to write the new MovieList to the
     *                movie.dat file.
     */
    private void removeMovieListing(MovieDB movieDB) {
        System.out.println("***********************************************");
        System.out.println("MOBLIMA -- Movie Listing Module (Remove Movie Listing):");

        Movie movieToRemove = selectMovie();
        if (movieToRemove == null) {
            System.out.println("No Movies Available.");
            return;
        }

        CineplexDB cineplexDB = new CineplexDB();
        ArrayList<Cineplex> cineplexList = cineplexDB.read();

        for (Movie m : movieList) {
            if (m.equals(movieToRemove)) {
                m.setStatus(MovieStatusType.END_OF_SHOWING);
                for (Cineplex c : cineplexList) {
                    c.removeMovieShowings(m);
                }
            }
        }

        movieDB.write(movieList);
        cineplexDB.write(cineplexList);
        System.out.println("Movie Listing successfully removed.");
        System.out.println("***********************************************");
    }

    /**
     * Helper Function to obtain the updated Movie Information.
     * 
     * @param movie Movie object that is being updated.
     */
    private void updateMovie(Movie movie) {
        boolean run = true;
        CineplexDB cineplexDB = new CineplexDB();
        ArrayList<Cineplex> cineplexList = cineplexDB.read();

        do {
            System.out.println("******************************");
            System.out.println("What do you want to update?");
            System.out.println("Possible List of Updates:");
            System.out.println("[1] Status");
            System.out.println("[2] Sale Count");
            System.out.println("[3] Type");
            System.out.println("[4] End Of Showing Date");
            System.out.println("[5] Back / Done");
            System.out.print("Please enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            int updateChoice = 0;

            switch (choice) {
                case 1:
                    while (true) {
                        System.out.println("Current Movie Status: " + movie.getStatus());
                        System.out.println("\n[1] COMING SOON");
                        System.out.println("[2] PREVIEW");
                        System.out.println("[3] NOW SHOWING");
                        System.out.println("[4] END OF SHOWING");
                        System.out.print("\nInput New Movie Status: ");
                        updateChoice = sc.nextInt();
                        sc.nextLine();
                        if (updateChoice <= 4 && updateChoice >= 1) {
                            break;
                        } else {
                            System.out.println("Error: Invalid Movie Status. Please try again.");
                        }
                    }
                    MovieStatusType status = MovieStatusType.values()[updateChoice - 1];
                    movie.setStatus(status);
                    if (status == MovieStatusType.END_OF_SHOWING) {
                        for (Cineplex c : cineplexList) {
                            c.removeMovieShowings(movie);
                        }
                    }
                    System.out.println("Movie Status successfully updated.");
                    break;

                case 2:
                    System.out.println("\nCurrent Sale Count: " + movie.getSaleCount());
                    System.out.print("Input New Sale Count: ");
                    int saleCount = sc.nextInt();
                    sc.nextLine();
                    movie.setSaleCount(saleCount);
                    for (Cineplex c : cineplexList) {
                        for (Cinema cin : c.getListOfCinemas()) {
                            for (Showing s : cin.getShowList()) {
                                if (s.getMovie().equals(movie)) {
                                    Movie movie1 = s.getMovie();
                                    movie1.setSaleCount(saleCount);
                                }
                            }
                        }
                    }
                    System.out.println("Movie Sale Count successfully updated.");
                    break;

                case 3:
                    while (true) {
                        System.out.println("Current Movie Type: " + movie.getType());
                        System.out.println("\n[1] Regular");
                        System.out.println("[2] 3D");
                        System.out.println("[3] Blockbuster");
                        System.out.print("\nInput New Movie Type: ");
                        updateChoice = sc.nextInt();
                        sc.nextLine();
                        if (updateChoice <= 3 && updateChoice >= 1) {
                            break;
                        } else {
                            System.out.println("Error: Invalid Movie Type. Please try again.");
                        }
                    }
                    MovieType type = MovieType.values()[updateChoice - 1];
                    movie.setType(type);
                    for (Cineplex c : cineplexList) {
                        for (Cinema cin : c.getListOfCinemas()) {
                            for (Showing s : cin.getShowList()) {
                                if (s.getMovie().equals(movie)) {
                                    Movie movie1 = s.getMovie();
                                    movie1.setType(type);
                                }
                            }
                        }
                    }
                    System.out.println("Movie Type successfully updated.");
                    break;

                case 4:
                    LocalDateTime endOfShowingDate = LocalDateTime.now();
                    while (true) {
                        try {
                            LocalDate currentEOSD = movie.getEndOfShowingDate().toLocalDate();
                            System.out.println("\nCurrent End Of Showing Date: "
                                + currentEOSD.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                            System.out.print("Input New End Of Showing Date (dd-MM-yyyy): ");
                            String dateString = sc.nextLine();
                            DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                            endOfShowingDate = LocalDateTime.parse(dateString + " 00:00", dtFormatter);
                            break;
                        } catch (Exception e) {
                            System.out.println("Error: Invalid Date Format. Please try again.");
                        }
                    }
                    movie.setEndOfShowingDate(endOfShowingDate);
                    for (Cineplex c : cineplexList) {
                        for (Cinema cin : c.getListOfCinemas()) {
                            for (Showing s : cin.getShowList()) {
                                if (s.getMovie().getTitle().equals(movie.getTitle())) {
                                    Movie movie1 = s.getMovie();
                                    movie1.setEndOfShowingDate(endOfShowingDate);
                                }
                            }
                        }
                    }
                    System.out.println("Movie End Of Showing Date successfully updated.");
                    break;

                case 5:
                    run = false;
                    break;

                default:
                    System.out.println("Error: Invalid Choice, Please try again.");
                    break;
            }
        } while (run);
        cineplexDB.write(cineplexList);
    }

    /**
     * Helper function that is used when creating a new MovieListing.
     * 
     * @return ArrayList of cast based on user input.
     */
    private ArrayList<String> getCast() {
        ArrayList<String> cast = new ArrayList<String>();

        int numberOfCast = 0;

        boolean run = true;
        do {
            System.out.print("\nInput number of cast members: ");
            numberOfCast = sc.nextInt();
            sc.nextLine();
            if (numberOfCast < 2) {
                System.out.println("There must be at least 2 cast members!");
            } else {
                run = false;
            }
        } while (run);

        for (int i = 0; i < numberOfCast; i++) {
            System.out.print("Input Movie Cast: ");
            String castMember = sc.nextLine();
            cast.add(castMember);
        }

        return cast;
    }

    /**
     * Helper function used for the user to select the desired MovieListing.
     * 
     * @return Movie Listing chosen.
     */
    private Movie selectMovie() {
        ArrayList<Movie> availableMovies = new ArrayList<Movie>();
        for (Movie m : movieList) {
            if (m.getStatus() != MovieStatusType.END_OF_SHOWING) {
                availableMovies.add(m);
            }
        }

        if (availableMovies.isEmpty()) {
            return null;
        }

        int choice = 0;
        do {
            for (int i = 0; i < availableMovies.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + availableMovies.get(i).getTitle());
            }
            System.out.print("\nPlease key in the number of the movie that you would like to select: ");
            choice = sc.nextInt();
            sc.nextLine();
            if (choice < 1 || choice > availableMovies.size()) {
                System.out.println("Error: Invalid input. Please try again.");
            } else {
                break;
            }
        } while (true);

        return availableMovies.get(choice - 1);

    }
}
