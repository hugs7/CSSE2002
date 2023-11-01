import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Schedule {
    private List<Flight> flights;

    public Schedule(List<Flight> flights) {
        this.flights = flights;
    }

    private static Schedule makeSchedule() {
        List<Flight> flights = new ArrayList<>();
        Airport brisbane = new Airport("Brisbane International", "bne");
        Airport sydney = new Airport("Sydney International", "syd");
        Airport jfk = new Airport("John F. Kennedy International", "jfk");
        Airport dubai = new Airport("Dubai International", "dxb");
        flights.add(new Flight(1192, brisbane, sydney, DayOfWeek.MON));
        flights.add(new Flight(8, sydney, dubai, DayOfWeek.SUN));
        flights.add(new Flight(349, jfk, brisbane, DayOfWeek.WED));
        return new Schedule(flights);
    }

    private List<Airport> getConnectedAirports() {
        List<Airport> seenAirports = new ArrayList<>();
        for (Flight flight : this.flights) {
            if (!seenAirports.contains(flight.getOrigin())) {
                seenAirports.add(flight.getOrigin());
            }
            if (!seenAirports.contains(flight.getDestination())) {
                seenAirports.add(flight.getDestination());
            }
        }
        return seenAirports;
    }

    /**
     * Returns the string representation of this schedule, in the following
     * format:
     *
     * numConnectedAirports
     * numFlights
     * airport1
     * airport2
     * ...
     * airportN
     * flight1
     * flight2
     * ...
     * flightM
     *
     * where each airport and flight line contains the toString() representation
     * of that airport or flight.
     *
     * Lines should be separated using a platform-independent line separator.
     *
     * @return string representation of schedule
     */
    @Override
    public String toString() {
        return super.toString(); // TODO implement according to the Javadoc
    }

    public static void main(String[] args) throws IOException,
            BadScheduleException {
        Schedule schedule = makeSchedule();
        System.out.println(schedule);

        // schedule.save("save.txt");
        // Schedule newSchedule = load("save.txt");
        // System.out.println(newSchedule);
    }
}
