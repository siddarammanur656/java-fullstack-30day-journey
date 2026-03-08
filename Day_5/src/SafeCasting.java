public class SafeCasting {
    public static void main(String[] args) {
        Employee emp = new Engineer("Bob", 28, "b@co.com", 85000, "Java", 5);

        // ❌ Dangerous — will crash at runtime
        try {
            Manager mgr = (Manager) emp; // ClassCastException! emp is Engineer
        } catch (ClassCastException e) {
            System.out.println("Crash: " + e.getMessage());
        }

        //  Safe — always check before casting
        if (emp instanceof Manager) {
            Manager mgr = (Manager) emp;
            mgr.conductMeeting();
        } else {
            System.out.println(emp.getName() + " is not a Manager");
        }

        //  Even cleaner with pattern matching
        if (emp instanceof Manager mgr) {
            mgr.conductMeeting();
        } else {
            System.out.println("Not a manager — no meeting");
        }
    }
}