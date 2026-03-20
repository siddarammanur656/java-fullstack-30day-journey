package StudentReportCardSystem;

public class Main {
    public static void main(String[] args) {

        try {
            Student s1 = new Student("S1", "Ajay", 2);

            s1.addMark("Math", 90);
            s1.addMark("Science", 85);

            System.out.println("Average: " + s1.getAverage());
            System.out.println("Grade: " + s1.getGrade());

            // Save to file
            FileManager.save(s1);

            // Read from file
            System.out.println("\n--- File Data ---");
            FileManager.read();

        } catch (StudentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}