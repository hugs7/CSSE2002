package portsim.cargo;

/**
 * Denotes a cargo whose function is to be transported via a Ship or land transport.
 */
public abstract class Cargo {
    /** The ID of the Cargo as an integer. */
    private final int id;

    /** The destination of the Cargo as a string. */
    private final String destination;

    /**
     * Constructor for the Cargo class.
     * @param id the identification number for this Cargo.
     * @param destination the destination for this Cargo.
     * @throws IllegalArgumentException when id is less than 0.
     */
    public Cargo(int id, String destination) {
        if (id < 0) {
            throw new IllegalArgumentException("ID is invalid: " + id + " < 0");
        }

        this.id = id;
        this.destination = destination;
    }

    /**
     * Returns the ID for this Cargo.
     * @return id the identification number for this Cargo.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the destination for this Cargo.
     * @return destination the destination for this Cargo.
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Returns the human-readable string representation for this Cargo.
     * @return string representation for this Cargo.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + this.getId() + " to "
                + this.getDestination();
    }
}
