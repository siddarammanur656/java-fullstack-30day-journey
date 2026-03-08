// ── CHILD CLASS: Intern
public class Intern extends Employee{
    private String university;
    private int    durationMonths;
    private String mentor; // mentor's name

    public Intern(String name, int age, String email, double salary, String university, int durationMonths, String mentor) {
        super(name, age, email, salary);
        this.university    = university;
        this.durationMonths = durationMonths;
        this.mentor        = mentor;
    }
    @Override
    public double calculateBonus() {
        return 0; // interns don't get bonuses
    }

    @Override
    public void work() {
        System.out.println(name + " (Intern from " + university
                + ") is learning under " + mentor);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("  └─ University: %-20s | Duration: %d months | Mentor: %s%n", university, durationMonths, mentor);
    }
    // Getters
    public String getUniversity(){
        return university;
    }
    public int getDurationMonths(){
        return durationMonths;
    }
    public String getMentor(){
        return mentor;
    }

    @Override
    public String toString() {
        return String.format("Intern{name='%s', university='%s', mentor='%s'}", name, university, mentor);
    }
}