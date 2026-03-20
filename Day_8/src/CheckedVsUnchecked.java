import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CheckedVsUnchecked {
    //checked-must declare with 'throws' or catch internally
    static String readFile(String path) throws IOException {

        return Files.readString(Paths.get(path));

    }
    //unchecked-no declaration needed
    static int divide(int a, int b){
        return a/b;
    }

    public static void main(String[] args) {

        try {

            String content = readFile("C:\\Users\\SIDDU\\Desktop\\Full-Stack Java Developer\\Day_8\\src\\text.txt");

            System.out.println("File content:\n" + content);

        } catch (IOException e) {

            System.out.println("File error: " + e.getMessage());

        }

        System.out.println(divide(4,0));
    }

}
