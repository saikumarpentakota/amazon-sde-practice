package DAY15;

public class HOMETASK4 {
    public static void main(String[] args) {
        int number = 12345;

        int digitCount = countDigits(number);
        int digitSum = sumDigits(number);

        System.out.println("Number: " + number);
        System.out.println("Number of digits: " + digitCount);
        System.out.println("Sum of digits: " + digitSum);
    }

    public static int countDigits(int num) {
        if (num == 0) {
            return 0;
        }
        return 1 + countDigits(num / 10);
    }

    public static int sumDigits(int num) {
        if (num == 0) {
            return 0;
        }
        return (num % 10) + sumDigits(num / 10);
    }
}

