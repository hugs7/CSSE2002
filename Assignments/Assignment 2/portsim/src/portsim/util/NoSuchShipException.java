package portsim.util;

/**
 * Exception thrown when a ship is requested by a given IMO number but no ship with that IMO number
 * exists.
 */
public class NoSuchShipException extends Exception {
    /**
     * Constructs a {@link NoSuchShipException} with no detail message or cause.
     */
    public NoSuchShipException() {
        super();
    }

    /**
     * Constructs a {@code NoSuchShipException} that contains a helpful detail message explaining
     * why the exception occurred.
     *
     * @param message detail message
     */
    public NoSuchShipException(String message) {
        super(message);
    }

    /**
     * Constructs a {@link NoSuchShipException} that stores the underlying cause of the exception.
     *
     * @param cause throwable that caused the exception
     */
    public NoSuchShipException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a {@link NoSuchShipException} that contains a helpful detail message explaining
     * why the exception occurred and the underlying cause of the exception.
     *
     * @param message detail message
     * @param cause throwable that caused the exception
     */
    public NoSuchShipException(String message, Throwable cause) {
        super(message, cause);
    }
}
