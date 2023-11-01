public class IncrementDecrement {

    public static void preIncrement() {
        int x = 1;
        int res = (++x);
        System.out.println(x + " " + res);
    }
    public static void postIncrement() {
        int x = 1;
        int res = (x++);
        System.out.println(x + " " + res);
    }
    public static void preDecrement() {
        int x = 1;
        int res = (--x);
        System.out.println(x + " " + res);
    }
    public static void postDecrement() {
        int x = 1;
        int res = (x--);
        System.out.println(x + " " + res);
    }

    public static void main(String args[]) {
        preIncrement();
        postIncrement();
        preDecrement();
        postDecrement();
    }
}
