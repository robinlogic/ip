package dyke.parse;

/**
 * Provides a way to throw errors unique to DYKE
 */
public class DykeException extends Exception {
    /**
     * Handler for catching {@code DykeException}
     * @param message represents the error caught
     */
    public DykeException(String message) {

        super(message);
    }

}
