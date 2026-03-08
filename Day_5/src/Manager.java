// ── CHILD CLASS: Manager
public class Manager extends Employee{
    private String department;
    private int    teamSize;
    private double budgetAuthority;

    // ── USE 1 of super: Call parent constructor ────────────────
    // super() MUST be the first statement in child constructor!
    public Manager(String name, int age, String email, double salary, String department, int teamSize, double budgetAuthority) {
        super(name, age, email, salary); // ← delegates to Employee constructor
        // After super(), we initialize Manager-specific fields
        if (teamSize < 0){
            throw new IllegalArgumentException("Team size cannot be negative");
        }
        this.department      = department;
        this.teamSize        = teamSize;
        this.budgetAuthority = budgetAuthority;
    }

    // ── USE 2 of super: Call parent method ────────────────────
    @Override
    public void displayInfo() {
        super.displayInfo(); // ← print Employee info first
        System.out.printf("  └─ Dept: %-15s | Team: %2d | Budget: $%,.2f%n", department, teamSize, budgetAuthority);
    }

    // ── USE 3 of super: Access parent field (rare — use getter)
    @Override
    public double calculateBonus() {
        // Managers get 15% + $1000 per team member
        return super.salary * 0.15 + (teamSize * 1000.0);
        // Or: return super.calculateBonus() * 3 + (teamSize * 1000.0);
    }

    @Override
    public void work() {
        System.out.println(name + " is managing team of " + teamSize);
    }

    // Manager-specific methods
    public void conductMeeting() {
        System.out.println(name + " is conducting a team meeting");
    }

    public void approveExpense(double amount) {
        if (amount > budgetAuthority) {
            System.out.printf("Expense $%.2f exceeds budget authority $%.2f%n",
                    amount, budgetAuthority);
        } else {
            System.out.printf("Expense $%.2f approved by %s%n", amount, name);
        }
    }
    // Getters
    public String getDepartment(){
        return department;
    }
    public int    getTeamSize(){
        return teamSize;
    }
    public double getBudgetAuthority(){
        return budgetAuthority;
    }

    @Override
    public String toString() {
        return String.format("Manager{name='%s', dept='%s', team=%d, salary=$%.2f}",
                name, department, teamSize, salary);
    }


}