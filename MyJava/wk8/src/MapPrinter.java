
import java.util.Map;

public class MapPrinter {
    // write your printDriverMap method here
    public static <T extends Vehicle> void printDriverMap(DriverMap<T> map) {
        for (T key : map.keySet()) {
            System.out.println(String.format("%s is driven by %s", key.toString(), map.get(key).toString()));
        }
    }

}

