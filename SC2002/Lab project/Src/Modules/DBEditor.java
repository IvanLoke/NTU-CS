package Modules;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Databases.AdminDB;
import Databases.CineplexDB;
import Databases.MovieDB;

import Databases.MovieGoerDB;
import Objects.Admin;
import Objects.Cineplex;
import Objects.Cinema;
import Objects.Movie;

import Objects.MovieGoer;
import Objects.Showing;
import Enums.MovieStatusType;
import Enums.MovieType;
import Enums.CinemaType;
import Enums.AgeType;

public class DBEditor {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("***********************************************");
    System.out.println("********* WELCOME TO DATABASE EDITOR **********");
    System.out.println("***********************************************");
    int choice;
    boolean running = true;
    while (running) {

      System.out.println("DATABASE EDITOR -- Main Menu:");
      System.out.println("[1] Edit AdminDB ");
      System.out.println("[2] Edit CineplexDB ");
      System.out.println("[3] Edit MovieDB ");
      System.out.println("[4] Edit MovieGoerDB ");
      System.out.println("[5] Display all Showings ");
      System.out.println("[6] Exit");
      System.out.print("Please Select: ");
      choice = sc.nextInt();
      sc.nextLine(); // consume \n
      System.out.println("***********************************************");
      switch (choice) {
        case 1:
          // create instance object of your DB and your local data variable
          AdminDB AdminDBInstance = new AdminDB();
          ArrayList<Admin> adminData = (ArrayList<Admin>) AdminDBInstance.read();
          if (adminData == null) {
            adminData = new ArrayList<Admin>();
          }

          // write
          System.out.println("Username: ");
          String username = sc.nextLine();
          System.out.println("Password: ");
          String password = sc.nextLine();
          Admin newAdminToAdd = new Admin(username, password);
          adminData.add(newAdminToAdd);
          AdminDBInstance.write(adminData);

          // read
          adminData = (ArrayList<Admin>) AdminDBInstance.read();
          System.out.println("Current Data:");
          for (int i = 0; i < adminData.size(); i++) {
            Admin adminPerson = (Admin) adminData.get(i);
            System.out.println(adminPerson);
          }
          break;
        case 2:
          CineplexDB CineplexDBInstance = new CineplexDB();
          ArrayList<Cineplex> cineplexData = (ArrayList<Cineplex>) CineplexDBInstance.read();
          if (cineplexData == null) {
            cineplexData = new ArrayList<Cineplex>();
          }

          // write
          System.out.println("cineplexName: ");
          String cineplexName = sc.nextLine();
          ArrayList<Cinema> cinemasList = new ArrayList<Cinema>();
          System.out.println("enter number of cinemas to add: ");
          int numberOfCinemas = sc.nextInt();
          while (numberOfCinemas > 0) {
            System.out.println("cinema number:");
            int cinemaNum = sc.nextInt();
            sc.nextLine();
            System.out.println("cinema code: ");
            String cinemaCode = sc.nextLine();
            System.out.println("cinema type: (GOLD_CLASS, DELUXE, REGULAR)");
            CinemaType cinemaType = CinemaType.valueOf(sc.nextLine());
            cinemasList.add(
                new Cinema(cinemaNum, cinemaCode, cinemaType));
            numberOfCinemas--;
          }

          Cineplex newCineplexToAdd = new Cineplex(cineplexName, cinemasList);
          cineplexData.add(newCineplexToAdd);
          CineplexDBInstance.write(cineplexData);

          // read
          cineplexData = (ArrayList<Cineplex>) CineplexDBInstance.read();
          System.out.println("Current Data:");
          for (int i = 0; i < cineplexData.size(); i++) {
            Cineplex cineplex = (Cineplex) cineplexData.get(i);
            System.out.println(cineplex);
          }

          break;
        case 3:
          // create instance object of your DB and your local data variable
          MovieDB MovieDBInstance = new MovieDB();
          ArrayList<Movie> movieData = (ArrayList<Movie>) MovieDBInstance.read();
          if (movieData == null) {
            movieData = new ArrayList<Movie>();
          }

          // write
          System.out.println("title: ");
          String title = sc.nextLine();
          System.out.println("status: (COMING_SOON, PREVIEW, NOW_SHOWING)");
          MovieStatusType status = MovieStatusType.valueOf(sc.nextLine());
          System.out.println("synopsis: ");
          String synopsis = sc.nextLine();
          System.out.println("director: ");
          String director = sc.nextLine();
          ArrayList<String> cast = new ArrayList<String>();
          System.out.println("cast: (enter a number to end)");
          while (!sc.hasNextInt()) {
            cast.add(sc.nextLine());
          }
          sc.nextLine();
          System.out.println("type: (REGULAR, THREE_D, BLOCKBUSTER)");
          MovieType type = MovieType.valueOf(sc.nextLine());
          System.out.println("endOfShowingDate (yyyyMMddHHmm): ");
          String dateString = sc.nextLine();
          DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
          LocalDateTime endOfShowingDate = LocalDateTime.parse(dateString, dtFormatter);
          Movie newMovieToAdd = new Movie(title, status, synopsis, director, cast, type, endOfShowingDate);
          movieData.add(newMovieToAdd);
          MovieDBInstance.write(movieData);

          // read
          movieData = (ArrayList<Movie>) MovieDBInstance.read();
          System.out.println("Current Data:");
          for (int i = 0; i < movieData.size(); i++) {
            Movie movie = (Movie) movieData.get(i);
            System.out.println(movie);
          }
          break;
        case 4:
          // create instance object of your DB and your local data variable
          MovieGoerDB movieGoerDBInstance = new MovieGoerDB();
          ArrayList<MovieGoer> movieGoerData = (ArrayList<MovieGoer>) movieGoerDBInstance.read();
          if (movieGoerData == null) {
            movieGoerData = new ArrayList<MovieGoer>();
          }

          // write
          System.out.println("Name: ");
          String name = sc.nextLine();
          System.out.println("Mobile: ");
          String mobile = sc.nextLine();
          System.out.println("AgeType: (CHILD, ADULT, SENIOR)");
          AgeType ageType = AgeType.valueOf(sc.nextLine());
          System.out.println("Email: ");
          String email = sc.nextLine();

          MovieGoer newMovieGoerToAdd = new MovieGoer(name, mobile, ageType, email);
          movieGoerData.add(newMovieGoerToAdd);
          movieGoerDBInstance.write(movieGoerData);

          // read
          movieGoerData = (ArrayList<MovieGoer>) movieGoerDBInstance.read();
          System.out.println("Current Data:");
          for (MovieGoer m : movieGoerData) {
            System.out.println(m);
          }
          break;

        case 5:
          CineplexDB cineplexDB = new CineplexDB();
          cineplexData = cineplexDB.read();
          for (int i = 0; i < cineplexData.size(); i++) {
            System.out.println("[" + (i + 1) + "]" + " " + cineplexData.get(i).getCineplexName());
          }
          System.out.println("Select the Cineplex of your choice");
          choice = sc.nextInt();
          System.out.println("***********************************************");
          Cineplex cineplex = cineplexData.get(choice - 1);
          ArrayList<Cinema> cinemaList = cineplex.getListOfCinemas();
          for (Cinema cinema : cinemaList) {
            System.out.println("***********************************************");
            System.out.println("Cinema " + cinema.getCinemaNum() + " (" + cinema.getCinemaType() + ")");
            ArrayList<Showing> showList = cinema.getShowList();
            for (Showing showing : showList) {
              Movie m = showing.getMovie();
              System.out.println(m.getTitle());
              System.out.println("\t EndOfShowingDate: " + m.getEndOfShowingDate());
              System.out.println("\t Movie Status: " + m.getStatus());
              System.out.println("\t Show Time: " + showing.getShowTime());
            }
            System.out.println("***********************************************");
            System.out.println();
          }
          break;

        case 6:
          System.out.println("Bye Bye!");
          running = false;
          break;

        default:
          System.out.println("Invalid Choice, Please try again!\n");
          break;
      }
      System.out.println("***********************************************");
    }
    sc.close();
  }
}
