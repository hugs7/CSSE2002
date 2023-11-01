package portsim.evaluators;

import portsim.port.*;
import portsim.movement.Movement;

/**
 * Evaluator to monitor how many quays are currently occupied at the port.
 */
public class QuayOccupancyEvaluator extends StatisticsEvaluator {
    /**
     * {@link Port} the {@link QuayOccupancyEvaluator} is monitoring
     */
    private final Port port;

    /**
     * Constructs a new {@link QuayOccupancyEvaluator}.
     *
     * @param port port to monitor quays
     */
    public QuayOccupancyEvaluator(Port port) {
        super();

        this.port = port;
    }

    /**
     * Return the number of quays that are currently occupied.
     * <p>
     * A quay is occupied if {@link Quay#isEmpty()} returns false.
     *
     * @return number of quays
     */
    public int getQuaysOccupied() {
        int quaysOccupied = 0;

        for (Quay quay : port.getQuays()) {
            if (!quay.isEmpty()) {
                quaysOccupied++;
            }
        }

        return quaysOccupied;
    }

    /**
     * {@link QuayOccupancyEvaluator} does not make use of {@code onProcessMovement()}, so this
     * method can be left empty.
     * <p>
     * Does nothing. This method is not used by this evaluator.
     *
     * @param movement movement to read
     */
    public void onProcessMovement(Movement movement) {}
}
