package ProjectStudentEnrollmentSystem;
// ── Enrollment System
import java.util.*;
class EnrollmentSystem {

    // HashMap — fast student lookup by ID
    private final Map<String, StudentRecord> students    = new HashMap<>();

    // TreeMap — courses sorted by code
    private final Map<String, Course>        courses     = new TreeMap<>();

    // PriorityQueue — waitlist by enrollment request (FIFO simulation)
    private final Queue<String[]>            waitlist    = new LinkedList<>();

    // Set — track which IDs have been used (fast duplicate check)
    private final Set<String>                usedIds     = new HashSet<>();

    // ── STUDENT MANAGEMENT ────────────────────────────────────
    public StudentRecord addStudent(String name, String email, int year) {
        String id = generateId("STU");
        StudentRecord student = new StudentRecord(id, name, email, year);
        students.put(id, student);
        usedIds.add(id);
        System.out.println("  ✅ Added: " + student);
        return student;
    }

    public boolean removeStudent(String id) {
        StudentRecord s = students.remove(id);
        if (s == null) return false;
        // Unenroll from all courses
        for (Course c : s.getEnrolledCourses()) c.unenroll();
        System.out.println("  🗑 Removed student: " + id);
        return true;
    }

    // ── COURSE MANAGEMENT ─────────────────────────────────────
    public Course addCourse(String code, String name,
                            int credits, int capacity) {
        if (courses.containsKey(code)) {
            System.out.println("  ⚠ Course " + code + " already exists");
            return courses.get(code);
        }
        Course course = new Course(code, name, credits, capacity);
        courses.put(code, course);
        System.out.println("  ✅ Added course: " + course);
        return course;
    }

    // ── ENROLLMENT ────────────────────────────────────────────
    public boolean enroll(String studentId, String courseCode) {
        StudentRecord s = students.get(studentId);
        Course        c = courses.get(courseCode);

        if (s == null) { System.out.println("  ❌ Student not found: " + studentId); return false; }
        if (c == null) { System.out.println("  ❌ Course not found: "  + courseCode); return false; }

        boolean success = s.enrollIn(c);
        if (!success && c.hasSpace() == false) {
            waitlist.offer(new String[]{studentId, courseCode});
            System.out.println("  ⏳ Added to waitlist: "
                + s.getName() + " → " + courseCode);
        }
        return success;
    }

    public boolean drop(String studentId, String courseCode) {
        StudentRecord s = students.get(studentId);
        Course        c = courses.get(courseCode);
        if (s == null || c == null) return false;

        boolean dropped = s.dropCourse(c);
        if (dropped) processWaitlist(courseCode); // fill the freed spot
        return dropped;
    }

    private void processWaitlist(String courseCode) {
        // Try to enroll next waitlisted student for this course
        Iterator<String[]> it = waitlist.iterator();
        while (it.hasNext()) {
            String[] entry = it.next();
            if (entry[1].equals(courseCode)) {
                it.remove();
                System.out.println("  📢 Processing waitlist for "
                    + courseCode + "...");
                enroll(entry[0], entry[1]);
                break;
            }
        }
    }

    // ── GRADE MANAGEMENT ──────────────────────────────────────
    public void setGrade(String studentId, String courseCode, double grade) {
        StudentRecord s = students.get(studentId);
        Course        c = courses.get(courseCode);
        if (s != null && c != null) s.setGrade(c, grade);
    }

    // ── QUERIES ───────────────────────────────────────────────
    public void printAllStudents() {
        System.out.println("\n" + "═".repeat(65));
        System.out.println("  ALL STUDENTS (alphabetical)");
        System.out.println("═".repeat(65));
        // Sort alphabetically using TreeSet
        new TreeSet<>(students.values()).forEach(s ->
            System.out.println("  " + s));
    }

    public void printAllCourses() {
        System.out.println("\n" + "═".repeat(65));
        System.out.println("  ALL COURSES (by code)");
        System.out.println("═".repeat(65));
        courses.values().forEach(c -> System.out.println("  " + c));
    }

