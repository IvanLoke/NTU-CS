package databases;

import java.util.ArrayList;

import objects.MovieGoer;

/**
 * Represents the concrete DB class for MovieGoer.
 * 
 * @author Aaron Chua
 * @version 1.0
 * @since 2022-11-11
 */
public class MovieGoerDB extends SerializeDB<ArrayList<MovieGoer>> {
    
    /**
     * Creates a new MovieGoerDB with the given filename.
     */
    public MovieGoerDB() {
        this.filename = "databases/movieGoer.dat";
    }
}
