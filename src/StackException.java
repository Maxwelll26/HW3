/**
 * A custom exception class for stack-related exceptions.
 * This class extends the RuntimeException class - that we can use.
 */

public class StackException extends RuntimeException{
    /**
     * Constructs a new StackException with no specific detail message - empty.
     */
    public StackException() {
    }

    /**
     * Constructs a new StackException with the specified detail message.
     * The massage will be accordingly to the problem. uses its super class - RuntimeException.
     * @param message the detail message.
     */
    public StackException(String message) {
        super(message);
    }

    /**
     * Constructs a new StackException with the specified detail message and cause.
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   the cause (which is saved for later retrieval by the getCause() method)
     */
    public StackException(String message, Throwable cause) {
        super(message, cause);
    }
}
