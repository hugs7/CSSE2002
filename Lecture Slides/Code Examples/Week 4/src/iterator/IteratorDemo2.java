package iterator;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;


public class IteratorDemo2 {

    public static void main(String args[]) {
        List<String> ls = new ArrayList<>();
        ls.add("1");
        ls.add("2");
        ls.add("3");
        ls.add("4");

        Iterator<String> it = ls.iterator();
        // remove first item
        //Iterator<String> it2 = ls.iterator();
        it.next();
        it.remove();

        // use first iterator
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
