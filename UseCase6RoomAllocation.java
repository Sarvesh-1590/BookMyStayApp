/**
 * =============================================================================
 * MAIN CLASS - UseCase6RoomAllocation
 * =============================================================================
 * 
 * Use Case 6: Reservation Confirmation & Room Allocation
 * 
 * Description:
 * This class demonstrates how booking 
 * requests are confirmed and rooms 
 * are allocated safely.
 * 
 * It consumes booking requests in FIFO 
 * order and updates inventory immediately.
 * 
 * @version 6.0
 */
public class UseCase6RoomAllocation {

    /**
     * Application entry point.
     * 
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   BOOK MY STAY - HOTEL MANAGEMENT SYSTEM        ");
        System.out.println("   UseCase 6: Room Allocation & Confirmation     ");
        System.out.println("==================================================");

        // 1. Setup Inventory (Case 3)
        RoomInventory inventory = new RoomInventory();

        // 2. Setup Booking Queue (Case 5)
        BookingRequestQueue queue = new BookingRequestQueue();

        // 3. Setup Allocation Service (Case 6)
        RoomAllocationService allocationService = new RoomAllocationService();

        // 4. Submit Incoming Requests
        System.out.println("Receiving Booking Requests...");
        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Double Room"));
        queue.addRequest(new Reservation("Charlie", "Single Room"));
        queue.addRequest(new Reservation("David", "Suite Room"));
        queue.addRequest(new Reservation("Eve", "Single Room"));
        queue.addRequest(new Reservation("Frank", "Single Room"));
        queue.addRequest(new Reservation("Grace", "Single Room")); // Request #6 for Single Room
        queue.addRequest(new Reservation("Henry", "Suite Room"));
        System.out.println("Current Queue: 8 requests pending.\n");

        // 5. Process and Confirm Reservations
        System.out.println("Processing Queue (First-Come-First-Served):");
        System.out.println("--------------------------------------------------");

        int processedCount = 1;
        while (queue.hasPendingRequests()) {
            System.out.print("[" + processedCount++ + "] ");
            Reservation nextRequest = queue.getNextRequest();
            allocationService.allocateRoom(nextRequest, inventory);
        }

        System.out.println("\nFinal Inventory Status:");
        inventory.getRoomAvailability().forEach((type, count) -> {
            System.out.println(" - " + type + ": " + count + " available");
        });
        System.out.println("==================================================");
    }
}
