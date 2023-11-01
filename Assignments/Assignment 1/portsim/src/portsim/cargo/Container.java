package portsim.cargo;

/**
 * This class represents a shipping container. A container can hold certain types of cargo
 * and can be loaded onto a ship for transport
 */
public class Container extends Cargo {
    /** The type of container. */
    private final ContainerType type;

    /**
     * Constructor for the Container class.
     * @param id the identification number for this Container.
     * @param destination the destination for this Container.
     * @param type the type of this Container.
     */
    public Container(int id, String destination, ContainerType type) {
        super(id, destination);

        this.type = type;
    }

    /**
     * Returns the type of this Container.
     * @return type the type of this Container.
     */
    public ContainerType getType() {
        return type;
    }

    /**
     * Returns the human-readable string representation of this Container.
     * @return string representation for this Container.
     */
    @Override
    public String toString() {
        return super.toString() + " [" + this.getType() + "]";
    }

}
