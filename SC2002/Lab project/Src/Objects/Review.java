package Objects;

import java.io.Serializable;

/**
 * Represents a Review for a Movie.
 * 
 * @author Ivan Loke
 * @version 1.0
 * @since 2022-11-11
 */

public class Review implements Serializable {
    
    /*
    * Represents the name of the MovieGoer writing this Review.
    */
    private String name;

    /**
     * Represents the numerical rating given for this Review.
     */
    private int rating; // From 1(worse) to 5 (best)

    /**
     * Represents the written review given for this Review.
     */
    private String review;

    /**
     * Creates a new Reivew object to be added into reviewList of a Movie.
     * 
     * @param name   Name of MovieGoer writing this review.
     * @param rating Numerical Rating for this review.
     * @param review Written Review for this review.
     */
    public Review(String name, int rating, String review) {
        this.name = name;
        this.rating = rating;
        this.review = review;
    }

    /**
     * Retrevies name of MovieGoer for this Review.
     * 
     * @return Name of MovieGoer who wrote this Reivew.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrivews numerical rating for this Reivew.
     * 
     * @return numerical rating for this Reivew.
     */
    public int getRating() {
        return this.rating;
    }

    /**
     * Retrives written reivew for this Reivew.
     * 
     * @return written review for this Review.
     */
    public String getReview() {
        return this.review;
    }
}
