package DAY12;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Task012{
    public static void main(String[] args) {
        HashMap<String, Integer> ht = new HashMap<>();
       Hashtable<String, Integer> ht1 = new Hashtable<>();
        ht.put("Anitha", 101);
        ht.put("Kavitha", 102);
        ht.put("Meera", 103);
        ht.put("null",1011);
        ht.put("hema",1011);
        ht.put("hema",1011);
        ht1.put("Anitha", 101);
        ht1.put("Kavitha", 102);
        ht1.put("Meera", 103);
        ht1.put("null",1011);
        ht1.put("hema",1011);
        ht1.put("null",1011);


        // use  get method of Ht
        for (Map.Entry<String, Integer> e : ht.entrySet())
            System.out.println(e.getKey() + " " + e.getValue());
        System.out.println("/n");

        for (Map.Entry<String, Integer> e : ht.entrySet())
            System.out.println(e.getKey() + " " + e.getValue());
    }
}

