package customset;

import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;

// needs to implement Comparable for TreeSet
class Type2 {
    public char x;

    public Type2(char c) {
        x = c;
    }

    public String toString() {
        return "" + x;
    }

    public boolean equals(Object o) {
        if (o instanceof Type2) {
            return this.x == ((Type2) o).x;
        }
        return false;
    }

    public int hashCode() {
        return x;
    }

}


public class CustSet2 {

    public static void doSet(Set<Type2> s) {
        s.add(new Type2('C'));
        s.add(new Type2('B'));
        s.add(new Type2('A'));

        System.out.println(s);

        Type2 t = new Type2('C');
        System.out.println(s.contains(t));
    }

    public static void main(String args[]) {
        System.out.println("---- HashSet output ----");
        doSet(new HashSet<Type2>());

        /* This issue is because TreeSet is sorted, where HashSet is not.
        equals() is not the same as comapreTo()
        When we add elements Java needs to be able to determine where it goes in the TreeSet.
        If we use a type we define (e.g. Type2), we need to implement and define compareTo().
         */
        System.out.println("---- TreeSet output ----");
        doSet(new TreeSet<Type2>()); // this line causes problems
    }
}