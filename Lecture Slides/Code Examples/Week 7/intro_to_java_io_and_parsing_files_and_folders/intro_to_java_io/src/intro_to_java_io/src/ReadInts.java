package intro_to_java_io.src;

import java.io.*;
import java.util.Scanner;


public class ReadInts {

    public static void fromScanner(InputStream is) {
        Scanner scan = new Scanner(is);
        /* We can just search for all the integers in the file one after
        * another */
        while (scan.hasNextInt()) {
            int i = scan.nextInt();
            System.out.println(i);
        }
    }

    public static void fromStrings(FileReader fr) {
        BufferedReader br = new BufferedReader(fr);
        /* We need to read the file line by line. The numbers in each
         * line have to be extracted */
        String line;
        try {
            while ((line = br.readLine()) != null) {
                /* extract the numbers from the line into an array. The space
                 * character is used as the delimiter */
                String[] toks = line.split(" ");
                for (String t : toks) {
                    try {
                        int i = Integer.parseInt(t); // convert string to int
                        System.out.println(i);
                    } catch (NumberFormatException nfe) {
                        System.out.println("That's not an integer");
                    }
                }
            }
        } catch (IOException ioe) {
            System.out.println("Something went wrong");
        }
    }

    /* Read integers from a text file using two different methods */
    public static void main(String[] args) {

        System.out.println("Read integers using a Scanner:");
        try (FileInputStream fis = new FileInputStream("data/nums.txt")) {
            fromScanner(fis);
        } catch (FileNotFoundException fnf) {
            System.err.println("Couldn't find file");
        } catch (IOException ioe) {
            System.err.println("Something went wrong, probably while closing file");
        }



        System.out.println("\nRead integers by parsing strings read from file:");
        try (FileReader fr = new FileReader("data/nums.txt")) {
            fromStrings(fr);
        } catch (FileNotFoundException fnf) {
            System.err.println("Couldn't find file");
        } catch (IOException ioe) {
            System.err.println("Something went wrong, probably while closing file");
        }


    }
}