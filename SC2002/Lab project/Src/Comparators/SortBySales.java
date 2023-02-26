package Comparators;

import java.util.Comparator;

import Objects.Movie;

/**
 * Represents the comparator class used for comparing two movies based on
 * Movie sales.
 * 
 * @author Ivan Loke
 * @version 1.0
 * @since 2022-11-11
 */
public class SortBySales implements Comparator<Movie> {
    /**
     * Creates a new Comparator to compare two Movies based on Movie sales
     * 
     * @param m0 First Movie object to be compared
     * @param m1 Second Movie object to be compared
     */
    public int compare(Movie m0, Movie m1) {
        if (m0.getSaleCount() < m1.getSaleCount()) {
            return 1;
        } else if (m0.getSaleCount() == m1.getSaleCount()) {
            return 0;
        } else {
            return -1;
        }
    }
}
