import java.io.*;
public class Filehandling6{

public static void main(String args[]) {
    try {
        byte b = 0;
        FileInputStream infile = new FileInputStream("NewFile01.txt");
        FileOutputStream outfile = new FileOutputStream("NewFile05.txt");
        int byteread = infile.read();

        while (byteread != -1) {
            b = (byte) infile.read();
            outfile.write(b);
        }
        System.out.println("Byte Copied From in.txt to out.txt FIle ");
    } catch (FileNotFoundException e) {
        System.out.println("Sorry..!! File Not Found...!!!");
    } catch (IOException e) {
        System.out.println(e.getMessage());
    }
}
}
