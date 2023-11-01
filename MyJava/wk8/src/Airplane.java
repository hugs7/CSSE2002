import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

public class Airplane {
    private String model; // e.g. A380
    private double wingspan; // in metres
    private int weight; // in kg

    public Airplane(String model, double wingspan, int weight) {
        this.model = model;
        this.wingspan = wingspan;
        this.weight = weight;
    }

    public String getModel() { return this.model; }
    public double getWingspan() { return this.wingspan; }
    public int getWeight() { return this.weight; }

    @Override
    public String toString() {
        return model + "|" + String.format("%.2f", wingspan) + "|" + weight;
    }

    /**
     * Writes the toString representation of this airplane to the given writer.
     *
     * If an IOException occurs, the message "IOException occurred" should be
     * printed to System.out.
     *
     * @param writer writer to write this airplane to
     */
    public void saveToFile(Writer writer) throws IOException {
        // write your code here
        BufferedWriter buffWrite = new BufferedWriter(writer);
        try {
            buffWrite.write(this.toString());
        } catch (IOException e) {
            System.out.print("IOException occurred");
        }
        buffWrite.flush();
        buffWrite.close();
    }
}
