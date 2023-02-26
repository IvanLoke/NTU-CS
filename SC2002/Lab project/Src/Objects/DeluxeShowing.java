package Objects;

import java.time.LocalDateTime;

import Enums.DayType;
import Enums.SeatType;

/** 
 * Represents a particular Showing of a Movie within a DELUXE class Cinema.
 * @author Chay Hui Xiang
 * @version 1.0
 * @since 2022-11-11
 */
public class DeluxeShowing extends Showing {

    /** 
     * Creates a new Showing of a Movie within a DELUXE class Cinema.
     * @param movie Movie for which this Showing corresponds to.
     * @param showTime Timing for which this Showing will air.
     * @param dayType Day Type for when this Showing will air (ie. WEEKDAY, WEEKEND, PUBLIC_HOLIDAY).
     */
    public DeluxeShowing(Movie movie, LocalDateTime showTime, DayType dayType) {
        super(movie, showTime, dayType);
        Seat[][] layout = new Seat[7][6]; // rows 0-6

        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                String seatId = (char) (i + 65) + String.valueOf(j);
                layout[i][j] = new Seat(seatId, SeatType.ELITE);
            }
        }
        this.seatLayout = layout;
    }
}
