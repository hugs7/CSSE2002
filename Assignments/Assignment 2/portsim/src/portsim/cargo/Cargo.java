package portsim.cargo;

import portsim.util.BadEncodingException;
import portsim.util.Encodable;
import portsim.util.NoSuchCargoException;

import java.util.HashMap;
import java.util.Map;

/**
 * Denotes a cargo whose function is to be transported via a Ship or land
 * transport.
 * <p>
 * Cargo is kept track of via its ID.
 *
 * @ass1_partial
 */
public abstract class Cargo {
    /**
     * The ID of the cargo instance
     */
    private final int id;

    /**
     * Destination for this cargo
     */
    private final String destination;

    /**
     * Database of all cargo currently active in the simulation
     */
    private static Map<Integer, Cargo> cargoRegistry = new HashMap<>();

    /**
     * Creates a new Cargo with the given ID and destination port.
     * <p>
     * When a new piece of cargo is created, it should be added to the cargo registry.
     * @param id          cargo ID
     * @param destination destination port
     * @throws IllegalArgumentException if a cargo already exists with the
     *                                  given ID or ID &lt; 0
     * @ass1_partial
     */
    public Cargo(int id, String destination) throws IllegalArgumentException {
        if (cargoRegistry.containsKey(id) || id < 0) {
            throw new IllegalArgumentException("Cargo ID must be greater than"
                + " or equal to 0: " + id);
        }
        this.id = id;
        this.destination = destination;

        cargoRegistry.put(this.id, this);
    }

    /**
     * Retrieve the ID of this piece of cargo.
     *
     * @return the cargo's ID
     * @ass1
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieve the destination of this piece of cargo.
     *
     * @return the cargo's destination
     * @ass1
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Returns the global registry of all pieces of cargo, as a mapping from cargo IDs to
     * {@link Cargo} instances.
     * <p>
     * Adding or removing elements from the returned map should not affect the original map.
     *
     * @return cargo registry
     */
    public static Map<Integer, Cargo> getCargoRegistry() {
        return new HashMap<>(cargoRegistry);
    }

    /**
     * Checks if a cargo exists in the simulation using its ID.
     *
     * @param id unique key to identify cargo
     * @return true if there is a cargo stored in the registry with key id; false otherwise.
     */
    public static boolean cargoExists(int id) {
        return cargoRegistry.containsKey(id);
    }

    /**
     * Returns the cargo specified by the given ID.
     *
     * @param id unique key to identify cargo
     * @return cargo specified by the id
     * @throws NoSuchCargoException if the cargo does not exist in the registry
     */
    public static Cargo getCargoById(int id) throws NoSuchCargoException {
        return cargoRegistry.get(id);
    }

    /**
     * Returns true if and only if this cargo is equal to the other given cargo.
     * <p>
     * For two cargo to be equal, they must have the same ID and destination.
     *
     * @param o other object to check equality
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof Cargo
                && id == ((Cargo) o).id
                && destination.equals(((Cargo) o).destination);
    }

    /**
     * Returns the hash code of this cargo.
     * <p>
     * Two {@link Cargo} are equal according to {@link Cargo#equals(Object)} method should have
     * the same hash code.
     *
     * @return hash code of this cargo
     */
    @Override
    public int hashCode() {
        return (int) Math.round(Math.pow(2, this.getId())
                + Math.pow(3, this.getDestination().hashCode()));
    }

    /**
     * Returns the human-readable string representation of this cargo.
     * <p>
     * The format of the string to return is
     * <pre>CargoClass id to destination</pre>
     * Where:
     * <ul>
     *   <li>{@code CargoClass} is the cargo class name</li>
     *   <li>{@code id} is the id of this cargo </li>
     *   <li>{@code destination} is the destination of the cargo </li>
     * </ul>
     * <p>
     * For example: <pre>Container 55 to New Zealand</pre>
     *
     * @return string representation of this Cargo
     * @ass1
     */
    @Override
    public String toString() {
        return String.format("%s %d to %s",
            this.getClass().getSimpleName(),
            this.id,
            this.destination);
    }

    /**
     * Returns the machine-readable string representation of this Cargo.
     * <p>
     * The format of the string to return is
     * <p>
     * {@code CargoClass}:{@code id}:{@code destination}
     * <p>
     * Where
     * <ul>
     *     <li>{@code CargoClass} is the {@link Cargo} class name</li>
     *     <li>{@code id} is the id of this cargo</li>
     *     <li>{@code destination} is the destination of this cargo</li>
     * </ul>
     * <p>
     * For example:
     * <p>
     * {@link Container}:{@code 3}:Australia
     * <p>
     * OR
     * <p>
     * {@link BulkCargo}:{@code 2}:France
     *
     * @return encoded string representation of this Cargo.
     * @see Encodable#encode()
     */
    public String encode() {
        return String.format("%s:%d:%s",
                this.getClass().getSimpleName(),
                this.getId(),
                this.getDestination());
    }

    /**
     * Reads a piece of cargo from its encoded representation in the given string.
     * <p>
     * The format of the given string should match the encoded representation of a Cargo, as
     * described in {@link Cargo#encode()} (and subclasses).
     * <p>
     * The encoded string is invalid if any of the following conditions are true:
     * <ul>
     *     <li>The number of colons (:) detected was more/fewer than expected.</li>
     *     <li>The cargo id is not an integer (i.e. cannot be parsed by
     *     {@link Integer#parseInt(String)}).</li>
     *     <li>The cargo id is less than one (1).</li>
     *     <li>A piece of cargo with the specified ID already exists.</li>
     *     <li>The cargo type specified is not one of {@link BulkCargoType} or {@link ContainerType}
     *     </li>
     *     <li>If the cargo type is a {@link BulkCargo}:</li>
     *     <ol>
     *         <li>The cargo weight in tonnes is not an integer (i.e. cannot be parsed by
     *         {@link Integer#parseInt(String)}).</li>
     *         <li>The cargo weight in tonnes is less than one ({@code 1}).</li>
     *     </ol>
     * </ul>
     *
     * @param string string containing the encoded cargo
     * @return decoded cargo instance
     * @throws BadEncodingException if the format of the given string is invalid according to the
     * rules above.
     */
    public static Cargo fromString(String string) throws BadEncodingException {
        String[] token = string.split(":");

        if (token[0].equals("BulkCargo")) {
            try {
                return new BulkCargo(Integer.parseInt(token[1]), token[2],
                        Integer.parseInt(token[4]), BulkCargoType.valueOf(token[3]));
            } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                throw new BadEncodingException();
            }
        } else if (token[0].equals("Container")) {
            try {
                return new Container(Integer.parseInt(token[1]), token[2],
                        ContainerType.valueOf(token[3]));
            } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                throw new BadEncodingException();
            }
        } else {
            throw new BadEncodingException();
        }
    }

    /**
     * Resets the global cargo registry.
     * This utility method is for the testing suite.
     *
     * @given
     */
    public static void resetCargoRegistry() {
        Cargo.cargoRegistry = new HashMap<>();
    }
}
