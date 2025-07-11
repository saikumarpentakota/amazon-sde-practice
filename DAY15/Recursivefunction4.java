package DAY15;

public class Recursivefunction4 {
    public static String decimalToBinary(int decimal) {
        if (decimal == 0) {
            return "0";
        }
        if (decimal == 1) {
            return "1";
        }

        return decimalToBinary(decimal / 2) + (decimal % 2);
    }

    public static void main(String[] args) {
        int[] testNumbers = {0, 5, 10, 15, 20, 255};

        for (int num : testNumbers) {
            System.out.println("Decimal: " + num +
                    " -> Binary: " + decimalToBinary(num));
        }
    }
}