    public void printTopStudents(int n) {
        System.out.println("\n" + "═".repeat(65));
        System.out.printf("  TOP %d STUDENTS BY GPA%n", n);
        System.out.println("═".repeat(65));

        // PriorityQueue for top-N (max-heap by GPA)
        PriorityQueue<StudentRecord> pq = new PriorityQueue<>(
            Comparator.comparingDouble(StudentRecord::calculateGPA).reversed()
        );
        pq.addAll(students.values());

        for (int i = 0; i < n && !pq.isEmpty(); i++) {
            StudentRecord s = pq.poll();
            System.out.printf("  #%d %-15s GPA: %.2f | Courses: %d%n",
                    i+1, s.getName(), s.calculateGPA(),
                    s.getEnrolledCourses().size());
        }
    }

    public void printCourseStats() {
        System.out.println("\n" + "═".repeat(65));
        System.out.println("  COURSE ENROLLMENT STATS");
        System.out.println("═".repeat(65));

        // Sort by fill rate descending
        courses.values().stream()
            .sorted(Comparator.comparingDouble(
                c -> -(double)c.getEnrolled()/c.getMaxCapacity()))
            .forEach(c -> {
                double fillRate = (double)c.getEnrolled() / c.getMaxCapacity() * 100;
                String bar = "█".repeat((int)(fillRate/10))
                           + "░".repeat(10-(int)(fillRate/10));
                System.out.printf("  %-8s %s %5.1f%%%n",
                        c.getCode(), bar, fillRate);
            });
    }

    public void printGradeDistribution() {
        System.out.println("\n" + "═".repeat(65));
        System.out.println("  GRADE DISTRIBUTION (all students)");
        System.out.println("═".repeat(65));

        // TreeMap — sorted grade ranges
        Map<String, Integer> dist = new TreeMap<>();
        dist.put("A (90-100)", 0);
        dist.put("B (80-89)",  0);
        dist.put("C (70-79)",  0);
        dist.put("D (60-69)",  0);
        dist.put("F (0-59)",   0);

        for (StudentRecord s : students.values()) {
            for (double g : s.getGrades().values()) {
                String key = g>=90 ? "A (90-100)"
                           : g>=80 ? "B (80-89)"
                           : g>=70 ? "C (70-79)"
                           : g>=60 ? "D (60-69)" : "F (0-59)";
                dist.merge(key, 1, Integer::sum);
            }
        }

        dist.forEach((grade, count) -> {
            String bar = "▓".repeat(count * 3);
            System.out.printf("  %-12s: %s (%d)%n", grade, bar, count);
        });
    }

    public void printStudentDetail(String studentId) {
        StudentRecord s = students.get(studentId);
        if (s == null) { System.out.println("Student not found"); return; }

        System.out.println("\n" + "═".repeat(65));
        System.out.println("  STUDENT DETAIL: " + s.getName());
        System.out.println("═".repeat(65));
        System.out.printf("  ID: %-10s | Year: %d | Email: %s%n",
                           s.getId(), s.getYear(), s.getEmail());
        System.out.printf("  GPA: %.2f | Credits: %d%n",
                           s.calculateGPA(), s.getTotalCredits());
        System.out.println("  Enrolled Courses:");
        s.getEnrolledCourses().forEach(c ->
            System.out.printf("    %-8s %-25s | Grade: %s%n",
                c.getCode(), c.getName(),
                s.getGrades().containsKey(c.getCode())
                    ? String.format("%.1f", s.getGrades().get(c.getCode()))
                    : "N/A"));
        System.out.println("  Recent Activity:");
        s.getActivityLog().forEach(e -> System.out.println("    > " + e));
        System.out.println("═".repeat(65));
    }

    private String generateId(String prefix) {
        String id;
        do { id = prefix + (1000 + new Random().nextInt(9000)); }
        while (usedIds.contains(id));
        return id;
    }
}