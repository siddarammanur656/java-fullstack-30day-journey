import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GradeTracker {

    //Helper Methods
    static String getLetterGrade(double avg){

        if(avg>=90){
            return "A";
        }else if(avg>=80){
            return "B";
        } else if(avg>=70){
            return "C";
        }else if(avg>=60){
            return "D";
        }else {
            return "F";
        }

    }

    static String getRemarks(String grade){

        String remark=switch (grade){
            case "A" ->"Outstanding!";
            case "B" ->"Good work!";
            case "C" ->"Satisfactory";
            case "D" ->"Needs improvement";
            default -> "Please seek academic help";
        };
        return remark;
    }
    static double getValidMark(Scanner sc ,  String subject){
        double mark=-1;
        while(mark<0 || mark>100){
            System.out.println("Enter mark for "+subject+" : ");
            if(sc.hasNextDouble()){
                mark=sc.nextDouble();
                if(mark<0 || mark>100){
                    System.out.println("Marks must be between 0 and 100!");
                }
            }else{
                System.out.println("Please enter a valid number");
                sc.next();
            }
        }
        return mark;
    }
    static void printDivider(char ch, int width){
        System.out.println(String.valueOf(ch).repeat(width));
    }


    //Main Program
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> studentNames=new ArrayList<>();
        List<Double> studentAvgs=new ArrayList<>();

        String [] subjects={"Mathematics", "Science", "English", "History", "Computer"};

        printDivider('=',50);
        System.out.println("           STUDENT GRADE TRACKER v1.0");
        printDivider('=',50);


        System.out.print("How many students? ");
        while(!sc.hasNextInt()){
            sc.next();
        }
        int numStudents=sc.nextInt();
        sc.nextLine(); //consume newline

        for (int i = 0; i < numStudents; i++) {
            printDivider('-', 50);
            System.out.printf("STUDENT %d of %d%n", i + 1, numStudents);
            printDivider('-', 50);

            System.out.print("Enter student name: ");
            String name = sc.nextLine().trim();
            if (name.isEmpty()) name = "Student " + (i + 1);

            List<Double> marks = new ArrayList<>();
            System.out.println("Enter marks (0–100) for each subject:");

            for (String subject : subjects) {
                double mark = getValidMark(sc, subject);
                marks.add(mark);
            }

            // Calculate stats
            double sum  = 0;
            double high = marks.get(0);
            double low  = marks.get(0);

            for (double m : marks) {
                sum  += m;
                if (m > high) high = m;
                if (m < low)  low  = m;
            }

            double avg    = sum / marks.size();
            String letter = getLetterGrade(avg);
            String remark = getRemarks(letter);

            // Individual report
            System.out.println();
            printDivider('*', 50);
            System.out.printf("  REPORT CARD: %s%n", name.toUpperCase());
            printDivider('*', 50);
            System.out.printf("  %-15s  %s%n", "Subject", "Mark");
            printDivider('-', 50);
            for (int j = 0; j < subjects.length; j++) {
                System.out.printf("  %-15s  %.1f%n", subjects[j], marks.get(j));
            }
            printDivider('-', 50);
            System.out.printf("  Average:         %.2f%n", avg);
            System.out.printf("  Highest Mark:    %.1f%n",  high);
            System.out.printf("  Lowest Mark:     %.1f%n",  low);
            System.out.printf("  Grade:           %s%n",   letter);
            System.out.printf("  Remarks:         %s%n",   remark);
            printDivider('*', 50);
            System.out.println();

            studentNames.add(name);
            studentAvgs.add(avg);
        }

        // Class summary
        if (numStudents > 1) {
            printDivider('=', 50);
            System.out.println("          CLASS SUMMARY");
            printDivider('=', 50);

            double classSum = 0;
            double classHigh = studentAvgs.get(0);
            double classLow  = studentAvgs.get(0);
            String topStudent = studentNames.get(0);
            String lowStudent = studentNames.get(0);

            for (int i = 0; i < studentAvgs.size(); i++) {
                double avg = studentAvgs.get(i);
                classSum += avg;
                if (avg > classHigh) { classHigh = avg; topStudent = studentNames.get(i); }
                if (avg < classLow)  { classLow  = avg; lowStudent = studentNames.get(i); }
            }

            double classAvg = classSum / numStudents;

            // Grade distribution
            int[] dist = new int[5]; // A, B, C, D, F
            for (double avg : studentAvgs) {
                if      (avg >= 90) dist[0]++;
                else if (avg >= 80) dist[1]++;
                else if (avg >= 70) dist[2]++;
                else if (avg >= 60) dist[3]++;
                else                dist[4]++;
            }

            System.out.printf("  Total Students:  %d%n",   numStudents);
            System.out.printf("  Class Average:   %.2f (%s)%n", classAvg, getLetterGrade(classAvg));
            System.out.printf("  Top Student:     %s (%.2f)%n", topStudent, classHigh);
            System.out.printf("  Needs Help:      %s (%.2f)%n", lowStudent, classLow);
            printDivider('-', 50);
            System.out.println("  Grade Distribution:");
            System.out.printf("  A (90-100): %d students%n", dist[0]);
            System.out.printf("  B (80-89):  %d students%n", dist[1]);
            System.out.printf("  C (70-79):  %d students%n", dist[2]);
            System.out.printf("  D (60-69):  %d students%n", dist[3]);
            System.out.printf("  F (0-59):   %d students%n", dist[4]);
            printDivider('=', 50);
        }

        System.out.println("Thank you for using Grade Tracker!");
        sc.close();
    }
}