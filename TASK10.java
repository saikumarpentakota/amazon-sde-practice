import java.util.Scanner;
public class TASK10 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter a ");
        int a = scanner.nextInt();
        System.out.println("enter b ");
        int b = scanner.nextInt();
        System.out.println("enter c ");
        int c = scanner.nextInt();
        int greatest;
        if(a >= b && a >= c){
            greatest = a;}
        else if (b >= a && b >= c){
            greatest = b;}
        else { greatest = c;}
        System.out.println(" the greatest number is " + greatest);
    }
}
