package customset;

import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;

// needs to implement Comparable for TreeSet
class Type1 {
    public char x;

    public Type1(char c) {
        x = c;
    }
}


public class CustSet1 {

    public static void doSet(Set<Type1> s) {
        s.add(new Type1('C'));
        s.add(new Type1('B'));
        s.add(new Type1('A'));

        System.out.println(s);

        Type1 t = new Type1('C');
        System.out.println(s.contains(t));
    }

    public static void main(String args[]) {
        System.out.println("---- HashSet output ----");
        doSet(new HashSet<Type1>()); // this line is ok

        /* This issue is because TreeSet is sorted, where HashSet is not.
        When we add elements Java needs to be able to determine where it goes in the TreeSet.
        If we use a type we define (e.g. Type1), we need to implement and define compareTo().
         */
        System.out.println("---- TreeSet output ----");
        doSet(new TreeSet<Type1>()); // this line causes problems
    }
}
