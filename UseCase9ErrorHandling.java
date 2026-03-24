/**
 * =============================================================================
 * MAIN CLASS - UseCase9ErrorHandling
 * =============================================================================
 * 
 * Use Case 9: Error Handling & Validation
 * 
 * Description:
 * This class demonstrates robust error handling 
 * and input validation within the booking system.
 * 
 * It tests various failure scenarios to ensure 
 * the system fails fast and stays stable.
 * 
 * @version 9.0
 */
public class UseCase9ErrorHandling {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   BOOK MY STAY - HOTEL MANAGEMENT SYSTEM        ");
        System.out.println("   UseCase 9: Error Handling & Validation        ");
        System.out.println("==================================================");

        // 1. Setup
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService allocationService = new RoomAllocationService();

        // 2. Scenario 1: Invalid Room Type
        System.out.println("[Scenario 1] Requesting non-existent room type...");
        processBooking(new Reservation("Alice", "Penthouse"), allocationService, inventory);

        // 3. Scenario 2: Missing Guest Name
        System.out.println("[Scenario 2] Requesting with empty guest name...");
        processBooking(new Reservation("", "Single Room"), allocationService, inventory);

        // 4. Scenario 3: Resource Exhaustion (Negative path)
        System.out.println("[Scenario 3] Exhausting inventory for Suite Rooms...");
        processBooking(new Reservation("Bob", "Suite Room"), allocationService, inventory);
        processBooking(new Reservation("Charlie", "Suite Room"), allocationService, inventory);
        processBooking(new Reservation("David", "Suite Room"), allocationService, inventory); // Should fail

        // 5. Scenario 4: Valid Path (Post-Errors)
        System.out.println("[Scenario 4] Verifying system stability with valid request...");
        processBooking(new Reservation("Eve", "Double Room"), allocationService, inventory);

        System.out.println("==================================================");
        System.out.println("   UseCase 9: Validation Test Complete           ");
        System.out.println("==================================================");
    }

    /**
     * Helper method to handle booking and catch exceptions.
     */
    private static void processBooking(Reservation res, RoomAllocationService service, RoomInventory inv) {
        try {
            service.allocateRoom(res, inv);
        } catch (InvalidBookingException e) {
            System.err.println("🚨 VALIDATION ERROR: " + e.getMessage());
            System.out.println("--------------------------------------------------");
        }
    }
}
