package iterator;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;


public class IteratorDemo {

    public static void main(String args[]) {
        List<String> ls = new ArrayList<>();
        ls.add("1");
        ls.add("2");
        ls.add("3");
        ls.add("4");

        Iterator<String> it = ls.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        // remove "3"
        it = ls.iterator();
        while (it.hasNext()) {
            String s = it.next();
            if (s.equals("3")) {
                it.remove();
            }

        }
        System.out.println("----");
        for (String s : ls) {
            System.out.println(s);
        }
    }
}
