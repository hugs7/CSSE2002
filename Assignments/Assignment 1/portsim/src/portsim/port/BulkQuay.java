package portsim.port;

/**
 * A type of quay designed especially for the loading and unloading of BulkCarriers.
 */
public class BulkQuay extends Quay {
    /** The maximum weight in tonnes allowed at the BulkQuay. */
    private final int maxTonnage;

    /**
     * Creates a new Bulk Quay with the given ID and max tonnage.
     * @param id quay ID.
     * @param maxTonnage maximum tonnage the quay can handle.
     */
    public BulkQuay(int id, int maxTonnage) {
        super(id);

        this.maxTonnage = maxTonnage;
    }

    /**
     * Returns the maximum number of tonnes of cargo this quay can handle.
     * @return maxTonnage.
     */
    public int getMaxTonnage() {
        return maxTonnage;
    }

    /**
     * Returns the human-readable string representation of this BulkQuay.
     * @return string representation of this quay.
     */
    @Override
    public String toString() {
        return super.toString() + " - " + this.getMaxTonnage();
    }
}
