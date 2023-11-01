package intro_to_java_io.src;

import java.io.*;


public class Read2 {

    public static void main(String[] args) {
        Reader ir = null;
        try {
            // passing the same file
            FileInputStream fis = new FileInputStream("src/intro_to_java_io.src.Read2.java");
            ir = new InputStreamReader(fis);
        } catch (IOException ioe) {
            System.err.println("Could not open the file");
            return;
        }

        char[] letters = new char[11];
        try {
            int num = ir.read(letters, 0, 10);
            for (int i = 0; i < num; ++i) {
                System.out.print(letters[i]);
            }
            System.out.println();
        } catch (IOException ioe) {
            System.err.println("Something went wrong");
        } finally {
            try {    // Note: Need nested try if we don't use try-with-resources
                ir.close();
            } catch (IOException ioe) {
                System.err.println("Closing file failed");
            }
        }
    }
}
