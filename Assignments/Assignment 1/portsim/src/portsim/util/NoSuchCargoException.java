package portsim.util;

/**
 * Exception thrown when a ship that is already unloaded is attempted to be unloaded.
 */
public class NoSuchCargoException extends Exception {
    /**
     * Constructs a new NoSuchCargoException with no detail message or cause.
     */
    public NoSuchCargoException() {
        super();
    }

    /**
     * Constructs a NoSuchCargoException that contains a helpful detail message explaining why the
     * exception occurred.
     * @param message detail message.
     */
    public NoSuchCargoException(String message) {
        super(message);
    }
}
