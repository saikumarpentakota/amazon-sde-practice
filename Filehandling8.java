import java.io.*;

public class Filehandling8 {
    public static void main(String args[]){
        File f1 = new File(args[0]);
        File f2 = new File(args[1]);
        boolean success = f1.renameTo(f2);
        //f1.renameTo(f2);
        if(success){
            System.out.println("rename file " +f1+ "to" + f2+ "succesfull");
        }else{
            System.out.println("failed");
        }

    }
}
