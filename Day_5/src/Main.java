public class Main {
    public static void main(String[] args) {
        // ── Create objects
        Manager m1 = new Manager("Alice", 40, "alice@company.com", 120000, "IT", 5, 50000);
        Engineer e1 = new Engineer("Bob", 28, "bob@company.com", 90000, "Java, Spring", 6);
        Intern i1 = new Intern("Charlie", 21, "charlie@uni.edu", 20000, "MIT", 6, "Alice");

        // ── Demonstrate polymorphism
        Employee[] employees = { m1, e1, i1 };

        for (Employee emp : employees) {
            System.out.println("--------------------------------------------------");
            emp.displayInfo();          // overridden in each subclass
            emp.work();                 // overridden in each subclass
            System.out.printf("Bonus: $%,.2f%n", emp.calculateBonus());
            System.out.println(emp);    // calls toString()
        }

        System.out.println("==================================================");

        // ── Manager-specific methods ─────────────────────
        m1.conductMeeting();
        m1.approveExpense(30000);
        m1.approveExpense(60000); // exceeds budget authority

        // ── Engineer-specific methods ────────────────────
        e1.codeReview();

        // ── Intern-specific methods ──────────────────────
        System.out.println(i1.getName() + " is mentored by " + i1.getMentor());
    }
}