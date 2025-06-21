
import java.io.IOException;
import java.io.*;
public class Filehandling4 {
    public static void main(String args[]){
    File f1 = new File("Filename03.txt");
    FileWriter fw = null;
        try {
            fw= new FileWriter(f1);
            fw.write("ahmedabad \n");
        fw.write(" baroda \n");
        fw.close();}

        catch(FileNotFoundException e){
        System.out.println("Sorry..!! File Not Found...!!!");
    }
    catch(IOException e)
    {
        System.out.println(e.getMessage());
    }
    System.out.println("write operation done");
    }

}
