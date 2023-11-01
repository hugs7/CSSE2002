package dangerousloops;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Each of the methods in this class has similar issues.
 * They are each modifying and retrieving something from the loop
 * while going through the loop.
 * */
public class DangerousLoops {

    public static void method1() {
        List<Integer> l = new ArrayList<>();
        l.add(10);
        l.add(8);
        l.add(3);

        /* Every iteration, it gets the current value then adds it to the list.
        * List size keeps increasing, so the loop never ends. Java runs out of
        * memory. */
        for (int i = 0; i < l.size(); i++) {
            l.add(l.get(i) + 1);
        }

        for (Integer i : l) {
            System.out.println(i);
        }
    }

    public static void method2() {
        List<Integer> l = new ArrayList<>();
        l.add(10);
        l.add(8);
        l.add(3);

        /* Causes ConcurrentModificationException.
        * Trying to modify the list (.add()) while also in the process of
        * accessing something from it (using for each loop implicitly gets
        * the element)  */
        for (Integer i : l) {
            l.add(i + 1);
        }

        for (Integer i : l) {
            System.out.println(i);
        }
    }

    public static void method3() {
        List<Integer> l = new ArrayList<>();
        l.add(10);
        l.add(8);
        l.add(3);

        /* Modifying and retrieving something from the loop while I go through
         it is dangerous. */
        int size = l.size();
        for (int i = 0; i < size; i++) {
            l.add(l.get(i) + 1);
        }

        for (Integer i : l) {
            System.out.println(i);
        }
    }

    public static void method4() {
        List<Integer> l = new ArrayList<>();
        l.add(10);
        l.add(8);
        l.add(3);

        /* Causes ConcurrentModificationException.
         * Trying to modify the list while also retrieving something from it.
         * Will get an exception because the iterator will become invalid after
         * we use add() the first time. */
        ListIterator<Integer> li = l.listIterator();
        while (li.hasNext()) {
            l.add(li.next() + 1);
        }

        for (Integer i : l) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
       // method1();
        //method2();
        //method3();
        method4();
    }

}
