import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

interface Sortingstrategy {
    List<String> sort(List<String> items);
}

class AlphabeticalSorting implements Sortingstrategy {
    @Override
    public List<String> sort(List<String> items) {
        return items.stream()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toList());
    }
}


class LengthwiseSorting implements Sortingstrategy {
    @Override
    public List<String> sort(List<String> items) {
        return items.stream()
                .sorted((s1, s2) -> Integer.compare(s2.length(), s1.length()))
                .collect(Collectors.toList());
    }
}

class SortingContext {
    private List<String> items;
    private Sortingstrategy strategy;

    public SortingContext() {
        this.items = new ArrayList<>();
    }


    public void setStrategyForSorting(Sortingstrategy strategy) {
        this.strategy = strategy;
    }


    public void addItems(String item) {
        items.add(item);
    }


    public void performSort() {
        if (strategy == null) {
            throw new IllegalStateException("Sorting strategy not set");
        }
        items = strategy.sort(items);
    }


    public List<String> getList() {
        return items;
    }
}

public class Sortingstratgey {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            SortingContext context = new SortingContext();

            System.out.println("Enter strings (type 'Done' to finish):");
            while (true) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("Done")) {
                    break;
                }
                context.addItems(input);
            }


            System.out.println("\nAlpha sorting:");
            context.setStrategyForSorting(new AlphabeticalSorting());
            context.performSort();
            context.getList().forEach(System.out::println);


            System.out.println("\nLengthwise sorting:");
            context.setStrategyForSorting(new LengthwiseSorting());
            context.performSort();
            context.getList().forEach(System.out::println);

            scanner.close();
        }
}
