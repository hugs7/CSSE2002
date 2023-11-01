package portsim.cargo;

/**
 * Bulk cargo is commodity cargo that is transported unpacked in large quantities.
 */
public class BulkCargo extends Cargo {
    /** The weight in tonnes of the BulkCargo as an integer. */
    private final int tonnage;

    /** The type of BulkCargo. */
    private final BulkCargoType type;

    /**
     * Constructor for the BulkCargo class.
     * @param id the identification number for this BulkCargo.
     * @param destination the destination for this BulkCargo.
     * @param tonnage the mass in tonnes for this BulkCargo.
     * @param type the type of this BulkCargo.
     */
    public BulkCargo(int id, String destination, int tonnage, BulkCargoType type) {
        super(id, destination);

        if (tonnage < 0) {
            throw new IllegalArgumentException();
        }

        this.tonnage = tonnage;
        this.type = type;
    }

    /**
     * Returns the tonnage for this BulkCargo.
     * @return tonnage the mass in tonnes for this BulkCargo.
     */
    public int getTonnage() {
        return tonnage;
    }

    /**
     * Returns the type for this BulkCargo.
     * @return type the type of this BulkCargo.
     */
    public BulkCargoType getType() {
        return type;
    }

    /**
     * Returns the human-readable string representation for this BulkCargo.
     * @return string representation for this BulkCargo.
     */
    @Override
    public String toString() {
        return super.toString() + " [" + this.getType() + " - " + this.getTonnage() + "]";
    }
}
