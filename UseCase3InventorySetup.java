import java.util.Map;

/**
 * =============================================================================
 * MAIN CLASS - UseCase3InventorySetup
 * =============================================================================
 * 
 * Use Case 3: Centralized Room Inventory Management
 * 
 * Description:
 * This class demonstrates how room availability 
 * is managed using a centralized inventory.
 * 
 * Room objects are used to retrieve pricing 
 * and room characteristics.
 * 
 * No booking or search logic is introduced here.
 * 
 * @version 3.1
 */
public class UseCase3InventorySetup {

    /**
     * Application entry point.
     * 
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the Hotel Booking Management System!");
        System.out.println("Use Case 3: Centralized Room Inventory Management\n");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Create room objects for metadata
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Display current inventory status
        System.out.println("--- Current Room Inventory ---");
        displayInventoryDetails(single, inventory);
        displayInventoryDetails(doubleRoom, inventory);
        displayInventoryDetails(suite, inventory);

        // Demonstrate controlled update
        System.out.println("Updating Suite Room availability to 5...");
        inventory.updateAvailability("Suite Room", 5);

        System.out.println("\n--- Updated Room Inventory ---");
        displayInventoryDetails(single, inventory);
        displayInventoryDetails(doubleRoom, inventory);
        displayInventoryDetails(suite, inventory);

        System.out.println("System execution completed.");
    }

    /**
     * Helper method to display room details along with inventory availability.
     * 
     * @param room the room object
     * @param inventory the inventory manager
     */
    private static void displayInventoryDetails(Room room, RoomInventory inventory) {
        String roomTypeName = "";
        if (room instanceof SingleRoom) roomTypeName = "Single Room";
        else if (room instanceof DoubleRoom) roomTypeName = "Double Room";
        else if (room instanceof SuiteRoom) roomTypeName = "Suite Room";

        int availability = inventory.getRoomAvailability().getOrDefault(roomTypeName, 0);
        
        room.displayRoomDetails();
        System.out.println(" - Current Availability: " + availability);
        System.out.println();
    }
}
