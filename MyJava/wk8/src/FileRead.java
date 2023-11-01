
import java.io.*;

public class FileRead {

    /**
     * Reads the content of a given file (accessed by the provided reader),
     * sums all the tokens (separated by spaces) in the file, then returns the sum.
     * If the file is empty, return 0.
     * If the reader is null, return 0.
     * If there are non-numbers tokens in the file (e.g. file5.txt), these
     * specific tokens need to be ignored when calculating the sum.
     * @param reader file reader object which has already been initialised to
     *               read from a target .txt file.
     * @return the sum of all the numbers in the file
     */
    public static double sumTotalInFile(Reader reader) {
        BufferedReader buffRead = new BufferedReader(reader);
        String line;
        double count = 0.0;
        try {

            while ((line = buffRead.readLine()) != null) {
                String[] toks;
                toks = line.split(" ");

                for (String t : toks) {
                    try {
                        double i = Double.parseDouble(t);
                        count += i;
                    } catch (NumberFormatException e) {
                        System.out.println("Format exception handled");
                    }
                }
            }
            return count;
        } catch (NullPointerException e) {
            return count;
        } catch (IOException e) {
            return 0;
        }
    }


    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("data.txt");
            System.out.println(sumTotalInFile(reader));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }


    }
}
