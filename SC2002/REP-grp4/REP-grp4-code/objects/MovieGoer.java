package objects;

import java.io.Serializable;
import java.util.ArrayList;

import enums.AgeType;

/**
 * Represents a MovieGoer user account.
 * 
 * @author Ivan Loke
 * @version 1.0
 * @since 2022-11-11
 */

public class MovieGoer implements Serializable {
    
    /**
     * The username of this MovieGoer account.
     */
    private String name;

    /**
     * The mobile number of this MovieGoer account.
     */
    private String mobile;

    /**
     * The AgeType of this MovieGoer account (CHILD,ADILT,SENIOR).
     */
    private AgeType agetype;

    /**
     * The email address of this MovieGoer account.
     */
    private String email;

    /**
     * The list of preiously bought MovieTickets of this MovieGoer account.
     */
    private ArrayList<MovieTicket> movieTicketList;

    /**
     * Creates a new MovieGoer object, with information keyed in by a MovieGoer
     * user.
     * 
     * @param name    Name of new MovieGoer.
     * @param mobile  Mobile number of new MovieGoer.
     * @param agetype AgeType of new MovieGoer(CHILD,ADULT,SENIOR).
     * @param email   Email address of new MovieGoer.
     */
    public MovieGoer(String name, String mobile, AgeType agetype, String email) {
        this.name = name;
        this.mobile = mobile;
        this.agetype = agetype;
        this.email = email;
        this.movieTicketList = new ArrayList<MovieTicket>();
    }

    /**
     * Retrieves the name of this MovieGoer.
     * 
     * @return this MovieGoer's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the email address of this MovieGoer.
     * 
     * @return this MovieGoer's email address.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Retrieves the mobile number of this MovieGoer.
     * 
     * @return this MovieGoer's number mobile.
     */
    public String getMobile() {
        return this.mobile;
    }

    /**
     * Retrieves the AgeType(CHILD,ADULT,SENIOR) of this MovieGoer.
     * 
     * @return this MovieGoer's AgeType.
     */
    public AgeType getAgeType() {
        return this.agetype;
    }

    /**
     * Retrieves the list of previously bought MovieTickets of this MovieGoer.
     * 
     * @return this MovieGoer's list of previously bought MovieTickets.
     */
    public ArrayList<MovieTicket> getMovieTicketList() {
        return this.movieTicketList;
    }

    /**
     * Adds a MovieTicket object into this MovieGoer's list of preivously bought
     * MovieTickets.
     * 
     * @param movieTicket represents the MovieTicket object to be added into this
     *                    MovieGoer's list of previously bought MovieTickets.
     */
    public void addMovieTicket(MovieTicket movieTicket) {
        movieTicketList.add(movieTicket);
    }
}
