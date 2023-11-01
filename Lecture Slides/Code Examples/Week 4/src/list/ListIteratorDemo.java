package list;

import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;


public class ListIteratorDemo {

    public static void main(String[] args) {
        List<String> ls = new LinkedList<>();
        ls.add("1");
        ls.add("2");
        ls.add("4");
        ls.add(2, "3");     // add at a numbered position
        for (String s : ls) {
            System.out.println(s);
        }

        System.out.println("-------------");

        // We can ask for a normal iterator to remove things
        // but we'd like to add things in position.
        ListIterator<String> li = ls.listIterator();
        while (li.hasNext()) {
            String s = li.next();
            li.add(s + ".5");
        }

        for (String s : ls) {
            System.out.println(s);
        }
    }
}
