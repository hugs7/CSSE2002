package map;

import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Iterator;


public class MapDemo {

    public static void doMap(Map<Integer, String> m) {
        System.out.println("---------------");
        m.put(3, "Fox");
        m.put(6, "Farmer");
        m.put(7, "Chicken");
        m.put(5, "Grain");
        m.put(3, "Hello");

        Iterator<Map.Entry<Integer, String>> it = m.entrySet().iterator();
        // Alternate implementation: var it = m.entrySet().iterator();
        while (it.hasNext()) {
            System.out.println(it.next().getValue());
        }
        System.out.println(m.containsValue("Fox"));
    }

    public static void main(String args[]) {
        doMap(new TreeMap<Integer, String>());
        doMap(new HashMap<Integer, String>());
    }
}
