package portsim.evaluators;

import portsim.movement.*;
import portsim.ship.Ship;

import java.util.*;

/**
 * Gathers data on how many ships pass through the port over time.
 * <p>
 * This evaluator only counts ships that have passed through the port in the last hour (60 minutes)
 */
public class ShipThroughputEvaluator extends StatisticsEvaluator {
    /**
     * An ordered map containing the time each ship passed through the port
     */
    private Map<Long, Ship> shipMap = new TreeMap<>();

    /**
     * Constructs a new {@link ShipThroughputEvaluator}.
     * <p>
     * Immediately after creating a new {@link ShipThroughputEvaluator}, get
     * {@link #getThroughputPerHour()} should return {@code 0}.
     */
    public ShipThroughputEvaluator() {
        super();
    }

    /**
     * Return the number of ships that have passed through the port in the last 60 minutes.
     *
     * @return ships throughput
     */
    public int getThroughputPerHour() {
        return shipMap.size();
    }

    /**
     * Updates the internal count of ships that have passed through the port using the given
     * movement.
     * <p>
     * If the movement is not an {@link MovementDirection#OUTBOUND} {@link ShipMovement}, this
     * method returns immediately without taking any action.
     * <p>
     * Otherwise, the internal state of this evaluator should be modified such that
     * {@link #getThroughputPerHour()} should return a value of {@code 1} more than before this
     * method was called.
     *
     * @param movement movement to read
     */
    public void onProcessMovement(Movement movement) {
        if (movement instanceof ShipMovement
                && movement.getDirection().equals(MovementDirection.OUTBOUND)) {
            shipMap.put(movement.getTime(), ((ShipMovement) movement).getShip());
        }
    }

    /**
     * Simulating a minute passing. The time since the evaluator was created should be
     * incremented by one.
     * <p>
     * If it has been more than 60 minutes since a ship exited the port, it should no longer be
     * counted towards the count returned by {@link #getThroughputPerHour()}.
     */
    @Override
    public void elapseOneMinute() {
        super.elapseOneMinute();

        for (long shipTime : shipMap.keySet()) {
            if (getTime() - shipTime > 60) {
                shipMap.remove(shipTime);
            }
        }
    }
}
