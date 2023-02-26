package Objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Enums.DayType;
import Enums.SeatType;

import Databases.CineplexDB;

/**
 * Represents an abstract Showing which will be inherited by specific showing
 * classes.
 * 
 * @author Ang Kai Jun
 * @version 1.0
 * @since 2022-11-11
 */
abstract public class Showing implements Serializable {

    /**
     * The id of the Showing.
     */
    private int id;

    /**
     * The Movie of this Showing.
     */
    private Movie movie;

    /**
     * The time of this Showing (YYYY-MM-DDT00:00:00).
     */
    private LocalDateTime showTime;

    /**
     * The type of day of the Showing (Weekend/ Weekday/ Public Holiday).
     */
    private DayType dayType;

    /**
     * The seat layout of the Showing.
     */
    protected Seat[][] seatLayout;

    /**
     * Creates a new Showing with information keyed in by the Admin.
     * 
     * @param movie    Movie object specific to this Showing.
     * @param showTime Time of the showing (YYYY-MM-DDT00:00:00).
     * @param dayType  Type of day of the showing (Weekend/ Weekday/ Public
     *                 Holiday).
     */
    public Showing(Movie movie, LocalDateTime showTime, DayType dayType) {
        this.movie = movie;
        this.showTime = showTime;
        this.dayType = dayType;
        this.id = CineplexDB.generateShowingId();
    }

    /**
     * Retrieves id this Showing.
     * 
     * @return this Showing's ID.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Retrieves showTime of this Showing.
     * 
     * @return this Showing's showTime.
     */
    public LocalDateTime getShowTime() {
        return this.showTime;
    }

    /**
     * Modifies the ShowTime of this showing.
     * 
     * @param showTime new ShowTime of this Showing.
     */
    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    /**
     * Retrieves the Movie of this showing.
     * 
     * @return this Showing's Movie.
     */
    public Movie getMovie() {
        return this.movie;
    }

    /**
     * Modifies the Movie of this Showing.
     * 
     * @param movie new Movie of this Showing.
     * @return this Showing's Movie.
     */
    public Movie setMovie(Movie movie) {
        return this.movie = movie;
    }

    /**
     * Retrieves the DayType of this Showing.
     * 
     * @return this Showings's DayType.
     */
    public DayType getDayType() {
        return this.dayType;
    }

    /**
     * Modifies the DayType of this Showing.
     * 
     * @param dayType new DayType of this Showing.
     * @return this Showing's DayType.
     */
    public DayType setDayType(DayType dayType) {
        return this.dayType = dayType;
    }

    /**
     * Retrieves the ShowTime of this Showing in dd-MM-yyyy HH:mm.
     * 
     * @return formatted ShowTime of this Showing (dd-MM-yyyy HH:mm).
     */
    public String getFormattedTime() {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDate = showTime.format(myFormatObj);
        return formattedDate;
    }

    /**
     * Prints out the seat layout of this Showing.
     * Includes a legend to indicate the seatType.
     * Unavailable Seats are represented with a "X".
     */
    public void printSeating() {
        System.out.println("***********************************************");
        System.out.print("=".repeat(seatLayout[0].length));
        System.out.print(" Movie Screen ");
        System.out.print("=".repeat(seatLayout[0].length) + "\n");
        System.out.println("{STAIRS}");
        System.out.println();

        // print labels on top
        System.out.print("   ");
        for (int i = 1; i <= seatLayout[0].length; i++) {
        System.out.print(i + "  ");
        if (i == this.seatLayout[0].length / 2) {
            System.out.print("\t ");
        }
        }
        System.out.println();

        for (int i = 0; i < this.seatLayout.length; i++) {
        for (int j = 0; j < this.seatLayout[i].length; j++) {
            if (j == 0) {
            System.out.print((char) (i + 65) + " ");
            }
            this.seatLayout[i][j].printSeat();
            if (j == this.seatLayout[i].length / 2 - 1) {
            System.out.print("\t");
            }
            if (j == this.seatLayout[i].length - 1) {
            System.out.println();
            }
        }
        }
        System.out.println("\nLegend: ");
        System.out.println("REGULAR: [ ][ ] | [X][X]");
        System.out.println("COUPLE:  [    ] | [X  X]");
        System.out.println("ELITE:   { }{ } | {X}{X}");
        System.out.println("ULTIMA:  {    } | {X  X}");
        System.out.println("\n***********************************************");
    }

    /**
     * Returns the availability status of the seat with a specified seatId
     * (boolean).
     * Returns true if seat is available.
     * Returns false if seat is not available.
     * 
     * @param seatId Id of the Seat.
     * @return availability status of the seat (boolean).
     */
    public boolean isAvailable(String seatId) {
        int row = (int) seatId.charAt(0) - 65;
        int column = Character.valueOf(seatId.charAt(1)) - 49;
        Seat seat = this.seatLayout[row][column];
        return seat.isAvailable();
    }

    /**
     * Retrieves seatType of the seat with a specifed seatId (REGULAR, COUPLE,
     * ELITE, ULTIMA).
     * 
     * @param seatId Id of the Seat.
     * @return seatType of the seat (REGULAR, COUPLE, ELITE,
     *         ULTIMA).
     */
    public SeatType getSeatType(String seatId) {
        int row = (int) seatId.charAt(0) - 65;
        int column = Character.valueOf(seatId.charAt(1)) - 49;
        Seat seat = this.seatLayout[row][column];
        return seat.getSeatType();
    }

    /**
     * Assigns an available seat to a MovieGoer.
     * 
     * @param movieGoer MovieGoer who is assigned the seat.
     * @param seatId    seatId of the booked seat.
     */
    public void assignSeat(MovieGoer movieGoer, String seatId) {
        int row = (int) seatId.charAt(0) - 65;
        int column = Character.valueOf(seatId.charAt(1)) - 49;
        Seat seat = this.seatLayout[row][column];
        seat.assignSeat(movieGoer);
    }

    /**
     * Unassigns a seat with a specified seatId.
     * 
     * @param seatId seatId of the seat to be unassigned.
     */
    public void unassignSeat(String seatId) {
        int row = (int) seatId.charAt(0) - 65;
        int column = Character.valueOf(seatId.charAt(1)) - 49;
        Seat seat = this.seatLayout[row][column];
        seat.unassignSeat();
    }

    /**
     * Helper function used to return the seatLayout.
     * 
     * @return the seatLayout for this Showing.
     */
    public Seat[][] getSeatLayout() {
        return this.seatLayout;
    }
}
