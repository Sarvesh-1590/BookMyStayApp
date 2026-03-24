import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * =============================================================================
 * CLASS - BookingHistory
 * =============================================================================
 * 
 * Use Case 8: Booking History & Reporting
 * 
 * Description:
 * This class maintains a chronological record 
 * of all confirmed reservations in the system.
 * 
 * It ensures that data is stored in the 
 * order of confirmation (FIFO).
 * 
 * @version 12.0
 */
public class BookingHistory implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    /** Internal list to store confirmed reservations. */
    private List<Reservation> history;

    /**
     * Initializes an empty booking history.
     */
    public BookingHistory() {
        this.history = new ArrayList<>();
    }

    /**
     * Adds a confirmed reservation to the history.
     * 
     * @param reservation confirmed booking
     */
    public void addRecord(Reservation reservation) {
        history.add(reservation);
        System.out.println("History Updated: Recorded booking for " + reservation.getGuestName());
    }

    /**
     * Returns a read-only view of the full history.
     * 
     * @return unmodifiable list of reservations
     */
    public List<Reservation> getFullHistory() {
        return Collections.unmodifiableList(history);
    }
}
