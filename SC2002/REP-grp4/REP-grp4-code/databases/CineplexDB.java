package databases;

import java.lang.System;
import java.util.ArrayList;

import objects.Cineplex;

/**
 * Represents the concrete DB class for Cineplex.
 * 
 * @author Aaron Chua
 * @version 1.0
 * @since 2022-11-11
 */
public class CineplexDB extends SerializeDB<ArrayList<Cineplex>> {
    
    /**
     * Creates a new CineplexDB with the given filename.
     */
    public CineplexDB() {
        this.filename = "databases/cineplex.dat";
    }

    /**
     * Generates a unique ShowingId based on current time
     * 
     * @return showingId
     */
    public static int generateShowingId() {
        return (int) (System.currentTimeMillis() / 1000);
    }
}
