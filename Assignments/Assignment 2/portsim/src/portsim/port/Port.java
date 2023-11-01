package portsim.port;

import jdk.dynalink.SecureLookupSupplier;
import portsim.cargo.Cargo;
import portsim.cargo.Container;
import portsim.evaluators.*;
import portsim.movement.CargoMovement;
import portsim.movement.Movement;
import portsim.movement.MovementDirection;
import portsim.movement.ShipMovement;
import portsim.ship.BulkCarrier;
import portsim.ship.ContainerShip;
import portsim.ship.Ship;
import portsim.util.BadEncodingException;
import portsim.util.NoSuchCargoException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

/**
 * A place where ships can come and dock with Quays to load / unload their
 * cargo.
 * <p>
 * Ships can enter a port through its queue. {@link Cargo} is stored within the port at warehouses.
 *
 * @ass1_partial
 */
public class Port {
    /**
     * The name of this port used for identification
     */
    private final String name;

    /**
     * The quays associated with this port
     */
    private final List<Quay> quays;

    /**
     * The cargo currently stored at the port at warehouses. Cargo unloaded from trucks / ships
     */
    private final List<Cargo> storedCargo;

    /**
     * List of Statistics Evaluators at the port
     * */
    private final List<StatisticsEvaluator> statEvals;

    /**
     * Time since simulation started.
     */
    private long time;

    /**
     * Queue of ships waiting to come into port.
     */
    private final ShipQueue shipQueue;

    /**
     * Queue of movements waiting to be processed.
     */
    private final PriorityQueue<Movement> movementQueue;


    /**
     * Creates a new port with the given name.
     * <p>
     * The time since the simulation was started should be initialised as {@code 0}.
     * <p>
     * The list of quays in the port, stored cargo (warehouses) and statistics evaluators should be
     * initialised as empty lists.
     * <p>
     * An empty {@link ShipQueue} should be initialised, and a PriorityQueue should be initialised
     * to store movements ordered by the time of the movement (see {@link Movement#getTime()}).
     *
     * @param name name of the port
     * @ass1_partial
     */
    public Port(String name) {
        this.name = name;
        this.quays = new ArrayList<Quay>();
        this.storedCargo = new ArrayList<Cargo>();

        this.statEvals = new ArrayList<StatisticsEvaluator>();
        this.time = 0L;
        this.shipQueue = new ShipQueue();
        this.movementQueue = new PriorityQueue<Movement>(Comparator.comparing(Movement::getTime));
    }

    /**
     * Creates a new port with the given name, time elapsed, ship queue, quays and stored cargo.
     * <p>
     * The list of statistics evaluators should be initialised as an empty list.
     * <p>
     * An empty {@link ShipQueue} should be initialised, and a {@link PriorityQueue} should be
     * initialised to store movements ordered by the time of the movement (see
     * {@link Movement#getTime()})
     *
     * @param name name of the port
     * @param time number of minutes since simulation started
     * @param shipQueue ships waiting to enter the port
     * @param quays the port's quays
     * @param storedCargo the cargo stored at the port
     * @throws IllegalArgumentException if time &lt; {@code 0}
     */
    public Port(String name, long time, ShipQueue shipQueue, List<Quay> quays,
                List<Cargo> storedCargo) throws IllegalArgumentException {
        this.name = name;
        if (time < 0) {
            throw new IllegalArgumentException();
        }
        this.time = time;
        this.shipQueue = shipQueue;
        this.quays = quays;
        this.storedCargo = storedCargo;

        this.statEvals = new ArrayList<StatisticsEvaluator>();
        this.movementQueue = new PriorityQueue<Movement>(Comparator.comparing(Movement::getTime));
    }

