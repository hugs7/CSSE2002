package portsim.movement;

import portsim.cargo.Cargo;
import portsim.ship.Ship;
import portsim.util.BadEncodingException;
import portsim.util.NoSuchCargoException;
import portsim.util.NoSuchShipException;

import java.util.ArrayList;
import java.util.List;

/**
 * The movement of a ship coming into or out of the port.
 *
 * @ass1_partial
 */
public class ShipMovement extends Movement {

    /**
     * The ship entering of leaving the Port
     */
    private final Ship ship;

    /**
     * Creates a new ship movement with the given action time and direction
     * to be undertaken with the given ship.
     *
     * @param time      the time the movement should occur
     * @param direction the direction of the movement
     * @param ship      the ship which that is waiting to move
     * @throws IllegalArgumentException if time &lt; 0
     * @ass1
     */
    public ShipMovement(long time, MovementDirection direction, Ship ship)
        throws IllegalArgumentException {
        super(time, direction);
        this.ship = ship;
    }

    /**
     * Returns the ship undertaking the movement.
     *
     * @return movements ship
     * @ass1
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Returns the human-readable string representation of this ShipMovement.
     * <p>
     * The format of the string to return is
     * <pre>
     * DIRECTION ShipMovement to occur at time involving the ship name </pre>
     * Where:
     * <ul>
     *   <li>{@code DIRECTION} is the direction of the movement </li>
     *   <li>{@code time} is the time the movement is meant to occur </li>
     *   <li>{@code name} is the name of the ship that is being moved</li>
     * </ul>
     * For example:
     * <pre>
     * OUTBOUND ShipMovement to occur at 135 involving the ship Voyager </pre>
     *
     * @return string representation of this ShipMovement
     * @ass1
     */
    @Override
    public String toString() {
        return String.format("%s involving the ship %s",
            super.toString(),
            this.ship.getName());
    }

    /**
     * Returns the machine-readable string representation of this ship movement.
     * <p>
     * The format of the string to return is
     * <p>
     * {@link ShipMovement}:{@code time}:{@code direction}:{@code imoNumber}
     * <p>
     * Where:
     * <ul>
     *     <li>{@code time} is the time that the movement will be actioned</li>
     *     <li>{@code direction} is the direction of the movement</li>
     *     <li>{@code imoNumber} is the imoNumber of the ship that is moving</li>
     * </ul>
     * @return encoded string representation of this movement
     */
    public String encode() {
        return String.format("%s:%d",
                super.encode(),
                ship.getImoNumber());
    }

    /**
     * Creates a ship movement from a string encoding.
     * <p>
     * The format of the string should match the encoded representation of a ship movement, as
     * described by {@link ShipMovement#encode()}.
     * <p>
     * The encoded string is invalid if any of the following conditions are true:
     * <ul>
     *     <li>The number of colons (:) detected was more/fewer than expected.</li>
     *     <li>The time is not a long (i.e. cannot be parsed by {@link Long#parseLong(String)}.</li>
     *     <li>The time is less than zero (0).</li>
     *     <li>The movementDirection is nto one of the valid directions (See {@link
     *     MovementDirection}.</li>
     *     <li>The imoNumber is not a long (i.e. cannot be parsed by {@link Long#parseLong(String)}.
     *     </li>
     *     <li>The imoNumber is less than zero (0).</li>
     *     <li>There is no ship that exists with the specified imoNumber</li>
     * </ul>
     * @param string string containing the encoded {@link ShipMovement}
     * @return decoded {@link ShipMovement} instance
     * @throws BadEncodingException if the format of the given string is invalid according to the
     * rules above
     */
    public static ShipMovement fromString(String string) throws BadEncodingException {

        String[] tokens = string.split(":");

        if (!tokens[0].equals(ShipMovement.class.getSimpleName())) {
            throw new BadEncodingException();
        }

        long time;
        try {
            time = Long.parseLong(tokens[1]);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new BadEncodingException();
        }

        if (time < 0) {
            throw new BadEncodingException();
        }

        MovementDirection movementDirection;
        try {
            movementDirection = MovementDirection.valueOf(tokens[2]);
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            throw new BadEncodingException();
        }

        Ship ship;
        try {
            ship = Ship.getShipByImoNumber(Long.parseLong(tokens[3]));
        } catch (IndexOutOfBoundsException | NumberFormatException | NoSuchShipException e) {
            throw new BadEncodingException();
        }

        return new ShipMovement(time, movementDirection, ship);
    }
}
