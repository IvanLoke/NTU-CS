package Databases;

import java.util.ArrayList;

import Objects.Movie;

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
        this.filename = "Databases/movie.dat";
    }
}
