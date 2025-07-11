package DAY15;

public class Recursivefunction3{
    public static String reverseString(String str) {

        char[] charArray = str.toCharArray();
        reverseString(charArray, 0, charArray.length - 1);
        return new String(charArray);
    }

    private static void reverseString(char[] str, int start, int end) {

        if (start >= end) {
            return;
        }


        char temp = str[start];
        str[start] = str[end];
        str[end] = temp;


        reverseString(str, start + 1, end - 1);
    }

    public static void main(String[] args) {
        String test = "saikumar pentakota";
        System.out.println("Original string: " + test);
        System.out.println("Reversed string: " + reverseString(test));
    }
}
