package portsim.ship;


import portsim.cargo.Cargo;
import portsim.cargo.Container;
import portsim.port.ContainerQuay;
import portsim.port.Quay;
import portsim.util.NoSuchCargoException;

import java.util.ArrayList;
import java.util.List;

/**
 * A ship capable of carrying shipping containers.
 */
public class ContainerShip extends Ship {
    /** The capacity in tonnes of the ContainerShip. */
    private final int capacity;

    /** An ArrayList of the Containers on board the ContainerShip. */
    private final ArrayList<Container> containers;

    /**
     * Creates a new Container Quay with the given ID and maximum number of containers.
     * @param imoNumber unique identifier.
     * @param name name of the ship.
     * @param originFlag port of origin.
     * @param flag the nautical flag this ship is flying.
     * @param capacity the container capacity of this ship.
     */
    public ContainerShip(long imoNumber, String name, String originFlag, NauticalFlag flag,
                         int capacity) {
        super(imoNumber, name, originFlag, flag);

        this.capacity = capacity;
        this.containers = new ArrayList<>();
    }


    /**
     * Checks if this ship can dock with the specified quay.
     * @param quay quay to be checked.
     * @return true if the Quay satisfies the conditions else false.
     */
    @Override
    public boolean canDock(Quay quay) {
        return quay instanceof ContainerQuay
                && ((ContainerQuay) quay).getMaxContainers() >= containers.size();
    }

    /**
     * Checks whether the specified cargo can be loaded onto the ship.
     * @param cargo cargo to be loaded.
     * @return true if the Cargo satisfies the conditions else false.
     */
    @Override
    public boolean canLoad(Cargo cargo) {
        return cargo instanceof Container
                && containers.size() <= capacity
                && cargo.getDestination().equals(this.getOriginFlag());
    }

    /**
     * Loads the specified cargo onto the ship.
     * @requires Cargo.canLoad to be true.
     * @param cargo cargo to be loaded.
     */
    @Override
    public void loadCargo(Cargo cargo) {
        this.containers.add((Container) cargo);
    }

    /**
     * Unloads the cargo from the ship.
     * The ship's cargo should be set to an empty list.
     * @return the ship's cargo before it was unloaded.
     * @throws NoSuchCargoException if the ship has already been unloaded.
     */
    public List<Container> unloadCargo() throws NoSuchCargoException {
        if (containers.size() != 0) {
            List<Container> tempContainers = containers;
            containers.clear();
            return tempContainers;
        } else {
            throw new NoSuchCargoException();
        }
    }

    /**
     * Returns the current cargo onboard this vessel.
     * Adding or removing elements from the returned list should not affect the original list.
     * @return containers on the vessel.
     */
    public List<Container> getCargo() {
        return new ArrayList<>(containers);
    }

    /**
     * Returns the human-readable string representation of this ContainerShip.
     * @return string representation of this ContainerShip./
     */
    @Override
    public String toString() {
        return super.toString() + " carrying " + this.getCargo().size() + " containers";
    }
}
