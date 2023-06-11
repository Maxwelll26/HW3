/**
 * A custom exception class for negative capacity related exceptions in a stack.
 * This class extends the StackException class.
 */
public class NegativeCapacityException extends StackException{

 /**
 * Constructs a new NegativeCapacityException with no specific detail message - empty constractor.
 */
 public NegativeCapacityException() {
    }

 /**
 * Constructs a new NegativeCapacityException with the specified detail message.
 * @param message the detail message (which is saved for later retrieval by the getMessage() method)
 */
 public NegativeCapacityException(String message) {super(message);}

 /**
 * Constructs a new NegativeCapacityException with the specified detail message and cause.
 * @param message the detail message (which is saved for later retrieval by the getMessage() method)
 * @param cause   the cause (which is saved for later retrieval by the getCause() method)
 */
 public NegativeCapacityException(String message, Throwable cause) {
            super(message, cause);
        }

}

