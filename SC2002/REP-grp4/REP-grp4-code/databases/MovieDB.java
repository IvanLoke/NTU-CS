package databases;

import java.util.ArrayList;

import objects.Movie;

/**
 * Represents the concrete DB class for Movie.
 * 
 * @author Aaron Chua
 * @version 1.0
 * @since 2022-11-11
 */
public class MovieDB extends SerializeDB<ArrayList<Movie>> {
    /**
     * Creates a new MovieDB with the given filename.
     */
    public MovieDB() {
        this.filename = "databases/movie.dat";
    }
}
