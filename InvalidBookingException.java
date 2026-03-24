/**
 * =============================================================================
 * CLASS - InvalidBookingException
 * =============================================================================
 * 
 * Use Case 9: Error Handling & Validation
 * 
 * Description:
 * Custom exception used to represent invalid 
 * booking scenarios or inconsistent system states.
 * 
 * Using domain-specific exceptions makes error 
 * causes explicit and improves reliability.
 * 
 * @version 9.0
 */
public class InvalidBookingException extends Exception {

    /**
     * Creates a new invalid booking exception.
     * 
     * @param message informative error message
     */
    public InvalidBookingException(String message) {
        super(message);
    }
}
