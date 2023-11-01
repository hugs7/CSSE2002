package portsim.evaluators;

import portsim.movement.*;
import portsim.port.*;

/**
 * A base class representing an object that gathers and reports data on various aspects of the
 * port's operation.
 */
public abstract class StatisticsEvaluator {
    /** Time since evaluator was created */
    private long time;

    /**
     * Creates a statistics evaluator and initialises the time since the evaluator was created to
     * zero.
     */
    public StatisticsEvaluator() {
        time = 0L;
    }

    /**
     * Returns the time since the evaluator was created.
     * @return time since created
     */
    public long getTime() {
        return time;
    }

    /**
     * Read a movement to update the relevant evaluator data.
     * <p>
     * This method is called by the {@link Port#processMovement(Movement)} method.
     * @param movement movement to read
     */
    public abstract void onProcessMovement(Movement movement);

    /**
     * Simulate a passing minute. The time since the evaluator was created should be incremented
     * by one.
     */
    public void elapseOneMinute() {
        time++;
    }
}
