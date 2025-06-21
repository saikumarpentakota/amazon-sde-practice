import java.io.FileOutputStream;
import java.io.IOException;
import java.io.*;

public class Filehandling {
    public static void main(String args[])
    {
        File f1 = new File("Filename01.txt");
                FileOutputStream outfile = null;
        byte Text[] = {'I','L','O','V','E','I','N','D','I','A'};
        try
        {
            outfile = new FileOutputStream(f1);
            outfile.write(Text);
        }
        catch(IOException e)
        {
            System.out.println(e);
            System.exit(-1);
        }
        System.out.println("Write Byte");
        System.out.println("Thank You...!!!");
    }
}
