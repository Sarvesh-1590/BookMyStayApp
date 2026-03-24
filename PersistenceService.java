import java.io.*;

/**
 * =============================================================================
 * CLASS - PersistenceService
 * =============================================================================
 * 
 * Use Case 12: Data Persistence & System Recovery
 * 
 * Description:
 * This service handles the serialization and 
 * deserialization of the system state to a file.
 * 
 * It ensures that critical data like inventory and 
 * booking history survive application restarts.
 * 
 * @version 12.0
 */
public class PersistenceService {

    private String storagePath;

    /**
     * Initializes the persistence service.
     * 
     * @param storagePath path to the persistence file
     */
    public PersistenceService(String storagePath) {
        this.storagePath = storagePath;
    }

    /**
     * Persists the system state to a file.
     * 
     * @param inventory current room inventory
     * @param history current booking history
     * @throws IOException if saving fails
     */
    public void saveSystemState(RoomInventory inventory, BookingHistory history) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storagePath))) {
            oos.writeObject(inventory);
            oos.writeObject(history);
            System.out.println("💾 System state saved successfully to: " + storagePath);
        }
    }

    /**
     * Loads the system state from a file.
     * 
     * @return an array containing {RoomInventory, BookingHistory}
     * @throws IOException if reading fails
     * @throws ClassNotFoundException if data is corrupted
     */
    public Object[] loadSystemState() throws IOException, ClassNotFoundException {
        File file = new File(storagePath);
        if (!file.exists()) {
            System.out.println("⚠️ No persistence file found. Starting with fresh state.");
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storagePath))) {
            RoomInventory inventory = (RoomInventory) ois.readObject();
            BookingHistory history = (BookingHistory) ois.readObject();
            System.out.println("🔄 System state restored successfully from: " + storagePath);
            return new Object[]{inventory, history};
        }
    }
}
