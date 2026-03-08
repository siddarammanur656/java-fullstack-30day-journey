// WITHOUT inheritance — massive duplication
class Manager1 {
    private String name;       // ← same
    private int age;           // ← same
    private String email;      // ← same
    private double salary;     // ← same
    private String department; // ← Manager-specific
    private int teamSize;      // ← Manager-specific
    // all getters, setters, toString duplicated!
}

class Engineer1 {
    private String name;       // ← same
    private int age;           // ← same
    private String email;      // ← same
    private double salary;     // ← same
    private String techStack;  // ← Engineer-specific
    private int level;         // ← Engineer-specific
    // all getters, setters, toString duplicated!
}

class Intern1 {
    private String name;       // ← same
    private int age;           // ← same
    private String email;      // ← same
    private double salary;     // ← same
    private String university; // ← Intern-specific
    // all getters, setters, toString duplicated!
}