import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

public class FileNavigation {
    public static void main(String[] args) throws IOException {

        Path dir = Path.of("student_data");

        // create directory
        Files.createDirectories(dir);
        Files.createDirectories(dir.resolve("grades"));
        Files.createDirectories(dir.resolve("reports"));

        //path operations
        Path file = dir.resolve("students.csv");
        System.out.println("Absolute: " + file.toAbsolutePath());
        System.out.println("Parent:   " + file.getParent());
        System.out.println("Filename: " + file.getFileName());
        System.out.println("Extension: " + getExtension(file));

        // file operations
        // Create
        Files.writeString(file, "name,score\nAlice,95\n");

        // Check
        System.out.println("Exists:    " + Files.exists(file));
        System.out.println("Is dir:    " + Files.isDirectory(file));
        System.out.println("Is file:   " + Files.isRegularFile(file));
        System.out.println("Readable:  " + Files.isReadable(file));
        System.out.println("Size:      " + Files.size(file) + " bytes");

        // Copy
        Path copy = dir.resolve("students_backup.csv");
        Files.copy(file, copy, StandardCopyOption.REPLACE_EXISTING);

        // Move/Rename
        Path moved = dir.resolve("students_v2.csv");
        Files.move(copy, moved, StandardCopyOption.REPLACE_EXISTING);

        // Delete
        Files.deleteIfExists(moved); // safe — no exception if missing

        //list directory content
        System.out.println("Directory contents:");
        try  {
            DirectoryStream<Path> stream = Files.newDirectoryStream(dir);
            for (Path entry : stream) {
                System.out.println(entry.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        //walk directory tree
        System.out.println("\nAll files recursively:");
        try  {
            Stream<Path> walk = Files.walk(dir);
            walk.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //find the file
        try (Stream<Path> find = Files.find(dir, 10,
                (path, attrs) -> path.toString().endsWith(".csv"))) {
            find.forEach(p -> System.out.println("CSV: " + p));
        }
    }

    static String getExtension(Path path) {
        String name = path.getFileName().toString();
        int dot = name.lastIndexOf('.');
        return dot > 0 ? name.substring(dot + 1) : "";
    }
}
