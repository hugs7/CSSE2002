package intro_to_java_io.src;

import java.io.*;


public class Read3 {

    public static void main(String[] args) {
        try (Reader ir = new FileReader("src/intro_to_java_io.src.Read2.java")) {

            char[] letters = new char[11];
            int num = ir.read(letters, 0, 10);
            for (int i = 0; i < num; ++i) {
                System.out.print(letters[i]);
            }
            System.out.println();
        } catch (FileNotFoundException fnf) {
            System.err.println("Could not open the file");
        } catch (IOException ioe) {
            System.err.println("Something went wrong");
        }
    }

}