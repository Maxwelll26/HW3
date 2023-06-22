/**
 * Exception thrown when a song already exists. New exception for playlist class.
 */
public class SongAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new SongAlreadyExistsException with no detail message.
     */
    public SongAlreadyExistsException() {
        super();
    }

    /**
     * Constructs a new SongAlreadyExistsException with the specified detail message.
     * @param string the detail message
     */
    public SongAlreadyExistsException (String string) {
        super(string);
    }

    /**
     * Constructs a new SongAlreadyExistsException with the specified detail message and cause.
     * @param string the detail message
     * @param cause   the cause of the exception
     */
    public SongAlreadyExistsException (String string, Throwable cause){
        super(string, cause);
    }
}
