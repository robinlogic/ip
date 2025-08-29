package dyke.parse;

/**
 * Provides a way to throw errors unique to DYKE
 */
public class DykeException extends Exception {
    public DykeException(String message) {

        super(message);
    }

}
