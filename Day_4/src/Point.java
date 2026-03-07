//equals() — Value Equality Deep Theory
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // ── WITHOUT override — uses Object's equals() which is just ==
    // Point p1 = new Point(3, 4);
    // Point p2 = new Point(3, 4);
    // p1.equals(p2) → FALSE! Different objects in memory.

    // ── WITH proper override ────────────────────────────────────
    @Override
    public boolean equals(Object obj) {
        // Step 1: Same reference? Definitely equal
        if (this == obj) return true;

        // Step 2: Is it null? Never equal to null
        if (obj == null) return false;

        // Step 3: Same class? Can't be equal if different types
        if (getClass() != obj.getClass()) return false;

        // Step 4: Cast and compare fields
        Point other = (Point) obj;
        return this.x == other.x && this.y == other.y;
    }

    // ── hashCode CONTRACT: if a.equals(b) then a.hashCode() == b.hashCode()
    @Override
    public int hashCode() {
        return java.util.Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ")";
    }

    public static void main(String[] args) {
        Point p1 = new Point(3, 4);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(5, 6);

        // Reference equality
        System.out.println(p1 == p2);        // false — different objects
        System.out.println(p1 == p1);        // true  — same reference

        // Value equality (after override)
        System.out.println(p1.equals(p2));   // true  same x and y
        System.out.println(p1.equals(p3));   // false — different values
        System.out.println(p1.equals(null)); // false — safe null check

        // HashCode consistency
        System.out.println(p1.hashCode() == p2.hashCode()); // true
    }
}