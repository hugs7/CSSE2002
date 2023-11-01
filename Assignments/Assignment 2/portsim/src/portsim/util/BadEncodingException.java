package portsim.util;

/**
 * Exception thrown when an encoded string is not correct according to the appropriate
 * {@code fromString()} method.
 */
public class BadEncodingException extends Exception {
    /**
     * Constructs a new {@code BadCodingException} with no detail message or cause.
     */
    public BadEncodingException() {
        super();
    }

    /**
     * Constructs a {@code BadEncodingException} that contains a helpful detail message
     * explaining why the exception occurred.
     * @param message detail message
     */
    public BadEncodingException(String message) {
        super(message);
    }

    /**
     * Constructs a {@code BadEncodingException} that stores the underlying cause of the exception.
     * @param cause throwable that caused this exception
     */
    public BadEncodingException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a {@code BadEncodingException} that contains a helpful detail message explaining
     * why the exception occurred and the underlying cause of the exception.
     * @param message detail message
     * @param cause throwable that caused this exception
     */
    public BadEncodingException(String message, Throwable cause) {
        super(message, cause);
    }
}
