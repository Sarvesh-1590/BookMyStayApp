import java.util.Stack;

/**
 * =============================================================================
 * CLASS - CancellationService
 * =============================================================================
 * 
 * Use Case 10: Booking Cancellation & Inventory Rollback
 * 
 * Description:
 * This service manages the cancellation of confirmed 
 * bookings and performs inventory rollbacks.
 * 
 * It uses a Stack to track recently released room 
 * IDs, demonstrating LIFO (Last-In-First-Out) behavior.
 * 
 * @version 10.0
 */
public class CancellationService {

    /** Stack to track recently released room IDs for rollback. */
    private Stack<String> releasedRoomIds;

    /**
     * Initializes the cancellation service.
     */
    public CancellationService() {
        this.releasedRoomIds = new Stack<>();
    }

    /**
     * Cancels a confirmed reservation and restores inventory.
     * 
     * @param reservation the reservation to cancel
     * @param inventory system inventory
     * @throws InvalidBookingException if reservation is not cancellable
     */
    public void cancelBooking(Reservation reservation, RoomInventory inventory) 
            throws InvalidBookingException {
        
        // 1. Validation
        if (reservation.getRoomId() == null) {
            throw new InvalidBookingException("Cannot cancel: Reservation has no assigned Room ID.");
        }
        if (reservation.isCancelled()) {
            throw new InvalidBookingException("Cannot cancel: Reservation is already cancelled.");
        }

        System.out.println("Processing cancellation for " + reservation.getGuestName() + "...");

        // 2. State Reversal - Inventory Rollback
        String roomType = reservation.getRoomType();
        int currentCount = inventory.getRoomAvailability().get(roomType);
        inventory.updateAvailability(roomType, currentCount + 1);

        // 3. Track Released Room ID (Stack - LIFO)
        String releasedId = reservation.getRoomId();
        releasedRoomIds.push(releasedId);

        // 4. Update Reservation Status
        reservation.setCancelled(true);

        System.out.println("✅ Cancellation SUCCESSFUL");
        System.out.println("   - Room Released: " + releasedId + " (Added to Rollback Stack)");
        System.out.println("   - Inventory Restored: " + (currentCount + 1) + " " + roomType + "(s) now available.");
        System.out.println("--------------------------------------------------");
    }

    /**
     * Returns the stack of released room IDs.
     * 
     * @return rollback stack
     */
    public Stack<String> getReleasedRoomIds() {
        return releasedRoomIds;
    }
}
