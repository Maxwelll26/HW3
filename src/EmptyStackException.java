/**
 * Exception thrown when attempting to perform an operation on an empty stack.
 */
public class EmptyStackException extends StackException {
    /**
     * Constructs a new empty stack exception with no detail message.
     */
    public EmptyStackException() {
        super();
    }

    /**
     * Constructs a new empty stack exception with the specified detail message.
     *
     * @param message the detail message of the exception
     */
    public EmptyStackException(String message) {
        super(message);
    }

    /**
     * Constructs a new empty stack exception with the specified detail message and cause.
     *
     * @param message the detail message of the exception
     * @param cause   the cause of the exception
     */
    public EmptyStackException(String message, Throwable cause) {
        super(message, cause);
    }
}
