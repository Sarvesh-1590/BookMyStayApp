/**
 * =============================================================================
 * MAIN CLASS - UseCase5BookingRequestQueue
 * =============================================================================
 * 
 * Use Case 5: Booking Request (First-Come-First-Served)
 * 
 * Description:
 * This class demonstrates how booking
 * requests are accepted and queued 
 * in a fair and predictable order.
 * 
 * No room allocation or inventory 
 * update is performed here.
 * 
 * @version 5.0
 */
public class UseCase5BookingRequestQueue {

    /**
     * Application entry point.
     * 
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        // Display application header
        System.out.println("Welcome to the Hotel Booking Management System!");
        System.out.println("Use Case 5: Booking Request (First-Come-First-Served)\n");

        // Initialize booking queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Create booking requests
        System.out.println("Guest requests arriving...");
        Reservation r1 = new Reservation("Abhi", "Single Room");
        Reservation r2 = new Reservation("Subha", "Double Room");
        Reservation r3 = new Reservation("Vanmathi", "Suite Room");

        // Add requests to the queue
        bookingQueue.addRequest(r1);
        System.out.println(" - Request added for: " + r1.getGuestName() + " (" + r1.getRoomType() + ")");
        bookingQueue.addRequest(r2);
        System.out.println(" - Request added for: " + r2.getGuestName() + " (" + r2.getRoomType() + ")");
        bookingQueue.addRequest(r3);
        System.out.println(" - Request added for: " + r3.getGuestName() + " (" + r3.getRoomType() + ")");

        // Display queued booking requests from the queue in FIFO order
        System.out.println("\n--- Processing Queued Requests (FIFO Order) ---");
        int count = 1;
        while (bookingQueue.hasPendingRequests()) {
            Reservation next = bookingQueue.getNextRequest();
            System.out.println("Processing Request #" + (count++) + ":");
            System.out.println(" - Guest: " + next.getGuestName());
            System.out.println(" - Room Type: " + next.getRoomType());
            System.out.println(" - Status: Waiting for allocation\n");
        }

        System.out.println("All pending requests have been retrieved for processing.");
        System.out.println("System execution completed.");
    }
}
