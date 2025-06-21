import java.util.*;
import java.util.stream.Collectors;


public class TASK0000016 {
    public static void main(String[] args) {
        List<Integer> numbers1 = Arrays.asList(2, 3, 4, 5, 6, 9);
        List<Integer> oddnum = numbers1.stream()
                .filter(num -> num % 2 !=0)
                .collect(Collectors.toList());

        System.out.println(oddnum);
    }
}

