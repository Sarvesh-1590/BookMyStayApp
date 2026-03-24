import java.util.List;

/**
 * =============================================================================
 * MAIN CLASS - UseCase7AddOnServiceSelection
 * =============================================================================
 * 
 * Use Case 7: Add-On Service Selection
 * 
 * Description:
 * This class demonstrates how optional 
 * services can be attached to a confirmed 
 * booking.
 * 
 * Services are added after room allocation 
 * and do not affect inventory.
 * 
 * @version 7.0
 */
public class UseCase7AddOnServiceSelection {

    /**
     * Application entry point.
     * 
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   BOOK MY STAY - HOTEL MANAGEMENT SYSTEM        ");
        System.out.println("   UseCase 7: Add-On Service Selection           ");
        System.out.println("==================================================");

        // 1. Initial System Setup (Case 3, 5, 6)
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService allocationService = new RoomAllocationService();
        AddOnServiceManager serviceManager = new AddOnServiceManager();

        // 2. Submit and Confirm a Reservation
        System.out.println("[Step 1] Confirming a Reservation...");
        Reservation guestReservation = new Reservation("John Doe", "Suite Room");
        try {
            allocationService.allocateRoom(guestReservation, inventory);
        } catch (InvalidBookingException e) {
            System.err.println("Booking Validation Error: " + e.getMessage());
        }

        // Assume the allocated Room ID is captured from the system output or state
        // In a real system, allocateRoom might return the Room ID.
        // For this demo, let's use a known predictable ID or a placeholder.
        String confirmedRoomId = "STE-301"; // Placeholder for demonstration
        System.out.println("Guest " + guestReservation.getGuestName() + " has confirmed Room: " + confirmedRoomId + "\n");

        // 3. Select Add-On Services
        System.out.println("[Step 2] Selecting Add-On Services...");
        AddOnService breakfast = new AddOnService("Buffet Breakfast", 25.0);
        AddOnService spa = new AddOnService("Full Body Spa", 80.0);
        AddOnService pickup = new AddOnService("Airport Pickup", 40.0);

        serviceManager.addService(confirmedRoomId, breakfast);
        serviceManager.addService(confirmedRoomId, spa);
        serviceManager.addService(confirmedRoomId, pickup);
        System.out.println("");

        // 4. Calculate Final Costs
        System.out.println("[Step 3] Final Reservation Summary:");
        System.out.println("--------------------------------------------------");
        System.out.println("Guest Name       : " + guestReservation.getGuestName());
        System.out.println("Assigned Room    : " + confirmedRoomId);
        
        System.out.println("\nSelected Add-Ons :");
        List<AddOnService> selectedServices = serviceManager.getServices(confirmedRoomId);
        for (AddOnService service : selectedServices) {
            System.out.println(" - " + service.getServiceName() + ": $" + service.getCost());
        }

        double addonTotal = serviceManager.calculateTotalServiceCost(confirmedRoomId);
        System.out.println("--------------------------------------------------");
        System.out.println("Total Add-On Cost: $" + addonTotal);
        System.out.println("==================================================");
    }
}
