public class InstanceOfDemo {

    static void processEmployee(Employee emp) {
        System.out.print(emp.getName() + ": ");

        // Old style — check then cast (two operations)
        if (emp instanceof Manager) {
            Manager mgr = (Manager) emp;  // must cast explicitly
            System.out.println("Manager of " + mgr.getDepartment()
                             + " with team " + mgr.getTeamSize());
            mgr.conductMeeting();

        } else if (emp instanceof Engineer) {
            Engineer eng = (Engineer) emp;
            System.out.println("Engineer (" + eng.getLevel()
                             + ") working in " + eng.getTechStack());
            eng.codeReview();

        } else if (emp instanceof Intern) {
            Intern intern = (Intern) emp;
            System.out.println("Intern from " + intern.getUniversity()
                             + " mentored by " + intern.getMentor());

        } else {
            System.out.println("Generic employee");
        }
    }

    // PATTERN MATCHING (Java 16+) — cleaner
    static void processEmployeeModern(Employee emp) {
        System.out.print(emp.getName() + ": ");

        // instanceof + cast + variable declaration in ONE expression!
        if (emp instanceof Manager mgr) {
            System.out.println("Manager of " + mgr.getDepartment());
            mgr.conductMeeting();

        } else if (emp instanceof Engineer eng) {
            System.out.println(eng.getLevel() + " Engineer — " + eng.getTechStack());

        } else if (emp instanceof Intern intern) {
            System.out.println("Intern, mentor: " + intern.getMentor());
        }
    }

    public static void main(String[] args) {
        Employee[] team = {
            new Manager ("Alice", 35, "a@co.com", 90000, "Eng", 8, 500000),
            new Engineer("Bob",   28, "b@co.com", 85000, "Java", 5),
            new Intern  ("Carol", 22, "c@co.com", 30000, "MIT", 6, "Alice"),
        };

        System.out.println("── Old style ──");
        for (Employee e : team){
            processEmployee(e);
        }

        System.out.println("\n── Pattern matching ──");
        for (Employee e : team) {
            processEmployeeModern(e);
        }
    }
}