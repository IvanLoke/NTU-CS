package Objects;

import java.io.Serializable;
import java.util.ArrayList;

/** 
 * Represents the class of the Cineplex object.
 * @author S Jivaganesh
 * @version 1.0
 * @since 2022-11-11
 */
public class Cineplex implements Serializable {
  
    /**
     * Number of this Cinema (eg. 1, 2, 3).
     */
    private String cineplexName;

    /**
     * List of Cinemas within this Cineplex.
     */
    private ArrayList<Cinema> cinemaList;

    /**
     * Constructor of the Cineplex object. Once called, a new Cineplex Object will be created.
     * @param cineplexName represents the name of the Cineplex object.
     * @param cinemaList represents the list of Cinema Objects in the Cineplex Object.
     */
    public Cineplex(String cineplexName, ArrayList<Cinema> cinemaList) {
        this.cineplexName = cineplexName;
        this.cinemaList = cinemaList;
    }
    
    /** 
     * Returns the Cineplex Name when called.
     * @return this Cineplex's name.
     */
    public String getCineplexName() {
        return cineplexName;
    }

    /** 
     * Returns a List of Cinemas in the Cineplex when called.
     * @return the list of Cinemas within this Cineplex.
     */
    public ArrayList<Cinema> getListOfCinemas() {
        return cinemaList;
    }

    /**
     * Prints the list of cinemas.
     */
    public void displayCinemas(){
        for (int i=0; i<cinemaList.size(); i++){
            System.out.println("Cinema " + cinemaList.get(i).getCinemaNum() + "\n");
        }
    }

    
    /** 
     * Removes a Showing from a Cinema in the Cineplex.
     * @param movie represents the Movie object to identify which Showing to remove from the Cinema.
     */
    public void removeMovieShowings(Movie movie) {
        for (Cinema c: cinemaList) {
            c.removeMovieShowings(movie);
        }
    }
}
