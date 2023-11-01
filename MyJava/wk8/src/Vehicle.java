
public class Vehicle {
    int id;

    public Vehicle(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }
}