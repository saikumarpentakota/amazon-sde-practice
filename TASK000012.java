import java.util.stream.*;
class DoubleColonOp {
    public static void main(String[] args) {
        Stream<String> stream
                = Stream.of("HeLlo", "My",
                "name", "is",
                "saikumar",
                "p");

        stream.forEach(System.out::println);
    }
}

