import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * =============================================================================
 * CLASS - RoomAllocationService
 * =============================================================================
 * 
 * Use Case 6: Reservation Confirmation & Room Allocation
 * 
 * Description:
 * This class is responsible for confirming 
 * booking requests and assigning rooms.
 * 
 * It ensures:
 * - Each room ID is unique
 * - Inventory is updated immediately
 * - No room is double-booked
 * 
 * @version 6.0
 */
public class RoomAllocationService {

    /** Stores all allocated room IDs to prevent duplicate assignments. */
    private Set<String> allocatedRoomIds;

    /**
     * Stores assigned room IDs by room type.
     * 
     * Key   -> Room type
     * Value -> Set of assigned room IDs
     */
    private Map<String, Set<String>> assignedRoomsByType;

    /**
     * Initializes allocation tracking structures.
     */
    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
        assignedRoomsByType.put("Single Room", new HashSet<>());
        assignedRoomsByType.put("Double Room", new HashSet<>());
        assignedRoomsByType.put("Suite Room", new HashSet<>());
    }

    /**
     * Confirms a booking request by assigning 
     * a unique room ID and updating inventory.
     * 
     * @param reservation booking request
     * @param inventory centralized room inventory
     */
    public void allocateRoom(Reservation reservation, RoomInventory inventory) {
        String roomType = reservation.getRoomType();
        Map<String, Integer> currentAvailability = inventory.getRoomAvailability();

        System.out.println("Processing request for " + reservation.getGuestName() + " (" + roomType + ")...");

        if (currentAvailability.containsKey(roomType) && currentAvailability.get(roomType) > 0) {
            // Generate unique room ID
            String roomId = generateRoomId(roomType);

            // Uniqueness Enforcement
            allocatedRoomIds.add(roomId);
            assignedRoomsByType.get(roomType).add(roomId);

            // Inventory Synchronization (Decrement count)
            int newCount = currentAvailability.get(roomType) - 1;
            inventory.updateAvailability(roomType, newCount);

            System.out.println("✅ Booking CONFIRMED");
            System.out.println("   - Room Assigned: " + roomId);
            System.out.println("   - Inventory Updated: " + newCount + " " + roomType + "(s) remaining.");
        } else {
            System.out.println("❌ Booking FAILED");
            System.out.println("   - Reason: " + roomType + " is out of stock.");
        }
        System.out.println("--------------------------------------------------");
    }

    /**
     * Generates a unique room ID 
     * for the given room type.
     * 
     * @param roomType type of room
     * @return unique room ID
     */
    private String generateRoomId(String roomType) {
        String prefix = "R";
        if (roomType.contains("Single")) prefix = "SING";
        else if (roomType.contains("Double")) prefix = "DBL";
        else if (roomType.contains("Suite")) prefix = "STE";

        int floor = (int) (Math.random() * 5) + 1;
        int roomNumber = (int) (Math.random() * 50) + 1;
        String id;

        // Ensure absolute uniqueness within the service session
        do {
            id = prefix + "-" + (floor * 100 + roomNumber);
            roomNumber++;
        } while (allocatedRoomIds.contains(id));

        return id;
    }
}
