/**
 * Exception thrown when a stack overflows due to exceeding its maximum capacity.
 */
public class StackOverflowException extends StackException {
    /**
     * Constructs a new stack overflow exception with no detail message.
     */
    public StackOverflowException() {
        super();
    }

    /**
     * Constructs a new stack overflow exception with the specified detail message.
     *
     * @param message the detail message of the exception
     */
    public StackOverflowException(String message) {
        super(message);
    }

    /**
     * Constructs a new stack overflow exception with the specified detail message and cause.
     *
     * @param message the detail message of the exception
     * @param cause   the cause of the exception
     */
    public StackOverflowException(String message, Throwable cause) {
        super(message, cause);
    }
}
