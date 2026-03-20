import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class FileWriting {

    public static void main(String[] args) {

        String filePath="C:\\Users\\SIDDU\\Desktop\\Full-Stack Java Developer\\Day_8\\src\\text.txt";
        System.out.println("FileWriter - Basic Character Writing");
        try{
            FileWriter fw=new FileWriter(filePath);
            fw.write("Hello using FileWriter");

            fw.close();
            System.out.println("Data written successfully!");
        }catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("BufferedWriter (Efficient Writing)");
        try{
            BufferedWriter bw  =new BufferedWriter(new FileWriter(filePath));
            bw.write("\n Hello This is BufferedWriter and it is Efficient approach");
            bw.close();
            System.out.println("Data Inserted... using BufferedWriter");
        }catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("PrintWriter (Convenient Printing)");
        try {
            PrintWriter pw = new PrintWriter(filePath);
            pw.println("Hello using PrintWriter!");
            pw.printf("Formatted number: %d%n", 123);
            pw.close();
            System.out.println("data inserted using PrintWriter");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("FileOutputStream (Byte Writing)");
        try  {
            FileOutputStream fos = new FileOutputStream(filePath);
            String text = "Hello using FileOutputStream!";
            fos.write(text.getBytes());
            fos.close();
            System.out.println("Data inserted using FileOutputStream");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("DataOutputStream (Binary/Text Data)");
        try  {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("output.dat"));
            dos.writeUTF("Hello using DataOutputStream!");
            dos.writeInt(42);
            dos.writeDouble(3.14);
            dos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("FileChannel (NIO)");
        try  {
            FileOutputStream fos = new FileOutputStream("output.txt");
            FileChannel channel = fos.getChannel();
            String text = "Hello using FileChannel!";
            ByteBuffer buffer = ByteBuffer.wrap(text.getBytes());
            channel.write(buffer);
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Files Class");
        try {
            Path path = Paths.get(filePath);
            Files.write(path, Arrays.asList("Hello using Files!", "Second line"), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
