/**
 * Concrete class representing a Single Room.
 */
public class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 200, 100.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("--- Single Room ---");
        super.displayRoomDetails();
    }
}
