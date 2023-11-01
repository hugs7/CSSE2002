package list;

import java.util.List;
import java.util.ArrayList;


public class ListDemo {

    public static void main(String args[]) {
        List<String> ls = new ArrayList<>();
        System.out.println("Size=" + ls.size());
        ls.add("1");
        ls.add("2");
        System.out.println("Size=" + ls.size());
        ls.add("3");
        ls.add("4");
        System.out.println("Size=" + ls.size());

        for (String s : ls) {
            System.out.println(s);
        }

        // reverse the contents
        for (int i = 0; i < (ls.size() + 1) / 2; ++i) {
            int npos = ls.size() - 1 - i;
            String temp = ls.get(i);
            ls.set(i, ls.get(npos));
            ls.set(npos, temp);
        }
        System.out.println("----");
        for (String s : ls) {
            System.out.println(s);
        }

        // remove item
        ls.remove(2);       // will close gap
        System.out.println("----");
        for (String s : ls) {
            System.out.println(s);
        }
    }
}
