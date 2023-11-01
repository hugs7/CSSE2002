package intro_to_java_io.src;

import java.io.*;


public class Read1 {

    public static void main(String[] args) {
        char[] letters = new char[11];
        // using a Reader object to get the useful capabilities of this class
        try (Reader ir = new InputStreamReader(System.in)) {
            // start from position 0 and read 10 characters.
            // we can use num to know how many characters we read in.
            int num = ir.read(letters, 0, 10);

            System.out.println(num);

            // still works if it is i < 10 as the array elements are declared, but empty
            // will fail if i < 11 as this would be trying to access an array element which does not exist
            for (int i = 0; i < num; ++i) {
                System.out.print(letters[i]);
            }
            System.out.println();
        } catch (IOException ioe) {
            System.err.println("Something went wrong");
        }
    }
}
