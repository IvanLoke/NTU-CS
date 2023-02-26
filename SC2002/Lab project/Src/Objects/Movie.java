package Objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import Enums.MovieStatusType;
import Enums.MovieType;

/**
 * Represents a Movie that has been listed by the Admin.
 * 
 * @author Ang Kai Jun
 * @version 1.0
 * @since 2022-11-11
 */
public class Movie implements Serializable, Comparable<Movie> {

    /**
     * The title of this Movie.
     */
    private String title;

    /**
     * The status of this Movie (ie. COMING_SOON, PREVIEW, NOW_SHOWING,
     * END_OF_SHOWING).
     */
    private MovieStatusType status;

    /**
     * Synopsis on what this Movie is about.
     */
    private String synopsis;

    /**
     * The name of the Movie director.
     */
    private String director;

    /**
     * A list of the cast names.
     */
    private ArrayList<String> cast;

    /**
     * A list of Reviews written on the Movie.
     */
    private ArrayList<Review> reviewList;

    /**
     * Number of MovieTickets sold.
     */
    private int saleCount;

    /**
     * The type of this Movie (ie. REGULAR, 3D, BLOCKBUSTER).
     */
    private MovieType type;

    /**
     * The date and time when the Movie stops airing.
     */
    private LocalDateTime endOfShowingDate; // YYYY-MM-DDT00:00:00

    /**
     * Creates a new Movie listing, with information keyed in by the Admin.
     * 
     * @param title            Title of the new Movie.
     * @param status           Status of the new Movie (ie. COMING_SOON, PREVIEW,
     *                         NOW_SHOWING, END_OF_SHOWING).
     * @param synopsis         Synopsis of what this new Movie is about.
     * @param director         Name of the director of the new Movie.
     * @param cast             List of the names of the cast members.
     * @param type             Type of the new Movie (ie. REGULAR, 3D, BLOCKBUSTER).
     * @param endOfShowingDate Date and Time when the new Movie stops airing.
     */
    public Movie(String title, MovieStatusType status, String synopsis, String director, ArrayList<String> cast,
        MovieType type, LocalDateTime endOfShowingDate) {
        this.title = title;
        this.status = status;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast;
        this.reviewList = new ArrayList<Review>(); // default empty reviewList
        this.saleCount = 0; // default empty saleCount
        this.type = type;
        this.endOfShowingDate = endOfShowingDate;
    }

    /**
     * Retrieves the title of this Movie.
     * 
     * @return the title of this Movie.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Modifies the title of this Movie.
     * 
     * @param title new title of this Movie.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the MovieStatusType of this Movie.
     * 
     * @return this Movie's MovieStatusType.
     */
    public MovieStatusType getStatus() {
        return this.status;
    }

    /**
     * Modifies the MovieStatusType of this Movie.
     * 
     * @param status new MovieStatusType of this Movie.
     */
    public void setStatus(MovieStatusType status) {
        this.status = status;
    }

    /**
     * Retrieves the synopsis of this Movie.
     * 
     * @return this Movie's synopsis.
     */
    public String getSynopsis() {
        return this.synopsis;
    }

    /**
     * Modifies the synopsis of this Movie.
     * 
     * @param synopsis new synopsis of this Movie.
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * Retrieves the list of Reviews of this Movie.
     * 
     * @return this Movie's ArrayList of Reviews.
     */
    public ArrayList<Review> getReviewList() {
        return this.reviewList;
    }

    /**
     * Adds a new Review to the current list of Reviews.
     * 
     * @param name   Name of MovieGoer who provided the new Review.
     * @param rating Rating of the Movie provided by the MovieGoer.
     * @param review Text Review given by the MovieGoer.
     */
    public void addReview(String name, int rating, String review) {
        Review newReview = new Review(name, rating, review);
        this.reviewList.add(newReview);
    }

    /**
     * Retrieves this Movie's Director Name.
     * 
     * @return the name of this Movie's Director.
     */
    public String getDirector() {
        return this.director;
    }

    /**
     * Modifies the director of this Movie.
     * 
     * @param director new director of this Movie.
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Retrieves the list of cast names of this Movie.
     * 
     * @return the list of cast names of this Movie.
     */
    public ArrayList<String> getCast() {
        return this.cast;
    }

    /**
     * Modifies the cast of this Movie.
     * 
     * @param cast new cast of this Movie.
     */
    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    /**
     * Retrieves the MovieType of this Movie.
     * 
     * @return this Movie's MovieType.
     */
    public MovieType getType() {
        return this.type;
    }

    /**
     * Modifies the MovieType of this Movie.
     * 
     * @param type new MovieType of this Movie.
     */
    public void setType(MovieType type) {
        this.type = type;
    }

    /**
     * Retrieves the sale count of this Movie.
     * 
     * @return this Movie's saleCount.
     */
    public int getSaleCount() {
        return this.saleCount;
    }

    /**
     * Modifies the sale count of this Movie.
     * 
     * @param saleCount new saleCount of this Movie.
     */
    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    /**
     * Retrieves the date when this Movie stops airing.
     * 
     * @return this Movie' endOfShowingDate.
     */
    public LocalDateTime getEndOfShowingDate() {
        return this.endOfShowingDate;
    }

    /**
     * Modifies the date when this Movie stops airing.
     * 
     * @param endOfShowingDate the new date when this Movie stops airing.
     */
    public void setEndOfShowingDate(LocalDateTime endOfShowingDate) {
        this.endOfShowingDate = endOfShowingDate;
    }

    /**
     * Increments the current sale count.
     */
    public void incrementSaleCount() {
        this.saleCount++;
    }

    /**
     * Prints the details of this Movie.
     */
    public void printMovieDetails() {
        System.out.println("Title: " + title);
        System.out.println("Status: " + status);
        System.out.println("Synopsis: " + synopsis);
        System.out.println("Director: " + director);
        System.out.println("Cast: " + cast);
        System.out.println("ReviewList: " + reviewList);
        System.out.println("Type: " + type);
        System.out.println("SaleCount: " + saleCount);
        System.out.println("EndOfShowingDate: " + endOfShowingDate);
    }

    /**
     * Computes the overall rating from all Reviews of this Movie.
     * Retrieves the computed overall rating.
     * 
     * @return the computed overall rating of the movie.
     */
    public double getOverallRating() {
        double totalRating = 0;
        int count = 0;
        for (Review r : reviewList) {
            totalRating += r.getRating();
            count++;
        }

        if (count == 0) {
            return 0;
        } else {
            return (totalRating / count);
        }
    }

    /**
     * Helper method to sort 2 different Movies in alphabetical order.
     * Case-Insensitive.
     * 
     * @param m Another Movie to compare this Movie with.
     * @return -1 when this Movie's Title is lexicographically less
     *         than the provided Movie's Title (ignoring case).
     *         0 when both Movie's Titles are equal (ignoring case).
     *         1 when this Movie's Title is lexicographically greater
     *         than the provided Movie's Title (ignoring case).
     */
    @Override
    public int compareTo(Movie m) {
        // compare by movie title
        return this.title.compareToIgnoreCase(m.getTitle());
    }

    /**
     * Helper method to check whether 2 Movies are equal.
     * 
     * @param o Another Object to compare this Movie with.
     * @return 1 when both Movie's Titles the same (ignoring case).
     *         0 when the provided Object is not a Movie.
     *         0 when the provided Object is null.
     *         0 when the title of the Movies do not match.
     */
    @Override
    public boolean equals(Object o) {
        // compare by movie title
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }

        Movie m = (Movie) o;
        if (this.title.equals(m.getTitle())) {
            return true;
        }
        return false;
    }
}
