package StudentReportCardSystem;

import java.io.*;

class FileManager {

    // Save student data to file
    static void save(Student s) {
        try  {
            BufferedWriter bw = new BufferedWriter(new FileWriter("students.txt", true));
            bw.write(s.id + "," + s.name + "," + s.year + "," + s.getAverage());
            bw.newLine();

        } catch (IOException e) {
            System.out.println("File Error!");
        }
    }

    // Read file
    static void read() {
        try  {
            BufferedReader br = new BufferedReader(new FileReader("students.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("File Read Error!");
        }
    }
}