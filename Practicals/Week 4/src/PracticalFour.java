import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.Random;
public class PracticalFour {
    private static void makeException(ExceptionEnum type)
            throws FileNotFoundException, UnknownException {
        // Your code here.
        switch (type) {
            case NULL -> throw new NullPointerException();
            case BOUNDS -> throw new ArrayIndexOutOfBoundsException();
            case NONE -> System.out.println("No Problems");
            case MISSING -> throw new FileNotFoundException();
            case UNKNOWN -> throw new UnknownException();
        }
    }

    private static void f() {
        try {
            makeException(ExceptionEnum.NULL);
        } catch (NullPointerException e) {
            // do nothing
        } catch (FileNotFoundException | UnknownException e) {
            // unreachable
        }
        try {
            makeException(ExceptionEnum.NONE);
            makeException(ExceptionEnum.MISSING);
            makeException(ExceptionEnum.NONE);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void g(Random random) throws FileNotFoundException, UnknownException {
        int x = random.nextInt(ExceptionEnum.values().length);
        int y = random.nextInt(ExceptionEnum.values().length);

        try {
            makeException(ExceptionEnum.values()[x]);
            System.out.println("x = " + x);
            makeException(ExceptionEnum.values()[y]);
            System.out.println("y = " + y);
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }

    private static void h(Random random) throws UnknownException {
        try {
            g(random);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } finally {
            System.out.println("Reached here!");
        }
    }

    public static void main(String[] args) {
        f();

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            try {
                h(random);
            } catch (UnknownException e) {
                System.out.println(e);
            }
        }
    }
}