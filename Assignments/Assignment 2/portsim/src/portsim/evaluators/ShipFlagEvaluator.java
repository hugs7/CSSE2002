package portsim.evaluators;

import portsim.movement.*;
import portsim.ship.NauticalFlag;

import java.util.HashMap;
import java.util.Map;

/**
 * Gathers data on how many ships each country has sent to this port.
 * <p>
 * Stores a mapping of country-of-origin flags to the number of times that flag has been seen in
 * inbound movements.
 */
public class ShipFlagEvaluator extends StatisticsEvaluator {
    /**
     * Distribution of flags flown by ships seen at the port as a Map
     */
    private final Map<String, Integer> flagDistribution;

    /**
     * Constructs a new {@link ShipFlagEvaluator}.
     */
    public ShipFlagEvaluator() {
        super();

        flagDistribution = new HashMap<>();
    }

    /**
     * Return the flag distribution seen at this port.
     *
     * @return flag distribution
     */
    public Map<String, Integer> getFlagDistribution() {
        return flagDistribution;
    }

    /**
     * Return the number of times the given flag has been seen at the port.
     *
     * @param flag country flag to find in the mapping
     * @return number of times flag seen or 0 if not seen
     */
    public int getFlagStatistics(String flag) {
        return flagDistribution.getOrDefault(flag, 0);
    }

    /**
     * Updates the internal mapping of ship country flags using the given movement.
     * <p>
     * If any movement is not an {@link MovementDirection#INBOUND} movement, this method returns
     * immediately without taking any action.
     * <p>
     * If the movement is not a {@link ShipMovement}, this method returns immediately without taking
     * any action.
     * <p>
     * If the movement is an {@link MovementDirection#INBOUND} {@link ShipMovement}, do the
     * following:
     * <ul>
     *     <li>If the flag has been seen before (exists as a key in the map) increment that
     *     number</li>
     *     <li>If the flag has not been seen before add as a key in the map with a corresponding
     *     value of {@code 1}</li>
     * </ul>
     *
     * @param movement movement to read
     */
    public void onProcessMovement(Movement movement) {
        if (movement instanceof ShipMovement
                && movement.getDirection().equals(MovementDirection.INBOUND)) {
            String flagString = ((ShipMovement) movement).getShip().getOriginFlag();
            flagDistribution.merge(flagString, 1, Integer::sum);
        }
    }
}
