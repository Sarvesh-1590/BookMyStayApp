import java.util.ArrayList;
import java.util.List;

/**
 * =============================================================================
 * MAIN CLASS - UseCase11ConcurrentBooking
 * =============================================================================
 * 
 * Use Case 11: Concurrent Booking Simulation (Thread Safety)
 * 
 * Description:
 * This class simulates a multi-user environment where 
 * multiple guests attempt to book rooms simultaneously.
 * 
 * It demonstrates how synchronization prevents race 
 * conditions and ensures a consistent system state.
 * 
 * @version 11.0
 */
public class UseCase11ConcurrentBooking {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   BOOK MY STAY - HOTEL MANAGEMENT SYSTEM        ");
        System.out.println("   UseCase 11: Concurrent Booking Simulation     ");
        System.out.println("==================================================");

        // 1. Shared Resources
        RoomInventory sharedInventory = new RoomInventory();
        BookingRequestQueue sharedQueue = new BookingRequestQueue();
        RoomAllocationService sharedAllocationService = new RoomAllocationService();

        // 2. Simulate 10 concurrent guest requests
        System.out.println("[Step 1] Initializing Concurrent Requests...");
        List<Thread> userThreads = new ArrayList<>();
        
        for (int i = 1; i <= 10; i++) {
            final String guestName = "Guest-" + i;
            String roomType = (i % 2 == 0) ? "Double Room" : "Single Room";
            
            Thread thread = new Thread(() -> {
                Reservation request = new Reservation(guestName, roomType);
                sharedQueue.addRequest(request);
                
                // Process request immediately (Multi-user interaction simulation)
                try {
                    Reservation next = sharedQueue.getNextRequest();
                    if (next != null) {
                        sharedAllocationService.allocateRoom(next, sharedInventory);
                    }
                } catch (InvalidBookingException e) {
                    System.err.println("🚨 CONCURRENCY ERROR [" + guestName + "]: " + e.getMessage());
                }
            });
            userThreads.add(thread);
        }

        // 3. Start all threads simultaneously
        System.out.println("[Step 2] Starting Simulation...");
        for (Thread t : userThreads) {
            t.start();
        }

        // 4. Wait for completion
        for (Thread t : userThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // 5. Final Audit
        System.out.println("\n[Step 3] Final System Audit:");
        System.out.println("----------------------------------------------");
        System.out.println("Remaining Inventory:");
        sharedInventory.getRoomAvailability().forEach((type, count) -> 
            System.out.println(" - " + type + ": " + count));
        System.out.println("==============================================");
    }
}
