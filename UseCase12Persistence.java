import java.io.IOException;

/**
 * =============================================================================
 * MAIN CLASS - UseCase12Persistence
 * =============================================================================
 * 
 * Use Case 12: Data Persistence & System Recovery
 * 
 * Description:
 * This class demonstrates how the system state is 
 * preserved across application restarts.
 * 
 * It simulates a "shutdown" by saving the state 
 * and a "start/recovery" by loading it back.
 * 
 * @version 12.0
 */
public class UseCase12Persistence {

    private static final String STATE_FILE = "system_state.dat";

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   BOOK MY STAY - HOTEL MANAGEMENT SYSTEM        ");
        System.out.println("   UseCase 12: Data Persistence & Recovery       ");
        System.out.println("==================================================");

        PersistenceService persistenceService = new PersistenceService(STATE_FILE);

        // --- SESSION 1: Booking and Saving ---
        System.out.println("\n[Phase 1] Session 1: Simulation (Active Bookings)");
        System.out.println("----------------------------------------------");
        RoomInventory session1Inventory = new RoomInventory();
        BookingHistory session1History = new BookingHistory();
        RoomAllocationService allocationService = new RoomAllocationService();

        try {
            // Perform a booking
            Reservation res = new Reservation("Persistence Guest", "Suite Room");
            allocationService.allocateRoom(res, session1Inventory);
            session1History.addRecord(res);

            // Save state
            persistenceService.saveSystemState(session1Inventory, session1History);
        } catch (IOException | InvalidBookingException e) {
            System.err.println("🚨 Session 1 Error: " + e.getMessage());
        }

        System.out.println("\n--- [SYSTEM RESTART SIMULATED] ---\n");

        // --- SESSION 2: Recovery ---
        System.out.println("[Phase 2] Session 2: Recovery (Restoring Data)");
        System.out.println("----------------------------------------------");
        RoomInventory session2Inventory;
        BookingHistory session2History;

        try {
            Object[] restoredData = persistenceService.loadSystemState();
            if (restoredData != null) {
                session2Inventory = (RoomInventory) restoredData[0];
                session2History = (BookingHistory) restoredData[1];

                // Verify recovery
                System.out.println("✅ Data Restoration Audit:");
                System.out.println("   - Recovered Booking Count: " + session2History.getFullHistory().size());
                System.out.println("   - Recovered Suite Inventory: " + session2Inventory.getRoomAvailability().get("Suite Room"));
                
                Reservation recoveredRes = session2History.getFullHistory().get(0);
                System.out.println("   - Recovered Guest Name: " + recoveredRes.getGuestName());
                System.out.println("   - Recovered Assigned Room: " + recoveredRes.getRoomId());
            } else {
                System.err.println("❌ No data recovered.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("🚨 Session 2 Error: " + e.getMessage());
        }

        System.out.println("==================================================");
        System.out.println("   UseCase 12: Recovery Test Complete            ");
        System.out.println("==================================================");
    }
}
