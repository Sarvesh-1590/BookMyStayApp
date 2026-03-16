/**
 * =============================================================================
 * MAIN CLASS - UseCase2HotelBookingApp
 * =============================================================================
 * 
 * Use Case 2: Basic Room Types & Static Availability
 * 
 * Description:
 * This class introduces object modeling and inheritance.
 * It demonstrates how to use abstract and concrete classes
 * to model different room types.
 */
public class UseCase2HotelBookingApp {

    public static void main(String[] args) {
        System.out.println("Welcome to the Hotel Booking Management System!");
        System.out.println("Use Case 2: Basic Room Types & Static Availability\n");

        // Initialize room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability variables (simulating system state)
        int singleRoomAvailability = 5;
        int doubleRoomAvailability = 3;
        int suiteRoomAvailability = 2;

        // Display room details and availability
        single.displayRoomDetails();
        System.out.println(" - Current Availability: " + singleRoomAvailability);
        System.out.println();

        doubleRoom.displayRoomDetails();
        System.out.println(" - Current Availability: " + doubleRoomAvailability);
        System.out.println();

        suite.displayRoomDetails();
        System.out.println(" - Current Availability: " + suiteRoomAvailability);
        System.out.println();

        System.out.println("System execution completed.");
    }
}
