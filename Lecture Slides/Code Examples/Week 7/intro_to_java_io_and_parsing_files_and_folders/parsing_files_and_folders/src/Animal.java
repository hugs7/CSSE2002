import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class AnimalFormatException extends Exception {
    public AnimalFormatException(String message, int lineNum) {
        super(message + " at " + lineNum);
    }
}


public class Animal {

    String name;
    String type;
    int weight;

    public Animal(String name, String type, int weight) {
        this.name = name;
        this.type = type;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Animal: " + this.name + ", " + this.type + ", " + this.weight;
    }

    public static List<Animal> loadAnimals(BufferedReader reader) throws AnimalFormatException {

        int lineNum = 0;
        List<Animal> animals;

        try {
            String line = reader.readLine();

            if (line == null) {
                throw new AnimalFormatException("No lines in file", lineNum);
            }

            lineNum++;

            // First line must be animals <count>
            if (!line.startsWith("animals")) {
                throw new AnimalFormatException("File does not start with animals", lineNum);
            }

            String [] pair = line.split(" ", 2);

            if (pair.length != 2) {
                throw new AnimalFormatException("Missing space", lineNum);
            }

            if (!pair[0].equals("animals")) {
                throw new AnimalFormatException("Initial keyword is incorrect", lineNum);
            }

            int numAnimals;

            try {
                numAnimals = Integer.parseInt(pair[1]);
            } catch (NumberFormatException numberFormatException) {
                throw new AnimalFormatException("Missing number of animals", lineNum);
            }

            // Construct a new ArrayList with the required capacity.
            // Note: capacity is optional and for efficiency only.
            animals = new ArrayList<>(numAnimals);

            // Read blank line.
            line = reader.readLine();
            lineNum++;

            if (line == null || !line.equals("")) {
                throw new AnimalFormatException("Line should be empty", lineNum);
            }

            // Read the animal lines.
            for (int i = 0; i < numAnimals; i++) {

                line = reader.readLine();

                if (line == null) {
                    throw new AnimalFormatException("Expected additional line", lineNum);
                }

                lineNum++;

                String [] animalStrings = line.split(",", 3);

                if (animalStrings.length != 3) {
                    throw new AnimalFormatException("Animal malformed", lineNum);
                }

                int animalWeight;

                try {
                    animalWeight = Integer.parseInt(animalStrings[2]);
                } catch (NumberFormatException numberFormatException) {
                    throw new AnimalFormatException("Animal malformed - weight is not a number", lineNum);
                }

                // Construct a new Animal and add it to the list.
                animals.add(new Animal(animalStrings[0], animalStrings[1], animalWeight));
            }
        } catch (IOException ioException) {
            throw new AnimalFormatException("Could not read line because: " + ioException, lineNum);
        }

        // Finally return the constructed animals.
        return animals;
    }

    public static void main(String [] args) {

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("animals.txt"));
        } catch (IOException ioException) {
            System.out.println("Could not open animals.txt: " + ioException);
            System.exit(1);
        }

        List<Animal> animals = null;
        try {
            animals = loadAnimals(reader);
        } catch (AnimalFormatException formatException) {
            System.out.println("Cannot load animals: " + formatException);
            System.exit(2);
        }

        for (Animal animal: animals) {
            System.out.println(animal);
        }
    }
}
