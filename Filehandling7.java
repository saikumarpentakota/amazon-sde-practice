import java.io.*;
public class Filehandling7{
    public static void main(String args[])
    {
        try
        {
            FileInputStream file1 = new FileInputStream("Filename01.txt");
            FileInputStream file2 = new FileInputStream("Filename02.txt");
            FileInputStream file3 = new FileInputStream("Filename03.txt");
            SequenceInputStream file4 = new SequenceInputStream(file1, file2);

            BufferedInputStream br1 = new BufferedInputStream(file4);
            BufferedOutputStream br2 = new BufferedOutputStream(System.out);
            int ch;
            while((ch = br1.read())!=-1)
            {
                br2.write((char)ch);
            }
            br1.close();
            br2.close();
            file1.close();
            file2.close();
            file3.close();
            System.out.println("Merge Two File Sucessfully ");
        }
        catch(IOException e)
        {
            System.out.println("Sorry..!! File Not Found...!!!");
        }
    }
}

