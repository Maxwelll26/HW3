/**
 * Base exception class for stack-related exceptions.
 */
public class StackException extends RuntimeException {
    /**
     * Constructs a new stack exception with no detail message.
     */
    public StackException() {
        super();
    }

    /**
     * Constructs a new stack exception with the specified detail message.
     *
     * @param message the detail message of the exception
     */
    public StackException(String message) {
        super(message);
    }

    /**
     * Constructs a new stack exception with the specified detail message and cause.
     *
     * @param message the detail message of the exception
     * @param cause   the cause of the exception
     */
    public StackException(String message, Throwable cause) {
        super(message, cause);
    }
}
