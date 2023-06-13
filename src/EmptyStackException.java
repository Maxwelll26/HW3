/**
 * Exception thrown when attempting to perform an operation on an empty stack.
 */
public class EmptyStackException extends StackException {
    /**
     * Constructs a new empty stack exception with the specified detail message.
     *
     * @param message the detail message of the exception
     */
    public EmptyStackException(String message) {
        super(message);
    }
}
