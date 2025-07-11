package DAY15;

public class Recursivefunction2{
    public static int countDigits(int number) {
        if (number == 0) {
            return 0;
        }


        return 1 + countDigits(number / 10);
    }

    public static int sumOfDigits(int number) {
        if (number == 0) {
            return 0;
        }

        return (number % 10) + sumOfDigits(number / 10);
    }

    public static void main(String[] args) {
        int number = 12345;

        int digitCount = countDigits(number);
        System.out.println("Number of digits in " + number + ": " + digitCount);


        int digitSum = sumOfDigits(number);
        System.out.println("Sum of digits in " + number + ": " + digitSum);
    }
}

