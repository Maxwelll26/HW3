public class EmptyStackException extends StackException{

    /**
     * Constructs a new EmptyStackException with no specific detail message - empty constractor.
     */
    public EmptyStackException() {
    }

    /**
     * Constructs a new EmptyStackException with the specified detail message.
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public EmptyStackException(String message) {super(message);}

    /**
     * Constructs a new EmptyStackException with the specified detail message and cause.
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   the cause (which is saved for later retrieval by the getCause() method)
     */
    public EmptyStackException(String message, Throwable cause) {
        super(message, cause);
    }




}
