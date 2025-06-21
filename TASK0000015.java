import java.util.*;
import java.util.stream.Collectors;


public class TASK0000015 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5, 6);
        List<Integer> squares = numbers.stream()
                .map(num -> num * num)
                .collect(Collectors.toList());

        System.out.println("Squares of the numbers" + squares);
    }


}
