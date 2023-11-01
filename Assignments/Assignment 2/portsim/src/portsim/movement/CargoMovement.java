package portsim.movement;

import portsim.cargo.Cargo;
import portsim.util.BadEncodingException;
import portsim.util.NoSuchCargoException;


import java.util.ArrayList;
import java.util.List;

/**
 * The movement of cargo coming into or out of the port.
 *
 * @ass1_partial
 */
public class CargoMovement extends Movement {

    /**
     * The cargo that will be involved in the movement
     */
    private final List<Cargo> cargo;

    /**
     * Creates a new cargo movement with the given action time and direction
     * to be undertaken with the given cargo.
     *
     * @param time      the time the movement should occur
     * @param direction the direction of the movement
     * @param cargo     the cargo to be moved
     * @throws IllegalArgumentException if time &lt; 0
     * @ass1
     */
    public CargoMovement(long time, MovementDirection direction,
                         List<Cargo> cargo) throws IllegalArgumentException {
        super(time, direction);
        this.cargo = cargo;
    }

    /**
     * Returns the cargo that will be moved.
     * <p>
     * Adding or removing elements from the returned list should not affect the original list.
     *
     * @return all cargo in the movement
     * @ass1
     */
    public List<Cargo> getCargo() {
        return new ArrayList<>(cargo);
    }

    /**
     * Returns the human-readable string representation of this {@link CargoMovement}.
     * <p>
     * The format of the string to return is
     * <pre>
     * DIRECTION {@link CargoMovement} to occur at time involving num piece(s) of cargo </pre>
     * Where:
     * <ul>
     *   <li>{@code DIRECTION} is the direction of the movement </li>
     *   <li>{@code time} is the time the movement is meant to occur </li>
     *   <li>{@code num} is the number of cargo pieces that are being moved</li>
     * </ul>
     * <p>
     * For example: <pre>
     * OUTBOUND CargoMovement to occur at 135 involving 5 piece(s) of cargo </pre>
     *
     * @return string representation of this {@link CargoMovement}
     * @ass1
     */
    @Override
    public String toString() {
        return String.format("%s involving %d piece(s) of cargo",
            super.toString(),
            this.cargo.size());
    }

    /**
     * Returns the machine-readable string representation of this movement.
     * <p>
     * The format of the string to return is
     * <p>
     * {@link CargoMovement}:{@code time}:{@code direction}:{@code numCargo}:{@code ID1,ID2,...}
     * <p>
     * Where:
     * <ul>
     *     <li>{@code time} is the time that the movement will be actioned</li>
     *     <li>{@code direction} is the direction of the movement</li>
     *     <li>{@code numCargo} is the number of the cargo in the movement</li>
     *     <li>{@code ID1,ID2,...} are the IDs of the cargo in the movement separated by a
     *     comma ','. There should be no trailing comma after the last ID.</li>
     * </ul>
     *
     * @return encoded string representation of this movement
     */
    @Override
    public String encode() {
        if (cargo.size() == 0) {
            return String.format("%s:%d",
                    super.encode(),
                    0);
        } else {
            StringBuilder cargoIds = new StringBuilder();
            for (Cargo cargo : this.cargo) {
                cargoIds.append(cargo.getId()).append(",");
            }
            cargoIds.deleteCharAt(cargoIds.length() - 1);
            return String.format("%s:%d:%s",
                    super.encode(),
                    cargo.size(),
                    cargoIds);
        }
    }

    /**
     * Creates a cargo movement from a string encoding.
     * <p>
     * The format of the string should match the encoded representation of a cargo movement, as
     * described in {@link CargoMovement#encode()}.
     * <p>
     * The encoded string is invalid if any of the following conditions are true:
     * <ul>
     *     <li>The number of colons (:) detected was more/fewer than expected.</li>
     *     <li>The given string is not a {@link CargoMovement} encoding</li>
     *     <li>The time is not a long (i.e. cannot be parsed by {@link Long#parseLong(String)}.</li>
     *     <li>The time is less than zero (0).</li>
     *     <li>The {@code movementDirection} is not one of the valid directions (See
     *     {@link MovementDirection}</li>
     *     <li>The number of ids is less than one (1).</li>
     *     <li>An id is not an int (i.e. cannot be parsed by {@link Integer#parseInt(String)}.</li>
     *     <li>An id is less than zero (0).</li>
     *     <li>There is no cargo that exists with a specified id.</li>
     *     <li>The number of ids does not match the number specified.</li>
     * </ul>
     * @param string string containing the encoded {@link CargoMovement}
     * @return decoded {@link CargoMovement} instance
     * @throws BadEncodingException if the format of the given string is invalid according to the
     * rules above
     */
    public static CargoMovement fromString(String string) throws BadEncodingException {
        String[] tokens = string.split(":");

        if (!tokens[0].equals(CargoMovement.class.getSimpleName())) {
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

        int numCargo;
        try {
            numCargo = Integer.parseInt(tokens[3]);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new BadEncodingException();
        }

        List<Cargo> cargoList = new ArrayList<>();

        Cargo cargo;
        for (int i = 0; i < numCargo; i++) {
            try {
                cargo = Cargo.getCargoById(Integer.parseInt(tokens[4].split(",")[i]));
                cargoList.add(cargo);
            } catch (IndexOutOfBoundsException | NumberFormatException | NoSuchCargoException e) {
                throw new BadEncodingException();
            }
        }

        return new CargoMovement(time, movementDirection, cargoList);
    }
}
