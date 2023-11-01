package set;

import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;


public class SetDemo {

    public static void doSet(Set<String> s) {
        System.out.println("---------------");
        s.add("Fox");
        s.add("Farmer");
        s.add("Chicken");
        s.add("Grain");
        s.add("Fox");
        for (String str : s) {
            System.out.println(str);
        }
        System.out.println(s.contains("Fox"));
    }

    public static void main(String args[]) {
        doSet(new TreeSet<String>());
        doSet(new HashSet<String>());
    }
}
