package portsim.movement;

import portsim.ship.Ship;

/**
 * The ShipMovement class handles the movement of ships in and out a port.
 */
public class ShipMovement extends Movement {
    /** The ship involved in the movement. */
    private final Ship ship;

    /**
     * Creates a new ship movement with the given action time and direction to be undertaken with
     * the given ship.
     * @param time the time the movement should occur.
     * @param direction the direction of the movement.
     * @param ship the ship which that is waiting to move.
     */
    public ShipMovement(long time, MovementDirection direction, Ship ship) {
        super(time, direction);

        this.ship = ship;
    }

    /**
     * Returns the ship undertaking the movement.
     * @return movements ship.
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Returns the human-readable string representation of this ShipMovement.
     * @return string representation of this ShipMovement.
     */
    @Override
    public String toString() {
        return super.toString() + " involving the " + this.getClass().getSimpleName() + " "
                + this.getShip();
    }
}
