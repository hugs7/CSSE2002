import java.util.LinkedHashMap;

// write your DriverMap class here


public class DriverMap<K extends Vehicle, V extends Driver> extends LinkedHashMap<K, V> {
    private DriverMap<K, V> map;

    public DriverMap() {
        map = (DriverMap<K, V>) new LinkedHashMap<>();
    }
}

/*
public class DriverMap<K extends Vehicle> extends LinkedHashMap<Vehicle, Driver> {
    private final Map<Class<?>, Object> values = new HashMap<>();

    public <T> void put( Class<K> key, T value ) {
        values.put( key, value );
    }

    public <T> T get( Class<T> key ) {
        return key.cast( values.get( key ) );
    }
}*/