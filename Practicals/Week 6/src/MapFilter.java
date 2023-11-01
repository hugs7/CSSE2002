import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class MapFilter {

    /**
     * Takes a provided TreeMap, removes entries where the
     * (i) key is a multiple of keyFilter
     * AND
     * (ii) the value contains the valueFilter character,
     * then returns the resulting TreeMap.
     * If applying the key and value filters does not remove any entries, then
     * the provided map must be returned.
     * @param keyFilter filter to be applied to the map entry keys. Must not be 0.
     * @param valueFilter filter to be applied to the map entry values
     * @return resulting map when filters are applied to provided map
     */
    public static TreeMap<Integer, String> filterTreeMap(
            TreeMap<Integer, String> map, int keyFilter, char valueFilter) {
        TreeMap<Integer, String> newMap = new TreeMap<Integer, String>();

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (!(entry.getKey() % keyFilter == 0 && entry.getValue().indexOf(valueFilter) != -1)) {
                newMap.put(entry.getKey(), entry.getValue());
            }
        }

        return newMap;
    }

    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<Integer, String>();

        treeMap.put(1, "Hello");
        treeMap.put(2, "hugo");
        treeMap.put(3, "This");
        treeMap.put(4, "Is");
        treeMap.put(5, "A");
        treeMap.put(6, "Test");
        treeMap.put(7, "Program");

        int keyFilter = 3;
        char valueFilter = 's';
        System.out.println(treeMap);
        TreeMap<Integer, String> newMap = filterTreeMap(treeMap, keyFilter, valueFilter);

        System.out.println(newMap);
    }
}
