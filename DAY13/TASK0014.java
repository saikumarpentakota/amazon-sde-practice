package DAY13;

import java.util.*;
import java.util.LinkedList;

public class TASK0014 {
    public static void main(String[] args) {
        LinkedList<String> lobj = new LinkedList<>();
        lobj.add("SAIKUMAR");
        lobj.add("PENTAKOTA");
        lobj.add("M");
        Spliterator<String> sitobj = lobj.spliterator();
        //forEachRemaining is a method of Spliterator
        System.out.println("Splitting the list:");
        sitobj.forEachRemaining(System.out::println);
    }
}

