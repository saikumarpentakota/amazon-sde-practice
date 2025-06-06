import java.sql.SQLOutput;
import java.util.Scanner;

public class TASK12 {
    public static void main(String[] args){
        String id = "saikumar";
        String password = "9985840849";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id: ");
        String inputid = sc.nextLine();
        System.out.println("enter password: ");
        String inputpassword = sc.nextLine();
        int count = 0;
       /* while(inputid.equals(id) && inputpassword.equals(password)){
            System.out.println("you have logged in for " + count++ + " times ");
            System.out.println("enter your login id and password");
            id = sc.nextLine();
            password = sc.nextLine();*/
        do {
            System.out.println("you have logged in for " + count++ + " times ");
            System.out.println("enter your login id and password");
            id = sc.nextLine();
            password = sc.nextLine();
            }
                while (id == "saikumar" && password == "9985840849") ;
            sc.close();
        }

}
