import java.util.Scanner;
public class TASK007 {
    public static void main(String[] args){
            Scanner scanner = new Scanner(System.in);{
            System.out.print("enter your ID; ");
            String userID = scanner.nextLine();
            System.out.print("enter your password; ");
            String password = scanner.nextLine();
            String maskedPassword = scanner.nextLine();
            System.out.println("HI; ");
            System.out.println("YOUR LOGIN ID IS; " + userID);
            System.out.println("AND YOUR PASSWORD IS ; " + maskedPassword);
        }
    }
}
