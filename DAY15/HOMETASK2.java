package DAY15;

public class HOMETASK2 {
    public static void main(String[] args) {
        String str = "Hello World";
        StringBuilder sb = new StringBuilder();
        reverseString(str, sb, str.length() - 1);

        System.out.println("Original string: " + str);
        System.out.println("Reversed string: " + sb.toString());
    }

    public static void reverseString(String str, StringBuilder sb, int index) {
        if (index < 0) {
            return;
        }
        sb.append(str.charAt(index));
          reverseString(str, sb, index - 1);
        }

}



