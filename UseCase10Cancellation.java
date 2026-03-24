import java.util.Stack;

/**
 * =============================================================================
 * MAIN CLASS - UseCase10Cancellation
 * =============================================================================
 * 
 * Use Case 10: Booking Cancellation & Inventory Rollback
 * 
 * Description:
 * This class demonstrates the cancellation of 
 * confirmed bookings and the LIFO rollback of 
 * inventory states.
 * 
 * @version 10.0
 */
public class UseCase10Cancellation {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   BOOK MY STAY - HOTEL MANAGEMENT SYSTEM        ");
        System.out.println("   UseCase 10: Cancellation & Rollback           ");
        System.out.println("==================================================");

        // 1. Setup
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService allocationService = new RoomAllocationService();
        CancellationService cancellationService = new CancellationService();

        try {
            // 2. Perform some bookings
            System.out.println("[Step 1] Creating initial bookings...");
            Reservation res1 = new Reservation("John Doe", "Suite Room");
            Reservation res2 = new Reservation("Jane Smith", "Suite Room");
            
            allocationService.allocateRoom(res1, inventory);
            allocationService.allocateRoom(res2, inventory);

            // 3. Perform a cancellation
            System.out.println("\n[Step 2] Testing cancellation and rollback...");
            cancellationService.cancelBooking(res2, inventory);

            // 4. Verify Rollback Stack (LIFO)
            System.out.println("\n[Step 3] Verifying Rollback Structure (Stack):");
            Stack<String> stack = cancellationService.getReleasedRoomIds();
            if (!stack.isEmpty()) {
                System.out.println("Last Released Room (TOP of stack): " + stack.peek());
            }

            // 5. Test invalid cancellation (Double cancellation)
            System.out.println("\n[Step 4] Testing invalid state handling...");
            cancellationService.cancelBooking(res2, inventory);

        } catch (InvalidBookingException e) {
            System.err.println("🚨 ERROR: " + e.getMessage());
        }

        System.out.println("\n==================================================");
        System.out.println("   UseCase 10: Rollback Test Complete            ");
        System.out.println("==================================================");
    }
}
