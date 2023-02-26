package Objects;

import java.io.Serializable;

import Enums.SeatType;

/** 
 * Represents a Seat within a Cinema. 
 * @author Chay Hui Xiang
 * @version 1.0
 * @since 2022-11-11
 */
public class Seat implements Serializable {

    /**
     * Represents the ID of the Seat. (Eg. A1, A2).
     */
    private String id;

    /**
     * MovieGoer for whom the Seat is assigned to. 
     * Set to null if the Seat is not assigned to anybody.
     */
    private MovieGoer movieGoer;

    /**
     * Type of the Seat. (ie. REGULAR, COUPLE, ELITE, ULTIMA).
     */
    private SeatType seatType;

    /**
     * Creates a new Seat to be added to the layout of the Cinema, with the given id and seatType.
     * @param id        ID of the Seat. (Eg. A1, A2).
     * @param seatType  Type of the Seat. (ie. REGULAR, COUPLE, ELITE, ULTIMA).
     */
    public Seat(String id, SeatType seatType) {
        this.id = id;
        this.movieGoer = null;
        this.seatType = seatType;
    }

    /**
     * Retrieves the MovieGoer for whom this seat is assigned to.
     * Returns null if the seat is not assigned to anyone.
     * @return this Seat's assigned MovieGoer.
     */
    public MovieGoer getMovieGoer() {
        return movieGoer;
    }

    /**
     * Retrieves the ID of this Seat.
     * @return this Seat's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Assigns this Seat to a particular MovieGoer
     * @param movieGoer MovieGoer to assign this Seat to.
     */
    public void assignSeat(MovieGoer movieGoer) {
        this.movieGoer = movieGoer;
    }

    /**
     * Unassigns this Seat from its original MovieGoer
     */
    public void unassignSeat() {
        this.movieGoer = null;
    }

    /**
     * Retrieves the seatType of this Seat.
     * @return this Seat's seatType
     */
    public SeatType getSeatType() {
        return seatType;
    }

    /**
     * Prints this Seat, depending on the avaiability of the seat, and the seatType.
     * Seat Legend:
     * REGULAR: [ ][ ] | [X][X]
     * COUPLE:  [    ] | [X  X]
     * ELITE:   { }{ } | {X}{X}
     * ULTIMA:  {    } | {X  X}
     */
    public void printSeat() {
        if (id == null) {
            return;
        }
        char columnChar = id.charAt(1);
        int column = Character.getNumericValue(columnChar);
        boolean left = column % 2 == 0 ? true : false;

        switch (seatType) {
            case REGULAR:
                if (isAvailable()) {
                    System.out.print("[ ]");
                } else {
                    System.out.print("[X]");
                }
                break;

            case COUPLE:
                if (left && isAvailable()) {
                    System.out.print("[  ");
                } else if (left && !isAvailable()) {
                    System.out.print("[X ");
                } else if (!left && isAvailable()) {
                    System.out.print("  ]");
                } else {
                    System.out.print(" X]");
                }
                break;

            case ELITE:
                if (isAvailable()) {
                    System.out.print("{ }");
                } else {
                    System.out.print("{X}");
                }
                break;

            case ULTIMA:
                if (left && isAvailable()) {
                    System.out.print("{  ");
                } else if (left && !isAvailable()) {
                    System.out.print("{X ");
                } else if (!left && isAvailable()) {
                    System.out.print("  }");
                } else {
                    System.out.print(" X}");
                }
                break;

            default:
                break;
        }
    }

    /**
     * Checks whether this Seat is available.
     * @return the availability status of the seat.
     */
    public boolean isAvailable() {
        if (movieGoer == null) {
            return true;
        } else {
            return false;
        }
    }
}
