public class StackOverflowException extends StackException{


 /**
     * Constructs a new StackOverflowException with no specific detail message - empty constractor.
     */
 /*
 public StackOverflowException() {
    }

  */

 /**
     * Constructs a new NegativeCapacityException with the specified detail message.
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
 public StackOverflowException (String message) {super(message);}


 /**
     * Constructs a new NegativeCapacityException with the specified detail message and cause.
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   the cause (which is saved for later retrieval by the getCause() method)
     */
 /*
 public StackOverflowException(String message, Throwable cause) {
        super(message, cause);
    }

  */

}
