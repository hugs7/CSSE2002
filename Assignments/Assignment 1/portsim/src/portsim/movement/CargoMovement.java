package portsim.movement;

import portsim.cargo.Cargo;

import java.util.List;

/**
 * The CargoMovement class handles the movement of cargo into and out of a port.
 */
public class CargoMovement extends Movement {
    /** Cargo coming in and out of the port as a list */
    private final List<Cargo> cargo;

    /**
     * Creates a new cargo movement with the given action time and direction to be undertaken with
     * the given cargo.
     * @param time the time the movement should occur.
     * @param direction the direction of the movement.
     * @param cargo the cargo to be moved.
     */
    public CargoMovement(long time, MovementDirection direction, List<Cargo> cargo) {
        super(time, direction);

        this.cargo = cargo;
    }

    /**
     * Returns the cargo that will be moved.
     * Adding or removing elements from the returned list should not affect the original list.
     * @return all cargo in the movement.
     */
    public List<Cargo> getCargo() {
        return cargo;
    }

    /**
     * Returns the human-readable string representation of this CargoMovement.
     * @return string representation of this CargoMovement.
     */
    @Override
    public String toString() {
        return super.toString() + " involving " + this.getCargo().size() + " piece(s) of cargo";
    }
}