    /**
     * Adds a movement to the {@link PriorityQueue} of movements.
     * <p>
     * If the given movement's action time is less than the current number of minutes elapsed
     * than an {@link IllegalArgumentException}
     *
     * @param movement movement to add
     * @throws IllegalArgumentException if movement's action time is less than current time
     */
    public void addMovement(Movement movement) throws IllegalArgumentException {
        if (movement.getTime() < time) {
            throw new IllegalArgumentException("Given movement's action time is less than the "
                    + "current number of minutes elapsed");
        } else {
            if (movement instanceof ShipMovement) {
                movementQueue.add(movement);
            } else if (movement instanceof CargoMovement) {
                movementQueue.add(movement);
            }
        }
    }

    /**
     * Processes a movement.
     * <p>
     * The action taken depends on the type of movement to be processed.
     * <p>
     * If the movement is a {@link ShipMovement}:
     * <ul>
     *     <li>If the movement direction is {@link MovementDirection#INBOUND} then the ship
     *     should be added to the ship queue.</li>
     *     <li>If the movement direction is {@link MovementDirection#OUTBOUND} then any cargo
     *     stored in the port whose destination is the ship's origin port should be added to the
     *     ship according to {@link Ship#canLoad(Cargo)}. Next the ship should be removed from the
     *     quay it is currently docked in (if any).</li>
     * </ul>
     * <p>
     * If the movement is a {@link CargoMovement}:
     * <ul>
     *     <li>If the movement direction is {@link MovementDirection#INBOUND} then all the cargo
     *     that is being moved should be added to the port's stored cargo.
     *     <li>If the movement direction is {@link MovementDirection#OUTBOUND} then all cargo
     *     with the given IDs should be removed from the port's stored cargo.
     * </ul>
     * <p>
     * Finally, the movement should be forwarded onto each statistics evaluator stored by the port
     * by calling {@link StatisticsEvaluator#onProcessMovement(Movement)}
     * @param movement movement to execute
     */
    public void processMovement(Movement movement) {
        if (movement instanceof ShipMovement) {
            Ship ship = ((ShipMovement) movement).getShip();
            if (movement.getDirection().equals(MovementDirection.INBOUND)) {
                shipQueue.add(ship);
            } else if (movement.getDirection().equals(MovementDirection.OUTBOUND)) {
                for (Cargo cargo : getCargo()) {
                    if (cargo.getDestination().equals(ship.getOriginFlag())
                            && ship.canLoad(cargo)) {
                        ((ShipMovement) movement).getShip().loadCargo(cargo);
                    }
                }

                // todo undock ship from quay.
                // How do I know which quay the ship is at?
            }
        } else if (movement instanceof CargoMovement) {
            if (movement.getDirection().equals(MovementDirection.INBOUND)) {
                storedCargo.addAll(((CargoMovement) movement).getCargo());
            } else if (movement.getDirection().equals(MovementDirection.OUTBOUND)) {
                for (Cargo cargo : ((CargoMovement) movement).getCargo()) {
                    storedCargo.remove(cargo);
                }
            }
        }

        // Tell statistics evaluators about movement
        for (StatisticsEvaluator eval : getEvaluators()) {
            eval.onProcessMovement(movement);
        }
    }

    /**
     * Adds the given statisticsEvaluator to the port's list of evaluators.
     * <p>
     * If the port already has an evaluator of that type, no action should be taken.
     *
     * @param eval statistics evaluator to add to the port
     */
    public void addStatisticsEvaluator(StatisticsEvaluator eval) {
        if (!statEvals.contains(eval)) {
            statEvals.add(eval);
        }
    }

    /**
     * Returns the name of this port.
     *
     * @return port's name
     * @ass1
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the time since simulator started.
     *
     * @return time in minutes
     */
    public long getTime() {
        return time;
    }

    /**
     * Returns a list of all quays associated with this port.
     * <p>
     * Adding or removing elements from the returned list should not affect the original list.
     * <p>
     * The order in which quays appear in this list should be the same as the order in which they
     * were added by calling {@link Port#addQuay(Quay)}.
     *
     * @return all quays
     * @ass1
     */
    public List<Quay> getQuays() {
        return new ArrayList<>(this.quays);
    }

