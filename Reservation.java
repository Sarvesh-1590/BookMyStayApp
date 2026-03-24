/**
 * =============================================================================
 * CLASS - Reservation
 * =============================================================================
 * 
 * Use Case 5: Booking Request (FIFO)
 * 
 * Description:
 * This class represents a booking request 
 * made by a guest.
 * 
 * At this stage, a reservation only captures 
 * intent, not confirmation or room allocation.
 * 
 * @version 5.0
 */
public class Reservation {

    /** Name of the guest making the booking. */
    private String guestName;

    /** Requested room type. */
    private String roomType;

    /** Assigned Room ID after confirmation. */
    private String roomId;

    /** Status of the reservation. */
    private boolean isCancelled;

    /**
     * Creates a new reservation request.
     * 
     * @param guestName name of the guest
     * @param roomType requested room type
     */
    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.isCancelled = false;
    }

    /** @return guest name */
    public String getGuestName() {
        return guestName;
    }

    /** @return requested room type */
    public String getRoomType() {
        return roomType;
    }

    /** @return assigned room ID */
    public String getRoomId() {
        return roomId;
    }

    /** @param roomId room ID to assign */
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    /** @return true if cancelled */
    public boolean isCancelled() {
        return isCancelled;
    }

    /** @param cancelled status */
    public void setCancelled(boolean cancelled) {
        this.isCancelled = cancelled;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "guestName='" + guestName + '\'' +
                ", roomType='" + roomType + '\'' +
                ", roomId='" + roomId + '\'' +
                ", isCancelled=" + isCancelled +
                '}';
    }
}
