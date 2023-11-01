package portsim.ship;

import portsim.cargo.BulkCargo;
import portsim.cargo.BulkCargoType;
import portsim.cargo.Cargo;
import portsim.port.BulkQuay;
import portsim.port.Quay;
import portsim.util.NoSuchCargoException;

import java.util.List;
import java.util.Objects;

/**
 * A ship capable of carrying BulkCargo.
 */
public class BulkCarrier extends Ship {
    /** The capacity in tonnes of the BulkCarrier. */
    private final int capacity;

    /** A Boolean indicating if cargo is on board or not. */
    private boolean cargoOnBoard;

    /** The BulkCargo on board. Null if no cargo. */
    private BulkCargo cargo;

    /** Weight of BulkCargo on board in tonnes. */
    private int cargoOnBoardTonnage;

    /**
     * Creates a new bulk carrier with the given IMO number, name, origin port, nautical flag and
     * cargo capacity.
     * @param imoNumber unique identifier.
     * @param name name of the BulkCarrier.
     * @param originFlag port of origin.
     * @param flag the nautical flag this ship is flying.
     * @param capacity the tonnage capacity of this ship.
     */
    public BulkCarrier(long imoNumber, String name, String originFlag, NauticalFlag flag,
                       int capacity) {
        super(imoNumber, name, originFlag, flag);

        this.capacity = capacity;
        cargoOnBoard = false;
        cargoOnBoardTonnage = 0;
    }

    /**
     * Check if this ship can dock with the specified quay.
     * @param quay quay to be checked.
     * @return true if the Quay satisfies the conditions else false.
     */
    @Override
    public boolean canDock(Quay quay) {
        return quay.getClass().equals(BulkQuay.class)
                && ((BulkQuay) quay).getMaxTonnage() >= this.cargoOnBoardTonnage;
    }

    /**
     * Checks whether the specified cargo can be loaded onto the ship.
     * @param cargo cargo to be loaded.
     * @return true if the Cargo satisfies the conditions else false.
     */
    @Override
    public boolean canLoad(Cargo cargo) {
        return !cargoOnBoard
                && cargo.getClass().equals(BulkCargo.class)
                && ((BulkCargo) cargo).getTonnage() <= capacity - cargoOnBoardTonnage
                && Objects.equals(cargo.getDestination(), this.getOriginFlag());
    }

    /**
     * Loads the specified cargo onto the ship.
     * @param cargo cargo to be loaded.
     */
    @Override
    public void loadCargo(Cargo cargo) {
        this.cargo = (BulkCargo) cargo;
        cargoOnBoardTonnage += ((BulkCargo) cargo).getTonnage();
        cargoOnBoard = true;
    }

    /**
     * Unloads the cargo from the ship.
     * The ship's cargo should be set to null at the end of the operation.
     * @return the ship's cargo before it was unloaded.
     * @throws NoSuchCargoException if the ship has already been unloaded.
     */
    public BulkCargo unloadCargo() throws NoSuchCargoException {
        if (cargoOnBoard) {
            cargoOnBoard = false;
            BulkCargo tempCargo = cargo;
            cargo = null;
            return tempCargo;
        } else {
            throw new NoSuchCargoException();
        }
    }

    /**
     * Returns the cargo onboard this vessel.
     * @return bulk cargo on the vessel.
     */
    public BulkCargo getCargo() {
        return cargo;
    }

    /**
     * Returns the human-readable string representation of this BulkCarrier.
     * @return string representation of this BulkCarrier.
     */
    @Override
    public String toString() {
        return super.toString() + " carrying " + cargo.getType();
    }
}