    /**
     * Returns the cargo stored in warehouses at this port.
     * <p>
     * Adding or removing elements from the returned list should not affect the original list.
     *
     * @return port cargo
     * @ass1
     */
    public List<Cargo> getCargo() {
        return new ArrayList<>(this.storedCargo);
    }

    /**
     * Returns the queue of ship waiting to be docked at this port.
     *
     * @return port's queue of ships
     */
    public ShipQueue getShipQueue() {
        return shipQueue;
    }

    /**
     * Returns the queue of movements waiting to be processed.
     *
     * @return movements queue
     */
    public PriorityQueue<Movement> getMovements() {
        return movementQueue;
    }

    /**
     * Returns the list of evaluators at the port.
     *
     * Adding or removing elements from the returned list should not affect the original list.
     * @return the port's evaluators
     */
    public List<StatisticsEvaluator> getEvaluators() {
        return new ArrayList<>(statEvals);
    }

    /**
     * Adds a quay to the ports control.
     *
     * @param quay the quay to add
     * @ass1
     */
    public void addQuay(Quay quay) {
        this.quays.add(quay);
    }

    /**
     * Advances the simulation by one minute.
     * <p>
     * One each call to {@code elapseOneMinute()}, the following actions should be completed
     * by the port in order:
     * <ol>
     *     <li>Advance the simulation time by 1</li>
     *     <li>If the time is a multiple of 10, attempt to bring a ship from the ship queue to any
     *     empty quay that matches the requirements from {@link Ship#canDock(Quay)}. The ship
     *     should only be docked to one quay.</li>
     *     <li>If the time is a multiple of 5, all quays must unload the cargo from ships docked (if
     *     any) and add it to warehouses at the port (the {@link Port}'s list of stored cargo)</li>
     *     <li>All movements stored in the queue whose action time is equal to the current time
     *     should be processed by {@link Port#processMovement(Movement)}</li>
     *     <li>Call {@link StatisticsEvaluator#elapseOneMinute()} on all statistics evaluators</li>
     * </ol>
     */
    public void elapseOneMinute() {
        // 1
        time++;

        // 2
        if (time % 10 == 0) {
            for (Quay quay : getQuays()) {
                if (quay.isEmpty() && shipQueue.getShipQueue().size() != 0
                        && shipQueue.peek().canDock(quay)) {
                    quay.shipArrives(shipQueue.poll());
                    break;
                }
            }
        }

        // 3
        if (time % 5 == 0) {
            for (Quay quay : getQuays()) {
                if (!quay.isEmpty()) {
                    if (quay instanceof BulkQuay && quay.getShip() instanceof BulkCarrier) {
                        try {
                            storedCargo.add(((BulkCarrier) quay.getShip()).unloadCargo());
                        } catch (NoSuchCargoException e) {
                            System.out.println("No cargo to unload from " + quay.getShip());
                        }
                    } else if (quay instanceof ContainerQuay
                            && quay.getShip() instanceof ContainerShip) {
                        try {
                            storedCargo.addAll(((ContainerShip) quay.getShip()).unloadCargo());
                        } catch (NoSuchCargoException e) {
                            System.out.println("No cargo to unload from " + quay.getShip());
                        }
                    }
                }
            }
        }

        // 4
        for (Movement movement : movementQueue) {
            if (movement.getTime() == this.time) {
                processMovement(movement);
            }
        }

        // 5
        for (StatisticsEvaluator eval : statEvals) {
            eval.elapseOneMinute();
        }
    }

