import java.util.*;
import java.util.stream.Collectors;


public class TASK0000017 {
    public static void main(String[] args) {
        List<Integer> numbers2 = Arrays.asList(2, 2, 2, 3, 3, 4, 6, 5, 6, 9);
        List<Integer> RemovDups = numbers2.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println(RemovDups);
    }

}

