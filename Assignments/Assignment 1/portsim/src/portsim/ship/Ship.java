package portsim.ship;

import portsim.cargo.Cargo;
import portsim.port.Quay;

/**
 * Represents a ship whose movement is managed by the portsim program.
 */
public abstract class Ship {
    /** The imoNumber of a ship. */
    private final long imoNumber;

    /** The name of a ship. */
    private final String name;

    /** The origin of a ship. */
    private final String originFlag;

    /** The nautical flag the ship is currently flying. */
    private final NauticalFlag flag;

    /**
     * Creates a new ship with the given IMO number, name, origin port flag and nautical flag.
     * @param imoNumber unique identifier.
     * @param name name of the ship.
     * @param originFlag port of origin.
     * @param flag the nautical flag this ship is flying.
     */
    public Ship(long imoNumber, String name, String originFlag, NauticalFlag flag) {
        if (imoNumber < 0 || String.valueOf(imoNumber).length() != 7) {
            throw new IllegalArgumentException("Invalid imoNumber: " + imoNumber + " < 0");
        }

        this.imoNumber = imoNumber;
        this.name = name;
        this.originFlag = originFlag;
        this.flag = flag;
    }

    /**
     * Check if this ship can dock with the specified quay according to the conditions determined by
     * the ships type.
     * @param quay quay to be checked.
     * @return true if the Quay satisfies the conditions else false.
     */
    public abstract boolean canDock(Quay quay);

    /**
     * Checks if the specified cargo can be loaded onto the ship according to the conditions
     * determined by the ships
     * type.
     * @param cargo cargo to be loaded.
     * @return true if the Cargo satisfies the conditions else false.
     */
    public abstract boolean canLoad(Cargo cargo);

    /**
     * Loads the specified cargo onto the ship.
     * @param cargo cargo to be loaded.
     */
    public abstract void loadCargo(Cargo cargo);

    /**
     * Returns the ship's name.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the ship's IMO number.
     * @return imoNumber.
     */
    public long getImoNumber() {
        return imoNumber;
    }

    /**
     * Returns this ship's flag denoting its origin.
     * @return originFlag.
     */
    public String getOriginFlag() {
        return originFlag;
    }

    /**
     * Returns the nautical flag the ship is flying.
     * @return flag.
     */
    public NauticalFlag getFlag() {
        return flag;
    }

    /**
     * Returns the human-readable string representation of this Ship.
     * @return string representation of this Ship.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + this.getName() + " from "
                + this.getOriginFlag() + " [" + this.getFlag() + "]";
    }
}
