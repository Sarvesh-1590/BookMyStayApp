/**
 * =============================================================================
 * MAIN CLASS - UseCase8BookingHistory
 * =============================================================================
 * 
 * Use Case 8: Booking History & Reporting
 * 
 * Description:
 * This class demonstrates the historical tracking 
 * of confirmed bookings and operational reporting.
 * 
 * It ensures that confirmed reservations are 
 * added to the history and summary reports 
 * are generated.
 * 
 * @version 8.0
 */
public class UseCase8BookingHistory {

    /**
     * Application entry point.
     * 
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   BOOK MY STAY - HOTEL MANAGEMENT SYSTEM        ");
        System.out.println("   UseCase 8: Booking History & Reporting        ");
        System.out.println("==================================================");

        // 1. Initial System Setup
        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue queue = new BookingRequestQueue();
        RoomAllocationService allocationService = new RoomAllocationService();
        BookingHistory bookingHistory = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // 2. Submit Incoming Requests (Simulate 5 requests)
        System.out.println("[Step 1] Receiving Booking Requests...");
        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Double Room"));
        queue.addRequest(new Reservation("Charlie", "Single Room"));
        queue.addRequest(new Reservation("David", "Suite Room"));
        queue.addRequest(new Reservation("Eve", "Double Room"));
        System.out.println("");

        // 3. Process and Record in History
        System.out.println("[Step 2] Processing and Recording Bookings...");
        System.out.println("----------------------------------------------");
        while (queue.hasPendingRequests()) {
            Reservation nextRequest = queue.getNextRequest();
            try {
                allocationService.allocateRoom(nextRequest, inventory);
                // Record in history ONLY if confirmed
                bookingHistory.addRecord(nextRequest);
            } catch (InvalidBookingException e) {
                System.err.println("Audit Error: " + e.getMessage());
            }
        }

        // 4. Operational Reporting
        System.out.println("\n[Step 3] Admin Reporting View");
        System.out.println("==============================================");
        
        // Detailed History
        reportService.displayDetailedHistory(bookingHistory);
        
        // Summary Report
        reportService.generateSummaryReport(bookingHistory);

        System.out.println("[FINISH] UseCase 8 - Audit Trail Verified.");
        System.out.println("==============================================");
    }
}