    /**
     * Returns the machine-readable string representation of this {@link Port}.
     * <p>
     * The format of the string to return is
     * <pre>
     * Name
     * Time
     * numCargo
     * EncodedCargo
     * EncodedCargo...
     * numShips
     * EncodedShip
     * EncodedShip...
     * numQuays
     * EncodedQuay
     * EncodedQuay...
     * ShipQueue:numShipsInQueue:shipID,shipID,...
     * StoredCargo:numCargo:cargoID,cargoID,...
     * Movements:numMovements
     * EncodedMovement
     * EncodedMovement...
     * Evaluators:numEvaluators:EvaluatorSimpleName,EvaluatorSimpleName,...
     * </pre>
     * Where
     * <ul>
     *     <li>{@code Name} is the name of {@link Port}</li>
     *     <li>{@code Time} is the time elapsed since the simulation started</li>
     *     <li>{@code numCargo} is the total number of cargo in the simulation</li>
     *     <li>If present ({@code numCargo > 0}): {@code EncodedCargo} is the encoded
     *     representation
     *     of each individual cargo in the simulation</li>
     *     <li>{@code numShips} is the total number of ships in the simulation</li>
     *     <li>If present ({@code numShips > 0}): {@code EncodedShip} is the encoded representation
     *     of each individual ship encoding in the simulation</li>
     *     <li>{@code numQuays} is the total number of quays in the {@link Port}</li>
     *     <li>If present ({@code numQuays > 0}): {@code EncodedQuay} is the encoded representation
     *     of each individual quay in the simulation</li>
     *     <li>{@code numShipsInQueue} is the total number of ships in the ship's queue in the port.
     *     </li>
     *     <li>If present ({@code numCargo > 0}): {@code cargoID} is each cargo's ID in the stored
     *     cargo list of {@link Port}</li>
     *     <li>{@code numMovements} is the number of movements in the list of movements in
     *     {@link Port}</li>
     *     <li>If present ({@code numMovements > 0}): {@code EncodedMovement} is the encoded
     *     representation of each individual {@link Movement} in the aforementioned list</li>
     *     <li>{@code numEvaluators} is the number of statistics evaluators in the {@link Port}
     *     evaluators list</li>
     *     <li>If present ({@code numEvaluators > 0}): {@code EvaluatorSimpleName} is the name given
     *     by {@link Class#getSimpleName()} for each evaluator in the aforementioned list separated
     *     by a comma</li>
     *     <li>Each line is separated by a {@link System#lineSeparator()}</li>
     * </ul>
     *
     * @return encoded string representation of this {@link Port}
     */
    public String encode() {
        StringBuilder portEncode = new StringBuilder();

        //Name
        portEncode.append(this.getName());
        portEncode.append(System.lineSeparator());

        // Time
        portEncode.append(this.getTime());
        portEncode.append(System.lineSeparator());

        // Cargo
        portEncode.append(Cargo.getCargoRegistry().size());
        portEncode.append(System.lineSeparator());
        for (Map.Entry<Integer, Cargo> cargo : Cargo.getCargoRegistry().entrySet()) {
            portEncode.append(cargo.getValue().encode());
            portEncode.append(System.lineSeparator());
        }

        // Ships
        portEncode.append(Ship.getShipRegistry().size());
        portEncode.append(System.lineSeparator());
        for (Map.Entry<Long, Ship> entry : Ship.getShipRegistry().entrySet()) {
            portEncode.append(entry.getValue().encode());
            portEncode.append(System.lineSeparator());
        }

        // Quays
        portEncode.append(this.getQuays().size());
        portEncode.append(System.lineSeparator());
        for (Quay quay : this.getQuays()) {
            portEncode.append(quay.encode());
            portEncode.append(System.lineSeparator());
        }

        // ShipQueue
        portEncode.append(this.getShipQueue().encode());

        // Stored Cargo
        portEncode.append("StoredCargo:");
        portEncode.append(this.getCargo().size());
        for (Cargo cargo : this.getCargo()) {
            portEncode.append(cargo.getId());
            portEncode.append(":");
        }
        portEncode.replace(-1, 0, System.lineSeparator());  // todo check the 0 here

        // Movements
        portEncode.append("Movements:");
        portEncode.append(this.getMovements().size());
        for (Movement movement : this.getMovements()) {
            portEncode.append(movement.encode());
            portEncode.append(System.lineSeparator());
        }

        // Evaluators
        portEncode.append("Evaluators:");
        portEncode.append(this.getEvaluators().size());
        portEncode.append(":");
        for (StatisticsEvaluator evaluator : this.getEvaluators()) {
            portEncode.append(evaluator.getClass().getSimpleName());
            portEncode.append(",");
        }
        portEncode.deleteCharAt(portEncode.length() - 1);

        return portEncode.toString();       // todo check this works
    }

