public class ThisKeyword {

    private String name;
    private int    score;

    // ── USE 1: Disambiguate field vs parameter (most common) ───
    public ThisKeyword(String name, int score) {
        this.name  = name;  // this.name = field; name = parameter
        this.score = score;
    }

    // ── USE 2: Call another method of the same object ──────────
    public void displayInfo() {
        System.out.println("Name: " + this.name); // 'this.' optional here
        this.printScore();                         // same as printScore()
    }

    private void printScore() {
        System.out.println("Score: " + this.score);
    }

    // ── USE 3: Call another constructor — this() ───────────────
    public ThisKeyword(String name) {
        this(name, 0); // calls the 2-param constructor above
    }

    public ThisKeyword() {
        this("Unknown"); // calls the 1-param constructor above
    }

    // ── USE 4: Return current object — enables method chaining ─
    public ThisKeyword setName(String name) {
        this.name = name;
        return this; // returns the CURRENT object
    }

    public ThisKeyword setScore(int score) {
        this.score = score;
        return this; // returns the CURRENT object
    }

    public static void main(String[] args) {
        // Method chaining — each setter returns 'this' so you can chain calls
        ThisKeyword obj = new ThisKeyword()
                .setName("Alice")   // returns 'this'
                .setScore(95);      // called on returned 'this'

        obj.displayInfo();
        // Name: Alice
        // Score: 95
    }
}