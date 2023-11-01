public class CopyDemo {

    public static void main(String args[]) {
        MessageHolder m1 = new MessageHolder("Bob");

        // clone() returns an object so needs to be cast to the type I know it to be
        MessageHolder m2 = (MessageHolder) m1.clone();

        System.out.println(m1 == m2); // do m1 and m2 refer to the same object?
        System.out.println(m1);
        System.out.println(m2);

        m1.addText(" Smith");
        System.out.println(m1);
        System.out.println(m2);
    }
}