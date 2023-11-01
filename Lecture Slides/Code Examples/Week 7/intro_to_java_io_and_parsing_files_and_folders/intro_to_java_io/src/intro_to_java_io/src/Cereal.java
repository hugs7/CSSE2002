package intro_to_java_io.src;

import java.io.*;

// Serialisation involves converting the current state of an object into a byte steam
public class Cereal implements Serializable {

    private String name;
    private String quest;
    private int num;

    public Cereal(String name, String task) {
        this.name = name;
        this.quest = task;
        this.num = name.length() + quest.length();
    }

    public String toString() {
        return name + " " + quest + " => " + num;
    }


    public static void main(String[] args) {
        Cereal c1 = new Cereal("Bob", "pass");
        Cereal c2 = new Cereal("Tim", "bunnies");
        System.out.println(c1);
        System.out.println(c2);

        // Save objects
        // The objs files saves data in binary. This is not human readable.
        // Opening the file will show some things we understand, and parts we cannot.
        // Strings get stored as characters so will render as characters in IntelliJ,
        // but other information we cannot understand.
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/objs"))) {
            oos.writeObject(c1);
            oos.writeObject(c2);
        } catch (IOException ioe) {
            System.err.println("Something went wrong: " + ioe);
        }

        System.out.println("\nintro_to_java_io.src.Output after loading saved objects:");
        // Load objects
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("data/objs"))){
            Object o1 = is.readObject();
            Object o2 = is.readObject();
            System.out.println(o1);
            System.out.println(o2);
        } catch (IOException ioe) {
            System.err.println("Another thing went wrong: " + ioe);
        } catch (ClassNotFoundException cnf) {
            System.err.println("How?");
        }
    }

}
