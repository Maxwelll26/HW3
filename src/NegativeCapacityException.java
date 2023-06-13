/**
 * Exception thrown when attempting to create a stack with a negative capacity.
 */
public class NegativeCapacityException extends StackException {
    /**
     * Constructs a new negative capacity exception with the specified detail message.
     *
     * @param message the detail message of the exception
     */
    public NegativeCapacityException(String message) {
        super(message);
    }
}
