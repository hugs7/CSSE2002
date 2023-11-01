package customset;

import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;


class Type3 implements Comparable<Type3> {

    public char x;

    public Type3(char c) {
        x = c;
    }

    public String toString() {
        return "" + x;
    }

    public boolean equals(Object o) {
        if (o instanceof Type3) {
            return this.x == ((Type3) o).x;
        }
        return false;
    }

    public int hashCode() {
        return x;
    }

    /**
     * Compares an instance of Type3 to another instance of Type3.
     * Currently this will sort elements in reverse alphabetical order.
     * To sort elements in  alphabetical order, exchange where return -1 and return 1
     * are included.
     * @param o
     * @return
     */
    public int compareTo(Type3 o) {    // change to return 0 to
        if (this.x == o.x) {           // see what happens
            return 0;
        } else if (this.x < o.x) {
            return -1;
        } else {
            return 1;
        }
    }

}


public class CustSet3 {

    public static void doSet(Set<Type3> s) {
        s.add(new Type3('C'));
        s.add(new Type3('B'));
        s.add(new Type3('A'));

        System.out.println(s);

        Type3 t = new Type3('C');
        System.out.println(s.contains(t));
    }

    public static void main(String args[]) {
        System.out.println("---- HashSet output ----");
        doSet(new HashSet<Type3>());

        System.out.println("---- TreeSet output ----");
        doSet(new TreeSet<Type3>());
    }
}
