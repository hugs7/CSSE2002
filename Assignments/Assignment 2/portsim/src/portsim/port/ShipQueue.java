package portsim.port;

import portsim.ship.ContainerShip;
import portsim.ship.NauticalFlag;
import portsim.ship.Ship;
import portsim.util.BadEncodingException;
import portsim.util.NoSuchShipException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Queue of ships waiting to enter a {@link Quay} at the port. Ships are chosen based on their
 * priority.
 */
public class ShipQueue {
    /**
     * List of ships waiting to come into port.
     */
    private final List<Ship> shipQueue;

    /**
     * Constructs a new {@link ShipQueue} with an initially empty queue of ships.
     */
    public ShipQueue() {
        shipQueue = new ArrayList<>();
    }

    /**
     * Gets the next ship to enter port and removes it from the queue.
     * <p>
     * The same rules apply in {@link ShipQueue#peek()} should be used for determining which ship
     * to remove and return.
     *
     * @return next ship to dock
     */
    public Ship poll() {
        Ship dockShip = peek();
        shipQueue.remove(dockShip);
        return dockShip;
    }

    /**
     * Returns the next ship waiting to enter the port. The queue should not change.
     * <p>
     * The rules for determining which ship in the queue should be returned next are as follows:
     * <ol>
     *     <li>If a ship is carrying dangerous cargo, it should be returned. If more than one
     *     ship is carrying dangerous cargo return the one added to the queue first.</li>
     *     <li>If a ship requires medical assistance, it should be returned. If more than one
     *     ship requires medical assistance, return the one added to the queue first.</li>
     *     <li>If a ship is ready to be docked, return the one added to the queue first.</li>
     *     <li>If there is a container ship in the queue, return the one added to the queue
     *     first.</li>
     *     <li>If this point is reached and no ship has been returned, return the ship that
     *     was added to the queue first.</li>
     *     <li>If there were no ships in the queue, return {@code null}.</li>
     * </ol>
     *
     * @return next ship in the queue
     */
    public Ship peek() {
        List<NauticalFlag> flagCheck = new ArrayList<>(Arrays.asList(NauticalFlag.BRAVO,
                NauticalFlag.WHISKEY, NauticalFlag.HOTEL));
        for (NauticalFlag flag : flagCheck) {
            for (Ship ship : shipQueue) {
                if (ship.getFlag() == flag) {
                    return ship;
                }
            }
        }

        // Container Ship
        for (Ship ship : shipQueue) {
            if (ship instanceof ContainerShip) {
                return ship;
            }
        }

        try {
            return shipQueue.get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Adds the specified ship to the queue.
     *
     * @param ship to be added to queue
     */
    public void add(Ship ship) {
        shipQueue.add(ship);
    }

    /**
     * Returns a list containing all the ships currently stored in this {@link ShipQueue}.
     * <p>
     * The order of the ships in the returned list should be the order in which the ships were
     * added to the queue.
     * <p>
     * Adding or removing elements from the returned list should not affect the original list.
     *
     * @return ships in queue
     */
    public List<Ship> getShipQueue() {
        return new ArrayList<>(shipQueue);
    }

    /**
     * Returns true if and only if this ship queue is equal to the other given ship queue.
     * <p>
     * For two ship queue to be equal, they must have the same ships in the queue.
     *
     * @param o other object to check equality
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        return o instanceof ShipQueue && shipQueue.equals(((ShipQueue) o).shipQueue);
    }

    /**
     * Returns the hash code of this ship queue.
     * <p>
     * Two ship queue's that are equal according to {@link ShipQueue#equals(Object)} method
     * should have the same hash code.
     *
     * @return hash code of this ship queue
     */
    @Override
    public int hashCode() {
        int hash = 0;

        for (Ship ship : shipQueue) {
            hash += ship.hashCode();
        }

        return hash;
    }

    /**
     * Returns the machine-readable string representation of this {@link ShipQueue}.
     * <p>
     * The format of the string to return is
     * <pre>ShipQueue:numShipsInQueue:shipID,shipID,...</pre>
     * Where
     * <ul>
     *     <li>numShipsInQueue is the total number of ships in the ship queue in the port</li>
     *     <li>If present (numShipsInQueue > 0): shipID in each ship's ID in the aforementioned
     *     queue</li>
     * </ul>
     *
     * @return encoded string representation of this {@link ShipQueue}
     */
    public String encode() {
        if (shipQueue.size() == 0) {
            return String.format("%s:%d:",
                    getClass().getSimpleName(),
                    0);
        } else {
            StringBuilder ships = new StringBuilder();
            for (Ship ship : shipQueue) {
                ships.append(ship.getImoNumber()).append(",");
            }
            ships.deleteCharAt(ships.length() - 1);
            return String.format("%s:%d:%s",
                    getClass().getSimpleName(),
                    shipQueue.size(),
                    ships);
        }
    }

    /**
     * Creates a ship queue from a string encoding.
     * <p>
     * The format of the string should match the encoded representation of a ship queue, as
     * described in {@link ShipQueue#encode()}.
     * <p>
     * The encoded string is invalid if any of the following conditions are true:
     * <ul>
     *     <li>The number of colons (:) detected was more/fewer than expected.</li>
     *     <li>The string does not start with the literal string "ShipQueue"</li>
     *     <li>The number of ships in the shipQueue is not an integer (i.e. cannot be parsed by
     *     {@link Integer#parseInt(String)}.</li>
     *     <li>The number of ships in the shipQueue does not match the number specified.</li>
     *     <li>The imoNumber of the ships in the shipQueue are not valid longs. (i.e. cannot be
     *     parsed by {@link Long#parseLong(String)}.</li>
     *     <li>Any imoNumber read does not correspond to a valid ship in the simulation</li>
     * </ul>
     *
     * @param string string containing the encoded {@link ShipQueue}
     * @return decoded ship queue instance
     * @throws BadEncodingException if the format of the given string is invalid according to the
     * rules above
     */
    public static ShipQueue fromString(String string) throws BadEncodingException {
        String[] tokens = string.split(":");

        // ShipQueue Equals
        if (!tokens[0].equals("ShipQueue")) {
            throw new BadEncodingException();
        }

        // NumShips
        int numShips;
        try {
            numShips = Integer.parseInt(tokens[1]);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new BadEncodingException();
        }

        ShipQueue shipQueue = new ShipQueue();

        if (numShips == 0) {
            return shipQueue;
        }

        String[] ships;
        int checkShips;

        try {
            ships = tokens[2].split(",");
            checkShips = ships.length;
        } catch (IndexOutOfBoundsException e) {
            throw new BadEncodingException();
        }

        if (!(checkShips == numShips && tokens.length == 3)) {
            throw new BadEncodingException();
        }

        Ship ship;
        for (int i = 0; i < numShips; i++) {
            try {
                ship = Ship.getShipByImoNumber(Long.parseLong(ships[i]));
            } catch (IndexOutOfBoundsException | NumberFormatException | NoSuchShipException e) {
                throw new BadEncodingException();
            }
            shipQueue.add(ship);
        }

        return shipQueue;
    }
}