public class RuntimePolymorphism {

    public static void main(String[] args) {

        // ── THE KEY INSIGHT ────────────────────────────────────
        // Reference type = Employee (what the compiler sees)
        // Object type    = Manager  (what actually exists at runtime)
        //     ↓                  ↓
        Employee emp = new Manager("Alice", 35, "a@co.com",
                                   90000, "Engineering", 8, 500000);

        // Compiler checks: does Employee have work()? YES ✅
        // Runtime decides: what is the actual object? Manager
        //   → calls Manager's work(), NOT Employee's work()
        emp.work(); // "Alice is managing team of 8" ← Manager's version!

        // ── ARRAY OF EMPLOYEES — different actual types ────────
        Employee[] team = {
            new Manager ("Alice", 35, "a@co.com", 90000, "Eng", 8, 500000),
            new Engineer("Bob",   28, "b@co.com", 85000, "Java/Spring", 5),
            new Intern  ("Carol", 22, "c@co.com", 30000, "MIT", 6, "Alice"),
            new Engineer("Dave",  32, "d@co.com", 95000, "React/Node", 9),
            new Manager ("Eve",   42, "e@co.com", 120000,"Sales", 15, 1000000),
        };

        System.out.println("═".repeat(60));
        System.out.println("  TEAM WORK LOG");
        System.out.println("═".repeat(60));

        // Same call — different behavior for each type at runtime
        for (Employee e : team) {
            e.work();           // polymorphic! each calls its OWN version
        }

        System.out.println("\n  BONUS CALCULATIONS");
        System.out.println("═".repeat(60));

        double totalBonus = 0;
        for (Employee e : team) {
            double bonus = e.calculateBonus(); // polymorphic!
            System.out.printf("  %-12s → $%,.2f bonus%n", e.getName(), bonus);
            totalBonus += bonus;
        }
        System.out.printf("  Total bonus payout: $%,.2f%n", totalBonus);
    }
}