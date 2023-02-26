package objects;

import java.util.HashMap;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/** 
 * Represents the Settings of the current Application.
 * @author Chay Hui Xiang
 * @version 1.0
 * @since 2022-11-11
 */
public class Settings implements Serializable {
  
    /** 
     * HashMap which stores the corresponding prices of different MovieTypes (ie. REGULAR, 3D, BLOCKBUSTER).
     */
    private HashMap<String, Double> movieTypePriceMap;

    /** 
     * HashMap which stores the corresponding prices of different CinemaClasses (ie. REGULAR, DELUXE, GOLD_CLASS).
     */
    private HashMap<String, Double> cinemaClassPriceMap;

    /** 
     * HashMap which stores the corresponding prices of different AgeTypes (ie. CHILD, ADULT, SENIOR).
     */
    private HashMap<String, Double> ageTypePriceMap;

    /** 
     * HashMap which stores the corresponding prices of different DayTypes (ie. WEEKDAY, WEEKEND, PUBLIC_HOLIDAY).
     */
    private HashMap<String, Double> dayTypePriceMap;

    /** 
     * HashMap which stores the corresponding prices of different SeatTypes (ie. REGULAR, COUPLE, ELITE, ULTIMA).
     */
    private HashMap<String, Double> seatTypePriceMap;

    /** 
     * Stores a list of all the holiday dates.
     */
    private ArrayList<LocalDate> holidayDates;

    /** 
     * Creates a new set of Settings for the entire application.
     */
    public Settings() {
        movieTypePriceMap = new HashMap<String, Double>();
        cinemaClassPriceMap = new HashMap<String, Double>();
        ageTypePriceMap = new HashMap<String, Double>();
        dayTypePriceMap = new HashMap<String, Double>();
        seatTypePriceMap = new HashMap<String, Double>();
        holidayDates = new ArrayList<LocalDate>();
    }

    /** 
     * Retrieves the price of the corresponding MovieType.
     * @param typeChoice MovieType to query for.
     * @return the price of the corresponding MovieType.
     */
    public double getMovieTypePrice(String typeChoice) {
        if (movieTypePriceMap.containsKey(typeChoice)) {
        return movieTypePriceMap.get(typeChoice);
        } else return 0;
    }

    /** 
     * Retrieves the price of the corresponding CinemaClass.
     * @param typeChoice CinemaClass to query for.
     * @return the price of the corresponding CinemaClass.
     */
    public double getCinemaClassPrice(String typeChoice) {
        if (cinemaClassPriceMap.containsKey(typeChoice)) {
            return cinemaClassPriceMap.get(typeChoice);
        } else return 0;
    }

    /** 
     * Retrieves the price of the corresponding AgeType.
     * @param typeChoice AgeType to query for.
     * @return the price of the corresponding AgeType.
     */
    public double getAgeTypePrice(String typeChoice) {
        if (ageTypePriceMap.containsKey(typeChoice)) {
            return ageTypePriceMap.get(typeChoice);
        } else return 0;
    }

    /** 
     * Retrieves the price of the corresponding DayType.
     * @param typeChoice DayType to query for.
     * @return the price of the corresponding DayType.
     */
    public double getDayTypePrice(String typeChoice) {
        if (dayTypePriceMap.containsKey(typeChoice)) {
            return dayTypePriceMap.get(typeChoice);
        } else return 0;
    }

    /** 
     * Retrieves the price of the corresponding SeatType.
     * @param typeChoice SeatType to query for.
     * @return the price of the corresponding SeatType.
     */
    public double getSeatTypePrice(String typeChoice) {
        if (seatTypePriceMap.containsKey(typeChoice)) {
            return seatTypePriceMap.get(typeChoice);
        } else return 0;
    }

    /** 
     * Retrieves a list of all holiday dates.
     * @return a list of all current set holiday dates within the Application.
     */
    public ArrayList<LocalDate> getHolidayDates() {
        return holidayDates;
    }

    /** 
     * Sets a new price for the corresponding MovieType.
     * @param typeChoice MovieType to set the new price for.
     * @param price New Price to set.
     */
    public void setMovieTypePrice(String typeChoice, double price) {
        movieTypePriceMap.put(typeChoice, price);
    }

    /** 
     * Sets a new price for the corresponding CinemaClass.
     * @param typeChoice CinemaClass to set the new price for.
     * @param price New Price to set.
     */
    public void setCinemaClassPrice(String typeChoice, double price) {
        cinemaClassPriceMap.put(typeChoice, price);
    }

    /** 
     * Sets a new price for the corresponding AgeType.
     * @param typeChoice AgeType to set the new price for.
     * @param price New Price to set.
     */
    public void setAgeTypePrice(String typeChoice, double price) {
        ageTypePriceMap.put(typeChoice, price);
    }

    /** 
     * Sets a new price for the corresponding DayType.
     * @param typeChoice DayType to set the new price for.
     * @param price New Price to set.
     */
    public void setDayTypePrice(String typeChoice, double price) {
        dayTypePriceMap.put(typeChoice, price);
    }

    /** 
     * Sets a new price for the corresponding SeatType.
     * @param typeChoice SeatType to set the new price for.
     * @param price New Price to set.
     */
    public void setSeatTypePrice(String typeChoice, double price) {
        seatTypePriceMap.put(typeChoice, price);
    }

    /** 
     * Resets the current set of holiday dates to a new set of holiday dates.
     * @param holidayDates new set of holiday dates to set into the Settings of the Application.
     */
    public void setHolidayDates(ArrayList<LocalDate> holidayDates) {
        this.holidayDates = holidayDates;
    }
}
