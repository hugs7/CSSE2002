package portsim.port;

import portsim.ship.Ship;

/**
 * A platform where ships are moored for loading and unloading.
 */
public abstract class Quay {
    /** The ID of the Quay. */
    private final int id;

    /** A Boolean indicating if a ship is docked at the Quay or not. */
    private boolean shipDocked;

    /** The ship docked at the Quay. Null if no ship docked. */
    private Ship shipAtQuay;

    /**
     * Creates a new Quay with the given ID, with no ship docked at the quay.
     * @param id quay ID.
     */
    public Quay(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Invalid ID: " + id + " < 0");
        }

        this.id = id;
        this.shipDocked = false;
    }

    /**
     * Get the id of this quay.
     * @return quay id.
     */
    public int getId() {
        return id;
    }

    /**
     * Docks the given ship at the Quay so that the quay becomes occupied.
     * @param ship ship to dock to the quay
     */
    public void shipArrives(Ship ship) {
        shipDocked = true;
        shipAtQuay = ship;
    }

    /**
     * Removes the current ship docked at the quay. The current ship should be set to null.
     * @return the current ship or null if quay is empty.
     */
    public Ship shipDeparts() {
        shipDocked = false;
        Ship tempShip = shipAtQuay;
        shipAtQuay = null;

        return tempShip;
    }

    /**
     * Returns whether a ship is currently docked at this quay.
     * @return true if there is no ship docked else false.
     */
    public boolean isEmpty() {
        return !shipDocked;
    }

    /**
     * Returns the ship currently docked at the quay.
     * @return ship at quay or null if no ship is docked.
     */
    public Ship getShip() {
        return shipAtQuay;
    }

    /**
     * Returns the human-readable string representation of this quay.
     * @return string representation of this quay.
     */
    @Override
    public String toString() {
        if (this.getShip() == null) {
            return this.getClass().getSimpleName() + " " + this.getId() + " [Ship: None]";
        } else {
            return this.getClass().getSimpleName() + " " + this.getId() + " [Ship: "
                    + this.getShip().getImoNumber() + "]";
        }
    }
}
