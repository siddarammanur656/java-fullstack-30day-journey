package ProjectStudentEnrollmentSystem;

import java.util.*;

class StudentRecord implements Comparable<StudentRecord> {
    private final String id;
    private final String name;
    private final String email;
    private final int    year;

    // LinkedHashSet — enrolled courses in insertion order, no duplicates
    private final Set<Course> enrolledCourses = new LinkedHashSet<>();

    // TreeMap — grade history sorted by course code
    private final Map<String, Double> grades = new TreeMap<>();

    // ArrayDeque — enrollment history (log)
    private final Deque<String> activityLog = new ArrayDeque<>();

    public StudentRecord(String id, String name, String email, int year) {
        this.id    = id;
        this.name  = name;
        this.email = email;
        this.year  = year;
    }

    public boolean enrollIn(Course course) {
        if (!course.hasSpace()) {
            log("FAILED to enroll in " + course.getCode() + " — full");
            return false;
        }
        if (enrolledCourses.contains(course)) {
            log("Already enrolled in " + course.getCode());
            return false;
        }
        // Credit limit check
        int totalCredits = enrolledCourses.stream()
                                          .mapToInt(Course::getCredits)
                                          .sum();
        if (totalCredits + course.getCredits() > 20) {
            log("FAILED to enroll in " + course.getCode()
              + " — credit limit exceeded (" + totalCredits + "/20)");
            return false;
        }
        course.enroll();
        enrolledCourses.add(course);
        log("Enrolled in " + course.getCode());
        return true;
    }

    public boolean dropCourse(Course course) {
        if (!enrolledCourses.remove(course)) {
            log("Not enrolled in " + course.getCode());
            return false;
        }
        course.unenroll();
        grades.remove(course.getCode()); // remove grade if any
        log("Dropped " + course.getCode());
        return true;
    }

    public void setGrade(Course course, double grade) {
        if (!enrolledCourses.contains(course)) {
            System.out.println("Cannot grade: not enrolled in " + course.getCode());
            return;
        }
        if (grade < 0 || grade > 100)
            throw new IllegalArgumentException("Grade must be 0–100");
        grades.put(course.getCode(), grade);
        log("Grade set: " + course.getCode() + " = " + grade);
    }

    public double calculateGPA() {
        if (grades.isEmpty()) return 0.0;
        double totalWeighted = 0;
        int    totalCredits  = 0;
        for (Course c : enrolledCourses) {
            if (grades.containsKey(c.getCode())) {
                totalWeighted += grades.get(c.getCode()) * c.getCredits();
                totalCredits  += c.getCredits();
            }
        }
        return totalCredits == 0 ? 0.0 : totalWeighted / totalCredits;
    }

    public int getTotalCredits() {
        return enrolledCourses.stream().mapToInt(Course::getCredits).sum();
    }

    private void log(String action) {
        activityLog.offerLast(id + " | " + action);
        // Keep only last 10 entries
        while (activityLog.size() > 10) activityLog.pollFirst();
    }

    // Getters
    public String        getId()            { return id; }
    public String        getName()          { return name; }
    public String        getEmail()         { return email; }
    public int           getYear()          { return year; }
    public Set<Course>   getEnrolledCourses(){ return Collections.unmodifiableSet(enrolledCourses); }
    public Map<String,Double> getGrades()   { return Collections.unmodifiableMap(grades); }
    public Deque<String> getActivityLog()   { return activityLog; }

    @Override
    public int compareTo(StudentRecord other) {
        return this.name.compareTo(other.name); // natural: alphabetical
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentRecord)) return false;
        return id.equals(((StudentRecord) o).id);
    }

    @Override public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return String.format("Student{id=%s, name='%s', year=%d, credits=%d, GPA=%.2f}",
                id, name, year, getTotalCredits(), calculateGPA());
    }
}