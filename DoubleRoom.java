/**
 * Concrete class representing a Double Room.
 */
public class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 350, 180.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("--- Double Room ---");
        super.displayRoomDetails();
    }
}