    /**
     * Creates a port instance by reading various ship, quay, cargo, movement and evaluator
     * entities from the given reader.
     * <p>
     * The provided file should be in the format
     * <pre>
     * Name
     * Time
     * numCargo
     * EncodedCargo
     * EncodedCaro...
     * numShips
     * EncodedShip
     * EncodedShip...
     * numQuays
     * EncodedQuay
     * EncodedQuay...
     * ShipQueue:NumShipsInQueue:shipID,shipID
     * StoredCargo:numCargo:cargoID,cargoID
     * Movements:numMovements
     * EncodedMovement
     * EncodedMovement...
     * Evaluators:numEvaluators:EvaluatorSimpleName, EvaluatorSimpleName
     * </pre>
     * As specified by {@link Port#encode()}
     * <p>
     * The encoded string is invalid if any of the following conditions are true:
     * <ul>
     *     <li>The time is not a valid long (i.e. cannot be parsed by
     *     {@link Long#parseLong(String)}).</li>
     *     <li>The number of cargo is not an integer (i.e. cannot be parsed by
     *     {@link Integer#parseInt(String)}).</li>
     *     <li>The number of cargo to be read in does not match the number specified above.
     *     (ie. too many / few encoded cargo following the number)</li>
     *     <li>An encoded cargo line throws a {@link BadEncodingException}</li>
     *     <li>The number of ships is not an integer (i.e. cannot be parsed by
     *     {@link Integer#parseInt(String)}).</li>
     *     <li>The number of ship to be read in does not match the number specified above. (ie.
     *     too many / few encoded ships following the number)</li>
     *     <li>An encoded ship line throws a {@link BadEncodingException}</li>
     *     <li>The number of quays is not an integer (i.e. cannot be parsed by
     *     {@link Integer#parseInt(String)}).</li>
     *     <li>The number of quays to be read in does not match the number specified above. (ie.
     *     too many / few encoded quays following the number)</li>
     *     <li>An encoded quay line throws a {@link BadEncodingException}</li>
     *     <li>The {@code shipQueue} does not follow the last encoded quay</li>
     *     <li>The number of ships in the shipQueue is not an integer (i.e. cannot be parsed by
     *     {@link Integer#parseInt(String)}).</li>
     *     <li>The {@code imoNumber} of the ships in the {@code shipQueue} are not valid longs.
     *     (i.e. cannot be parsed by {@link Long#parseLong(String)}).</li>
     *     <li>Any {@code imoNumber} read does not correspond to a valid ship in the simulation</li>
     *     <li>The {@code storedCargo} does not follow the encoded {@code shipQueue}</li>
     *     <li>The number of cargo in the storedCargo is not an integer (i.e. cannot be parsed by
     *     {@link Integer#parseInt(String)}).</li>
     *     <li>The id of the cargo in the storedCargo are not valid Integers. (i.e. cannot be
     *     parsed by {@link Integer#parseInt(String)}).</li>
     *     <li>Any cargo id read does not correspond to a valid cargo in the simulation</li>
     *     <li>The movements do not follow the encoded {@code storedCargo}</li>
     *     <li>The number of movements is not an integer (i.e. cannot be parsed by
     *     {@link Integer#parseInt(String)}).</li>
     *     <li>The number of movements to be read in does not match the number specified above.
     *     (ie. too many / few encoded movements following the number)</li>
     *     <li>An encoded movement line throws a {@link BadEncodingException}</li>
     *     <li>The evaluators do not follow the encoded movements</li>
     *     <li>The number of evaluators is not an integer (i.e. cannot be parsed by
     *     {@link Integer#parseInt(String)}).</li>
     *     <li>The number of evaluators to be read in does not match the number specified above.
     *     (ie. too many / few encoded evaluators following the number)</li>
     *     <li>An encoded evaluator name does not match any of the possible evaluator classes</li>
     *     <li>If any of the following lines are missing:</li>
     *     <ol>
     *         <li>Name</li>
     *         <li>Time</li>
     *         <li>Number of Cargo</li>
     *         <li>Number of Ships</li>
     *         <li>Number of Quays</li>
     *         <li>ShipQueue</li>
     *         <li>StoredCargo</li>
     *         <li>Movements</li>
     *         <li>Evaluators</li>
     *     </ol>
     * </ul>
     *
     * @param reader reader from which to load all info
     * @return port created by reading from given reader
     * @throws IOException          if an {@link IOException} is encountered when reading from the
     *                              reader
     * @throws BadEncodingException if the reader reads a line that does not adhere to the rules
     *                              above indicating that the contents of the reader are invalid.
     */
    public static Port initialisePort(Reader reader) throws IOException, BadEncodingException {
        BufferedReader bufferedReader = new BufferedReader(reader);

        // Port name
        String name = bufferedReader.readLine();

        // --Instantiate port--
        Port port = new Port(name);

        // Time
        try {
            port.time = Long.parseLong(bufferedReader.readLine());
        } catch (NumberFormatException e) {
            throw new BadEncodingException();
        }

        // Cargo
        int numCargo = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < numCargo; i++) {
            Cargo.fromString(bufferedReader.readLine());
        }

