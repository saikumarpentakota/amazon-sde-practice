import java.util.ArrayList;
public class TASK0000012 {
    public static void main(String[] args){
        ArrayList<String>names = new ArrayList<>();

        names.add("sai");
        names.add("vineeth");
        names.add("kishore");
        names.add("veeresh");
        names.add("surya");

        System.out.println("names");
        for(String name : names){
            System.out.println(name);
        }
    }
}
