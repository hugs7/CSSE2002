package intro_to_java_io.src;

import java.util.Scanner;


public class ScanInts {
    public static void main (String[] args) {
        int total = 0;
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter integer values until their total is greater than 100");
		while (total <= 100) {
            total += input.nextInt();
        }
        System.out.printf("Actual total was " + total);
    }
}