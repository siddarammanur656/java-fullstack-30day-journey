import java.util.Scanner;

public class ScannerDemo{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your name: ");
        String name = sc.nextLine();  //reads whole line including spaces

        System.out.println("Enter the age: ");
        int age=sc.nextInt(); //reads integer
        sc.nextLine();

        System.out.print("Enter your GPA: ");
        double gpa = sc.nextDouble();

        System.out.printf("Hello %s! You are %d years old with GPA %.1f%n", name, age, gpa);

        sc.close(); // always close when done


    }
}