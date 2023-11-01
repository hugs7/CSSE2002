package intro_to_java_io.src;

import java.io.*;


public class Output {

    public static void sendNums(PrintWriter pw) {
        for (int i = 0; i < 10; ++i) {
            pw.print(i);
            pw.print(" ");
        }
        /* For writers, we need to flush output.
         * Otherwise if the output is buffered, it may not get sent until the
         * buffer is full. */
        pw.flush();
    }


    public static void main(String[] args) {
        sendNums(new PrintWriter(System.out)); // passing print writer which wraps Sytem.out

        // we can print numbers to a file
        try (PrintWriter pw = new PrintWriter("data/outs")) {
            sendNums(pw);
        } catch (FileNotFoundException fnf) {
            System.out.println("Couldn't open the file");
        }

        // same concept as above but an alternate method
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("data/outs2")))) {
            sendNums(pw);
        } catch (IOException ioe) {
            System.out.println("Which one failed?");
        }
    }

}