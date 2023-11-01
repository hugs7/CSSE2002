/***
 * All of the loop in this class print out the numbers 0 to 9, but each uses
 * different loops to do it.
 */
public class Loops {

    public static void main(String[] args) {
        /* Print out numbers 0 to 9 using while loop */
        System.out.println("While Loop Example");
        int i = 0;
        while (i < 10) {
            System.out.println("'i' is " + i);
            i++; // same as i = i + 1;
        }

        /* Print out numbers 0 to 9 using for loop */
        System.out.println("For loop example");
        for (int j = 0; j < 10; j++) {
            System.out.println("'j' is " + j);
        }

        /* Print out numbers 0 to 9 using for each loop */
        System.out.println("For each loop example");
        int[] oddNumbers = {1, 3, 5, 7, 9};
        for (int number : oddNumbers) {
            System.out.println("'number' is " + number);
        }

        /* Print out numbers 0 to 9 using do while loop */
        System.out.println("Do While Loop Example");
        int k = 0;
        do {
            System.out.println("'k' is " + k);
            k++; // same as k = k + 1;
        } while(k < 10);
    }
}
