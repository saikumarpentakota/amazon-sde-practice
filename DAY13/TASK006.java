package DAY13;

import java.util.LinkedList;

public class TASK006 {
        public static void main(String[] args){
            java.util.LinkedList<String> list = new LinkedList<>();
            list.add("11");
            list.add("100");
            list.add("12");
            list.add("20");
            list.add("new iteam");
            System.out.println(list);
            String firstelement = list.getFirst();
            String lastelement = list.getLast();
            System.out.println("1st elemet =" + firstelement);
            System.out.println("last elemet =" + lastelement);

        }
    }

