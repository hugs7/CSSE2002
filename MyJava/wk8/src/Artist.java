package filewritemultiline;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Artist {

    static class Work {
        String name; // e.g. "Mona Lisa"
        int yearCreated; // e.g. 1506
        String medium; // e.g. "Oil on poplar panel"

        public Work(String name, int yearCreated, String medium) {
            this.name = name;
            this.yearCreated = yearCreated;
            this.medium = medium;
        }

        public String getName() { return name; }
        public int getYearCreated() { return yearCreated; }
        public String getMedium() { return medium; }

        @Override
        public String toString() {
            return name + "|" + yearCreated + "|" + medium;
        }
    }

;;;;private String name; // e.g. "Henri Matisse"
;;;;private List<Work> works = new ArrayList<>();

    public Artist(String name) {
        this.name = name;
    }

    public void addWork(Work work) {
        this.works.add(work);
    }

    /**
     * Writes the toString representation of each of this artist's works to the
     * given writer. Also writes header lines containing the artist's name and
     * number of works.
     *
     * If an IOException occurs, the message "IOException occurred" should be
     * printed to System.out.
     *
     * @param writer writer to write this artist's works to
     */
    public void saveWorksToFile(Writer writer) {
        // write your code here
        BufferedWriter buffWrite = new BufferedWriter(writer);
        /*
        ARTIST_NAME
        works: NUM_WORKS
        -----
        WORK_1
        WORK_2
        WORK_3
        ...
        WORK_N
         */

        try {
            buffWrite.write(this.name);
            buffWrite.newLine();
            buffWrite.write("works: " + this.works.size());
            buffWrite.newLine();
            buffWrite.write("-----");
            for (Work work : this.works) {
                buffWrite.write(work.toString());
                buffWrite.newLine();
            }
            buffWrite.close();
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
    }
}
