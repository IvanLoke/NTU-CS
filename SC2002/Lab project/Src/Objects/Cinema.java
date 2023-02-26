package Objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import Enums.CinemaType;
import Enums.DayType;
import Enums.MovieStatusType;

/** 
 * Represents the class of the Cinema object.
 * @author S Jivaganesh
 * @version 1.0
 * @since 2022-11-11
 */

public class Cinema implements Serializable {

    /**
     * Number of this Cinema (eg. 1, 2, 3).
     */
    private int cinemaNum;

    /**
     * Code of this Cinema (eg. OR1, OR2, OR3).
     */
    private String cinemaCode;

    /**
     * Type of this Cinema (ie. GOLD_CLASS, DELUXE, REGULAR).
     */
    private CinemaType cinemaType;

    /**
     * List of all Showings registered under this Cinema.
     */
    private ArrayList<Showing> showList;

    /**
     * Constructor of the Cinema object. Once called, a new Cinema Object will be created.
     * @param cinemaNum represents the number of the Cinema object within the cineplex.
     * @param cinemaCode represents the unique code of the Cinema Object.
     * @param cinemaType represents the type of cinema of the Cinema Object.
     */
    public Cinema(int cinemaNum, String cinemaCode, CinemaType cinemaType) {
        this.cinemaNum = cinemaNum;
        this.cinemaCode = cinemaCode;
        this.cinemaType = cinemaType;
        this.showList = new ArrayList<Showing>();
    }

    /** 
     * Returns Showing List when called.
     * @return this Cinema's showList.
     */
    public ArrayList<Showing> getShowList() {
        return this.showList;
    }

    /** 
     * Returns the Cinema Number when called.
     * @return this Cinema's cinemaNum.
     */
    public int getCinemaNum() {
        return this.cinemaNum;
    }

    /** 
     * Returns Cinema Code when called.
     * @return this Cinema's cinemaCode.
     */
    public String getCinemaCode() {
        return this.cinemaCode;
    }

    /** 
     * Returns Cinema Type when called.
     * @return this Cinema's cinemaType.
     */
    public CinemaType getCinemaType() {
        return this.cinemaType;
    }

    /** 
     * Prints Showing List when method is called.
     */
    public void displayShowList() {
        int showsAvailable = 0;
        for (int i = 0; i < showList.size(); i++) {
            Showing show = showList.get(i);
            Movie movie = show.getMovie();
            System.out.println("[" + (i + 1) + "]: " + movie.getTitle() + " " + show.getFormattedTime());
            showsAvailable = 1;
        }

        if (showsAvailable == 0) {
            System.out.println("No Shows Found.");
        }
    }

    
    /** 
     * Prints Showing list of available showings when method is called.
     */
    public void displayAvailableShows() {
        int index = 1;
        int showsAvailable = 0;
        for (Showing showing : showList) {
            Movie movie = showing.getMovie();
            if (movie.getStatus() == MovieStatusType.NOW_SHOWING
                && movie.getEndOfShowingDate().compareTo(LocalDateTime.now()) > 0) {
                System.out.println("[" + index + "]: " + movie.getTitle() + " " + showing.getFormattedTime());
                index++;
                showsAvailable = 1;
            }
        }
        if (showsAvailable == 0) {
            System.out.println("No Shows Found.");
        }
    }

    /** 
     * Returns Showing object corresponding to the Search Index requested.
     * @param searchIndex represents the Search Index requested.
     * @return the Showing corresponding to the requested searchIndex.
     */
    public Showing searchShow(int searchIndex) {
        int index = 1;
        for (int i = 0; i < showList.size(); i++) {
            Showing s = showList.get(i);
            Movie m = s.getMovie();
            if (m.getStatus() == MovieStatusType.NOW_SHOWING) {
                if (index == searchIndex) {
                return s;
                }
                index++;
            }
        }
        return null;
    }

    /** 
     * Adds a new Showing object to the Showing List.
     * @param movie represents the Movie object for which this Showing corresponds to.
     * @param showTime represents the show time for which this Showing corresponds to.
     * @param dayType represents the type of day for which this Showing corresponds to.
     */
    public void addShow(Movie movie, LocalDateTime showTime, DayType dayType) {
        Showing show;
        switch (cinemaType) {
        case DELUXE:
            show = new DeluxeShowing(movie, showTime, dayType);
            showList.add(show);
            break;

        case REGULAR:
            show = new RegularShowing(movie, showTime, dayType);
            showList.add(show);
            break;

        case GOLD_CLASS:
            show = new GoldShowing(movie, showTime, dayType);
            showList.add(show);
            ;
            break;

        default:
            break;
        }
    }

    
    /** 
     * Removes a Showing from the Showing List.
     * @param show represents the Showing object to be removed.
     */
    public void removeShow(Showing show) {
        showList.remove(show);
    }

    /** 
     * Removes a Showing which has the mentioned Movie object.
     * @param movie represents the Movie object to identify which Showing to remove.
     */
    public void removeMovieShowings(Movie movie) {
        Iterator<Showing> i = showList.iterator();

        while (i.hasNext()) {
            Showing s = i.next();
            Movie m = s.getMovie();
            if (m.equals(movie)) {
                i.remove();
            }
        }
    }
}
