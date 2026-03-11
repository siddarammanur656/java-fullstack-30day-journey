package ProjectStudentEnrollmentSystem;

// ── Main
public class EnrollmentMain {
    public static void main(String[] args) {
        EnrollmentSystem sys = new EnrollmentSystem();

        System.out.println("🏫 STUDENT ENROLLMENT SYSTEM");
        System.out.println("═".repeat(65));

        // Add courses
        System.out.println("\n── Adding Courses ──");
        Course cs101 = sys.addCourse("CS101","Intro to Programming",   3, 3);
        Course cs201 = sys.addCourse("CS201","Data Structures",        4, 2);
        Course cs301 = sys.addCourse("CS301","Algorithms",             4, 30);
        Course math1 = sys.addCourse("MATH1","Calculus I",             3, 4);
        Course eng1  = sys.addCourse("ENG1", "Technical Writing",      2, 3);
        Course db1   = sys.addCourse("DB101","Database Systems",       3, 3);

        // Add students
        System.out.println("\n── Adding Students ──");
        StudentRecord alice   = sys.addStudent("Alice Smith",   "alice@uni.edu",   1);
        StudentRecord bob     = sys.addStudent("Bob Jones",     "bob@uni.edu",     2);
        StudentRecord charlie = sys.addStudent("Charlie Brown", "charlie@uni.edu", 1);
        StudentRecord diana   = sys.addStudent("Diana Prince",  "diana@uni.edu",   3);
        StudentRecord eve     = sys.addStudent("Eve Wilson",    "eve@uni.edu",     2);

        // Enroll students
        System.out.println("\n── Enrollments ──");
        sys.enroll(alice.getId(),   "CS101");
        sys.enroll(alice.getId(),   "MATH1");
        sys.enroll(alice.getId(),   "CS201");

        sys.enroll(bob.getId(),     "CS101");
        sys.enroll(bob.getId(),     "CS201"); // CS201 has capacity 2
        sys.enroll(bob.getId(),     "CS301");

        sys.enroll(charlie.getId(), "CS101"); // CS101 capacity 3 — FULL now
        sys.enroll(charlie.getId(), "MATH1");

        sys.enroll(diana.getId(),   "CS101"); // waitlisted — CS101 full!
        sys.enroll(diana.getId(),   "CS301");
        sys.enroll(diana.getId(),   "DB101");

        sys.enroll(eve.getId(),     "CS201"); // waitlisted — CS201 full!
        sys.enroll(eve.getId(),     "MATH1");
        sys.enroll(eve.getId(),     "ENG1");

        // Set grades
        System.out.println("\n── Setting Grades ──");
        sys.setGrade(alice.getId(),   "CS101", 95.0);
        sys.setGrade(alice.getId(),   "MATH1", 88.0);
        sys.setGrade(alice.getId(),   "CS201", 92.0);
        sys.setGrade(bob.getId(),     "CS101", 75.0);
        sys.setGrade(bob.getId(),     "CS201", 82.0);
        sys.setGrade(bob.getId(),     "CS301", 79.0);
        sys.setGrade(charlie.getId(), "CS101", 91.0);
        sys.setGrade(charlie.getId(), "MATH1", 85.0);
        sys.setGrade(diana.getId(),   "CS301", 96.0);
        sys.setGrade(diana.getId(),   "DB101", 88.0);
        sys.setGrade(eve.getId(),     "MATH1", 72.0);
        sys.setGrade(eve.getId(),     "ENG1",  94.0);

        // Drop to test waitlist processing
        System.out.println("\n── Drop + Waitlist Test ──");
        sys.drop(alice.getId(), "CS101"); // frees CS101 spot → Diana gets it!

        // Reports
        sys.printAllCourses();
        sys.printAllStudents();
        sys.printTopStudents(3);
        sys.printCourseStats();
        sys.printGradeDistribution();
        sys.printStudentDetail(alice.getId());
        sys.printStudentDetail(diana.getId());
    }
}