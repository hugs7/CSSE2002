package portsim.evaluators;

import portsim.cargo.*;
import portsim.movement.*;
import portsim.ship.BulkCarrier;
import portsim.ship.ContainerShip;
import portsim.ship.Ship;
import portsim.util.NoSuchCargoException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Collects data on what types of cargo are passing through the port. Gathers data on all
 * derivatives of the cargo class.
 * <p>
 * The data gathered is a count of how many times each type of cargo has entered the port. This
 * includes a count of how many times the port has received "{@link BulkCargo}" or
 * "{@link Container}" class cargo. As well as a count of how many times the port has seen each
 * cargo subclass type ({@link ContainerType} and {@link BulkCargoType}).
 */
public class CargoDecompositionEvaluator extends StatisticsEvaluator {
    /**
     * Distribution of the types of cargo at the port as a map
     */
    private final Map<String, Integer> cargoDistribution;

    /**
     * Distribution of the types of BulkCargo at the port as a map
     */
    private Map<BulkCargoType, Integer> bulkCargoDistribution;

    /**
     * Distribution of the types of Containers at the port as a map
     */
    private Map<ContainerType, Integer> containerDistribution;


    /**
     * Constructors a new {@link CargoDecompositionEvaluator}.
     */
    public CargoDecompositionEvaluator() {
        super();
        cargoDistribution = new HashMap<String, Integer>();
    }

    /**
     * Returns the distribution of which cargo types that have entered the port.
     *
     * @return cargo distribution map
     */
    public Map<String, Integer> getCargoDistribution() {
        return cargoDistribution;
    }

    /**
     * Returns the distribution of bulk cargo types that have entered the port.
     *
     * @return bulk cargo distribution map
     */
    public Map<BulkCargoType, Integer> getBulkCargoDistribution() {
        return bulkCargoDistribution;
    }

    /**
     * Returns the distribution of container cargo types that have entered the port.
     *
     * @return container distribution map
     */
    public Map<ContainerType, Integer> getContainerDistribution() {
        return containerDistribution;
    }

    /**
     * Updates the internal distributions of cargo types using the given movement.
     * <p>
     * If any movement is not an {@link MovementDirection#INBOUND} movement, this method returns
     * immediately without taking any action.
     * <p>
     * If the movement is an {@link MovementDirection#INBOUND} movement, do the following
     * <ul>
     *     <li>If the movement is a {@link ShipMovement}, Retrieve the cargo from the ships and
     *     for each
     *     piece of cargo:</li>
     *     <ol>
     *         <li>If the cargo class ({@link Container} / {@link BulkCargo}) has been seen before
     *         (simple name exists as a key in the cargo map) -> increment that number</li>
     *         <li>If the cargo class has not been seen before then add its class simple name as a
     *         key in the map with a corresponding value of {@code 1}</li>
     *         <li>If the cargo type (Value of {@link ContainerType} / {@link BulkCargoType}) for
     *         the given cargo class has been seen before (exists as a key in the map) increment
     *         that number</li>
     *         <li>If the cargo type (Value of {@link ContainerType} / {@link BulkCargoType}) for
     *         the given cargo class has not been seen before add as a key in the map with a
     *         corresponding value of {@code 1}</li>
     *     </ol>
     *     <li>If the movement is a {@link CargoMovement}, Retrieve the cargo from the movement. For
     *     the cargo retrieved:</li>
     *     <ol>
     *         <li>Complete steps {@code 1}-{@code 4} as given above for {@link ShipMovement}</li>
     *     </ol>
     * </ul>
     *
     * @param movement movement to read
     * @see StatisticsEvaluator#onProcessMovement(Movement)
     */
    public void onProcessMovement(Movement movement) {      // todo test
        if (!movement.getDirection().equals(MovementDirection.INBOUND)) {
            return;
        }

        List<Cargo> unloadedCargoList = new ArrayList<>();

        if (movement instanceof ShipMovement) {
            Ship movementShip = ((ShipMovement) movement).getShip();

            if (movementShip instanceof BulkCarrier) {
                try {
                    unloadedCargoList.add(((BulkCarrier) movementShip).unloadCargo());
                } catch (NoSuchCargoException e) {
                    System.out.println("No cargo to unload from " + movementShip);
                }
            } else if (movementShip instanceof ContainerShip) {
                try {
                    unloadedCargoList.addAll(((ContainerShip) movementShip).unloadCargo());
                } catch (NoSuchCargoException e) {
                    System.out.println("No cargo to unload from " + movementShip);
                }
            }
        } else if (movement instanceof CargoMovement) {
            unloadedCargoList.addAll(((CargoMovement) movement).getCargo());
        } else {
            return;
        }

        // Iterate over cargo
        for (Cargo piece : unloadedCargoList) {
            if (piece instanceof BulkCargo) {
                cargoDistribution.merge(piece.getClass().getSimpleName(), 1, Integer::sum);
                bulkCargoDistribution.merge(((BulkCargo) piece).getType(), 1, Integer::sum);
            } else if (piece instanceof Container) {
                cargoDistribution.merge(piece.getClass().getSimpleName(), 1, Integer::sum);
                containerDistribution.merge(((Container) piece).getType(), 1, Integer::sum);
            }
        }
    }
}
