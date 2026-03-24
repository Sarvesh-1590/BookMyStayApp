import java.util.List;

/**
 * =============================================================================
 * CLASS - BookingReportService
 * =============================================================================
 * 
 * Use Case 8: Booking History & Reporting
 * 
 * Description:
 * This service generates operational reports 
 * from the stored booking history.
 * 
 * It provides visibility into system usage 
 * without modifying the underlying data.
 * 
 * @version 8.0
 */
public class BookingReportService {

    /**
     * Prints a summary report of all bookings.
     * 
     * @param history the booking history to analyze
     */
    public void generateSummaryReport(BookingHistory history) {
        List<Reservation> records = history.getFullHistory();
        
        System.out.println("\n--- [ADMIN] BOOKING SUMMARY REPORT ---");
        System.out.println("Total Volume: " + records.size() + " confirmed bookings.");

        int single = 0, dbl = 0, suite = 0;
        for (Reservation r : records) {
            String type = r.getRoomType();
            if (type.contains("Single")) single++;
            else if (type.contains("Double")) dbl++;
            else if (type.contains("Suite")) suite++;
        }

        System.out.println("Distribution by Room Type:");
        System.out.println("   - Single Rooms: " + single);
        System.out.println("   - Double Rooms: " + dbl);
        System.out.println("   - Suite Rooms : " + suite);
        System.out.println("--------------------------------------\n");
    }

    /**
     * Displays a detailed chronological log of bookings.
     * 
     * @param history the booking history to display
     */
    public void displayDetailedHistory(BookingHistory history) {
        List<Reservation> records = history.getFullHistory();
        
        System.out.println("--- [ADMIN] DETAILED BOOKING LOG ---");
        if (records.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (int i = 0; i < records.size(); i++) {
                Reservation r = records.get(i);
                System.out.printf("[%d] Guest: %-10s | Room Type: %s%n", 
                                  (i + 1), r.getGuestName(), r.getRoomType());
            }
        }
        System.out.println("------------------------------------\n");
    }
}
