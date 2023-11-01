package portsim.port;

/**
 * A type of quay designed especially for the loading and unloading of ContainerShips.
 */
public class ContainerQuay extends Quay {
    /** The maximum number of containers allowed at a Quay. */
    private final int maxContainers;

    /**
     * Creates a new Container Quay with the given ID and maximum number of containers.
     * @param id quay ID.
     * @param maxContainers maximum number of containers the quay can handle.
     */
    public ContainerQuay(int id, int maxContainers) {
        super(id);

        this.maxContainers = maxContainers;
    }

    /**
     * Returns the maximum number of containers of this quay can process at once.
     * @return maxContainers.
     */
    public int getMaxContainers() {
        return maxContainers;
    }

    /**
     * Returns the human-readable string representation of this ContainerQuay.
     * @return string representation of this ContainerQuay.
     */
    @Override
    public String toString() {
        return super.toString() + " - " + this.getMaxContainers();
    }
}
