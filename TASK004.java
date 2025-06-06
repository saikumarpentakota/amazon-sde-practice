public class TASK004 {
    public static void main(String[] args){
        int a = 2;
        int b = 5;
        System.out.println("before swapping: a = " + a + ",b =  " + b);
        int temp = a;
        a = b;
        b = temp;
        System.out.println("after swapping: a = " + a + ",b =  " + b);
    }
}