        // Ships
        int numShips = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < numShips; i++) {
            Ship.fromString(bufferedReader.readLine());
        }

        // Quays
        int numQuays = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < numQuays; i++) {
            Quay.fromString(bufferedReader.readLine());
        }

        // ShipQueue
        ShipQueue.fromString(bufferedReader.readLine());

        // Stored Cargo
        String[] encodedStoredCargo = bufferedReader.readLine().split(":");
        int numStoredCargo = Integer.parseInt(encodedStoredCargo[1]);

        for (int i = 0; i < numStoredCargo; i++) {
            try {
                port.storedCargo.add(Cargo.getCargoById(Integer.parseInt(
                        encodedStoredCargo[2].split(",")[i])));
            } catch (NoSuchCargoException | NumberFormatException | IndexOutOfBoundsException e) {
                throw new BadEncodingException();
            }
        }

        // Movements
        String[] encodedMovements = bufferedReader.readLine().split(":");
        int numEncodedMovements = Integer.parseInt(encodedMovements[1]);

        for (int i = 0; i < numEncodedMovements; i++) {
            String encodedMovement = bufferedReader.readLine();
            String movementType = encodedMovement.split(":")[0];
            switch (movementType) {
                case "ShipMovement":
                    ShipMovement.fromString(encodedMovement);
                    break;
                case "CargoMovement":
                    CargoMovement.fromString(encodedMovement);
            }
        }

        // Evaluators
        String[] evaluators = bufferedReader.readLine().split(":");
        int numEvaluators = Integer.parseInt(evaluators[1]);

        for (int i = 0; i < numEvaluators; i++) {
            switch (evaluators[2].split(",")[i]) {
                case "QuayOccupancyEvaluator":
                    port.statEvals.add(new QuayOccupancyEvaluator(port));
                    break;
                case "ShipFlagEvaluator":
                    port.statEvals.add(new ShipFlagEvaluator());
                    break;
                case "ShipThroughputEvaluator":
                    port.statEvals.add(new ShipThroughputEvaluator());
                    break;
                case "CargoDecompositionEvaluator":
                    port.statEvals.add(new CargoDecompositionEvaluator());
                    break;
            }
        }

        // Return port

        return port;
    }
}
