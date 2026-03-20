package StudentReportCardSystem;
import java.util.*;

class Student {
    String id, name;
    int year;
    Map<String, Integer> marks = new HashMap<>();

    Student(String id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    void addMark(String subject, int mark) {
        if (mark < 0 || mark > 100) {
            throw new StudentException("Invalid Marks!");
        }
        marks.put(subject, mark);
    }

    double getAverage() {
        int sum = 0;
        for (int m : marks.values()) {
            sum += m;
        }
        return marks.size() == 0 ? 0 : (double) sum / marks.size();
    }

    String getGrade() {
        double avg = getAverage();
        if (avg >= 90) return "A";
        if (avg >= 80) return "B";
        if (avg >= 70) return "C";
        if (avg >= 60) return "D";
        return "F";
    }
}