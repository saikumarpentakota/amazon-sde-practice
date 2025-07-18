package DAY18;

import java.util.Scanner;
public class TASK003 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number: ");
        long number = scanner.nextLong();


        number = Math.abs(number);


        if (number == 0) {
            System.out.println("It is a 1 digit number.");
        } else {
            int count = 0;
            while (number > 0) {
                number = number / 10;
                count++;
            }
            System.out.println("It is a " + count + " digit number.");
        }

        scanner.close();
    }
}

