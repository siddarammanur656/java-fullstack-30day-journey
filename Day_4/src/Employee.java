//Getters & Setters — With Real Validation
public class Employee {

    // All fields private — nobody touches them directly
    private String name;
    private double salary;
    private int    age;
    private String email;
    private String department;

    public Employee(String name, double salary, int age,
                    String email, String department) {
        // Use setters in constructor — validation runs automatically!
        setName(name);
        setSalary(salary);
        setAge(age);
        setEmail(email);
        setDepartment(department);
    }

    // ── GETTERS — read access ──────────────────────────────────
    public String getName()       { return name; }
    public double getSalary()     { return salary; }
    public int    getAge()        { return age; }
    public String getEmail()      { return email; }
    public String getDepartment() { return department; }

    // ── COMPUTED GETTER — derived value, no stored field needed
    public double getMonthlyPay() { return salary / 12; }
    public double getYearlyBonus(){ return salary * 0.1; }

    // ── SETTERS — write access with validation ─────────────────
    public void setName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be empty");
        this.name = name.trim();
    }

    public void setSalary(double salary) {
        if (salary < 0)
            throw new IllegalArgumentException("Salary cannot be negative");
        if (salary > 10_000_000)
            throw new IllegalArgumentException("Salary exceeds maximum allowed");
        this.salary = salary;
    }

    public void setAge(int age) {
        if (age < 18 || age > 100)
            throw new IllegalArgumentException("Age must be between 18 and 100");
        this.age = age;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@"))
            throw new IllegalArgumentException("Invalid email address");
        this.email = email.toLowerCase().trim();
    }

    public void setDepartment(String department) {
        if (department == null || department.isBlank())
            throw new IllegalArgumentException("Department required");
        this.department = department;
    }

    // Raise salary by percentage — business logic belongs here
    public void giveRaise(double percent) {
        if (percent <= 0 || percent > 100)
            throw new IllegalArgumentException("Raise must be between 0% and 100%");
        this.salary *= (1 + percent / 100);
    }

    @Override
    public String toString() {
        return String.format(
                "Employee{name='%s', dept='%s', age=%d, salary=$%.2f}",
                name, department, age, salary);
    }

    public static void main(String[] args) {
        Employee emp = new Employee("Alice Smith", 75000,
                28, "alice@company.com", "Engineering");
        System.out.println(emp);
        System.out.printf("Monthly pay: $%.2f%n", emp.getMonthlyPay());

        emp.giveRaise(10);
        System.out.printf("After 10%% raise: $%.2f%n", emp.getSalary());

        // Try invalid operations
        try {
            emp.setSalary(-1000); // ← blocked by validation
        } catch (IllegalArgumentException e) {
            System.out.println("Blocked: " + e.getMessage());
        }

        try {
            emp.setAge(15); // ← blocked
        } catch (IllegalArgumentException e) {
            System.out.println("Blocked: " + e.getMessage());
        }
    }
}