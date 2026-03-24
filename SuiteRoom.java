/**
 * Concrete class representing a Suite Room.
 */
public class SuiteRoom extends Room {
    public SuiteRoom() {
        super(3, 600, 350.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("--- Suite Room ---");
        super.displayRoomDetails();
    }
}
