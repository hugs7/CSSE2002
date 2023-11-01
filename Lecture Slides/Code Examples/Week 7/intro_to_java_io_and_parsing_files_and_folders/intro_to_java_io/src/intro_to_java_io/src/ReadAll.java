package intro_to_java_io.src;

import java.io.*;


public class ReadAll {
    /* reads all bytes in an input stream */
    public static void readAll(InputStream is) {
        try {
            int b;
            do {
                b = is.read();
            } while (b != -1); // while not at the End of File
        } catch (IOException ioe) {
            System.err.println("Something bad happened");
        }
    }

    /* Evaluates the time to read a file using two different approaches */
    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            readAll(new BufferedInputStream(new FileInputStream("data/big")));
            long mid = System.currentTimeMillis();
            /* same data in 'big2' as 'big'. We use different files to avoid
             * the operating system from caching the file for the second time
             * we read the data */
            readAll(new FileInputStream("data/big2"));
            long end = System.currentTimeMillis();
            /* the times you get may be different to the lecture slides
             * depending on the speed of your computer */
            System.out.println("First approach: \t" + (mid - start) + "ms");
            System.out.println("Second approach: \t" + (end - mid) + "ms");
        } catch (FileNotFoundException fnf) {
            System.err.println("Could not open a file");
        }
    }

}
