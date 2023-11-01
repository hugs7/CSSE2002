public class Flight {
    private int number;
    private Airport origin;
    private Airport destination;
    private DayOfWeek dayOperating;

    public Flight(int number, Airport origin, Airport destination, DayOfWeek dayOperating) {
        this.number = number;
        this.origin = origin;
        this.destination = destination;
        this.dayOperating = dayOperating;
    }

    public Airport getOrigin() {
        return origin;
    }

    public Airport getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return number + "|" + origin.getCode() + "|" + destination.getCode()
                + "|" + dayOperating;
    }
}
