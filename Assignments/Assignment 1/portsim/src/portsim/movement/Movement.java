package portsim.movement;

/**
 * The Movement class handles the movement of ships or cargo in and out of a port from land or sea.
 */
public abstract class Movement  {
    /** Stores the time the ship or cargo comes in or out of the port */
    private final long time;

    /** Stores the direction the ship or cargo is moving */
    private final MovementDirection direction;

    /**
     * Creates a new movement with the given action time and direction.
     * @param time the time the movement should occur.
     * @param direction the direction of the movement.
     */
    public Movement(long time, MovementDirection direction) {
        if (time < 0) {
            throw new IllegalArgumentException("Time is invalid: " + time + " < 0");
        }

        this.time = time;
        this.direction = direction;
    }

    /**
     * Returns the time the movement should be actioned.
     * @return movement time.
     */
    public long getTime() {
        return time;
    }

    /**
     * Returns the direction of the movement.
     * @return movement direction.
     */
    public MovementDirection getDirection() {
        return direction;
    }

    /**
     * Returns the human-readable string representation of this Movement.
     * @return string representation of this Movement.
     */
    @Override
    public String toString() {
        return this.getDirection() + " " + this.getClass().getSimpleName() + " to occur at "
                + this.getTime();
    }
}