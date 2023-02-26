package objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** 
 * Represents a MovieTicket / Movie Booking.
 * Each seat booked at a cinema corresponds to a MovieTicket.
 * @author Chay Hui Xiang
 * @version 1.0
 * @since 2022-11-11
 */
public class MovieTicket implements Serializable {

    /**
     * MovieGoer that possesses this MovieTicket.
     */
    private MovieGoer movieGoer;

    /**
     * Price of the MovieTicket.
     */
    private double price;

    /**
     * Showing for which this MovieTicket corresponds to.
     */
    private Showing showing;

    /**
     * Cineplex for which this MovieTicket corresponds to.
     */
    private Cineplex cineplex;

    /**
     * Cinema for which this MovieTicket corresponds to.
     */
    private Cinema cinema;

    /**
     * SeatID booked by the MovieGoer.
     */
    private String seatId;

    /**
     * Transaction ID in the format XXXYYYYMMDDhhmm.
     * (Y: year, M: month, D: day, h: hour, m: minutes, XXX: cinema code in letters)
     */
    private String TID;

    /**
     * Creates a new MovieTicket with the necessary information about the booking.
     * @param movieGoer     MovieGoer for whom this MovieTicket belongs to.
     * @param price         Price of this MovieTicket.
     * @param showing       Showing for which this MovieTicket corresponds to.
     * @param cineplex      Cineplex for which this MovieTicket corresponds to.
     * @param cinema        Cinema within the Cineplex for which this MovieTicket corresponds to.
     * @param seatId        SeatId booked by the MovieGoer.
     */
    public MovieTicket(MovieGoer movieGoer, double price, Showing showing, Cineplex cineplex, Cinema cinema, String seatId) {
        this.movieGoer = movieGoer;
        this.price = price;
        this.showing = showing;
        this.cineplex = cineplex;
        this.cinema = cinema;
        this.seatId = seatId;

        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        this.TID = cinema.getCinemaCode() + LocalDateTime.now().format(dtFormatter);
    }

    /** 
     * Prints information about the booking. 
    */
    public void printTicket() {
        System.out.println("***********************************************");
        System.out.println("Booking Information for TID " + TID);
        System.out.println("*******************************************");
        System.out.println("Personal Particulars: ");
        System.out.println("Name: " + movieGoer.getName());
        System.out.println("Mobile: " + movieGoer.getMobile());
        System.out.println("Email: " + movieGoer.getEmail());
        System.out.println("*******************************************");
        System.out.println("Movie Information: ");
        System.out.println("Movie: " + showing.getMovie().getTitle());
        System.out.println("Cineplex: " + cineplex.getCineplexName());
        System.out.println("Cinema: " + cinema.getCinemaNum());
        System.out.println("Price: " + price + " | Time: " + showing.getFormattedTime() + " | Seat: " + seatId);
        System.out.println("***********************************************");
    }

    /** 
     * Retrieves the Showing Object of a MovieTicket.
     * @return this MovieTicket's Showing.
    */
    public Showing getShowing() {
        return showing;
    }
}
