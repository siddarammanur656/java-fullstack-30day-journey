// ── CHILD CLASS: Engineer
public class Engineer extends Employee{
    private String techStack;
    private int    experienceYears;
    private String level; // Junior, Mid, Senior, Principal

    public Engineer(String name, int age, String email, double salary, String techStack, int experienceYears) {
        super(name, age, email, salary);
        this.techStack = techStack;
        this.experienceYears = experienceYears;
        this.level = calculateLevel(experienceYears);
    }
    // Private helper — determines level from experience
    private String calculateLevel(int years) {
        if (years < 2)  return "Junior";
        if (years < 5)  return "Mid";
        if (years < 10) return "Senior";
        return "Principal";
    }
    @Override
    public double calculateBonus() {
        double base = super.salary * 0.10; // engineers get 10%
        // Senior+ gets extra multiplier
        return switch (level) {
            case "Senior"    -> base * 1.5;
            case "Principal" -> base * 2.0;
            default          -> base;
        };
    }

    @Override
    public void work() {
        System.out.println(name + " [" + level + "] is coding in " + techStack);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("  └─ Stack: %-15s | Exp: %2d yrs | Level: %s%n",
                techStack, experienceYears, level);
    }

    public void codeReview() {
        System.out.println(name + " is doing a code review");
    }

    // Getters
    public String getTechStack(){
        return techStack;
    }
    public int    getExperienceYears(){
        return experienceYears;
    }
    public String getLevel(){
        return level;
    }

    @Override
    public String toString() {
        return String.format("Engineer{name='%s', level='%s', stack='%s', salary=$%.2f}",
                name, level, techStack, salary);
    }

}