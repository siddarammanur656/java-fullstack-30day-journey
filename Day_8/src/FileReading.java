import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class FileReading{
    public static void main(String[] args) {
        String fileName="text.txt";

        System.out.println("Using BufferedReader");
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while((line=br.readLine())!=null){
                System.out.println(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("Using Scanner");
        try{
            Scanner sc = new Scanner(new File(fileName));
            while(sc.hasNextLine()){
                System.out.println(sc.nextLine());
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("Using File reader");
        try{
            FileReader fr=new FileReader(new File(fileName));
            int i;
            while((i=fr.read()) !=-1){

            }
        }catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("Using DataInputStream");
        try{
            DataInputStream dis=new DataInputStream(new FileInputStream(fileName));
            while(dis.available()>0){
                char c=(char)dis.readByte();
                System.out.println(c);
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("Using FileChannl(NIO)");
        try {
            FileChannel channel = new FileInputStream(fileName).getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (channel.read(buffer) > 0) {
                buffer.flip();
                System.out.print(new String(buffer.array(), 0, buffer.limit()));
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Using Files.readAllLines");
        try{
            List<String> lines= Files.readAllLines(Paths.get(fileName));
            lines.forEach(System.out::println);
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Using StreamTokenizer");
        try {
            // Read from file
            FileReader fr = new FileReader("text.txt");
            StreamTokenizer st = new StreamTokenizer(fr);

            while (st.nextToken() != StreamTokenizer.TT_EOF) {

                if (st.ttype == StreamTokenizer.TT_WORD) {
                    System.out.println("Word: " + st.sval);
                }
                else if (st.ttype == StreamTokenizer.TT_NUMBER) {
                    System.out.println("Number: " + st.nval);
                }
            }

            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
