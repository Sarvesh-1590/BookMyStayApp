import java.util.Map;

/**
 * =============================================================================
 * CLASS - BookingValidator
 * =============================================================================
 * 
 * Use Case 9: Error Handling & Validation
 * 
 * Description:
 * This utility class provides structured validation 
 * for booking requests and system constraints.
 * 
 * It implements a "Fail-Fast" design, raising errors 
 * before any state changes occur.
 * 
 * @version 9.0
 */
public class BookingValidator {

    /**
     * Validates a booking request against system rules.
     * 
     * @param reservation the request to validate
     * @param inventory the current system inventory
     * @throws InvalidBookingException if validation fails
     */
    public static void validateBookingRequest(Reservation reservation, RoomInventory inventory) 
            throws InvalidBookingException {
        
        // 1. Validate Guest Name (Input Validation)
        if (reservation.getGuestName() == null || reservation.getGuestName().trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty or null.");
        }

        // 2. Validate Room Type (Input Validation)
        String roomType = reservation.getRoomType();
        Map<String, Integer> availability = inventory.getRoomAvailability();
        
        if (!availability.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type requested: [" + roomType + "].");
        }

        // 3. Validate Availability (System Constraint)
        if (availability.get(roomType) <= 0) {
            throw new InvalidBookingException("Resource Exhausted: No " + roomType + "s available.");
        }
    }
}
