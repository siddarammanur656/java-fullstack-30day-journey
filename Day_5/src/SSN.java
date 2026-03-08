// ── FINAL CLASS — cannot be extended ──────────────────────────
public final class SSN {  // Social Security Number — no subclassing allowed
    private final String value;
    public SSN(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
// class ExtendedSSN extends SSN { } ← COMPILE ERROR

// ── FINAL METHOD — cannot be overridden
//public class Account {
//    public final String getAccountType() {
//        return "STANDARD";  // no subclass can change this behavior
//    }
//    // Subclasses can still CALL this method, just not override it
//}

// ── FINAL FIELD — value set once, never changed
//public class Circle {
//    public static final double PI = 3.14159265358979; // constant
//    private final double radius;                       // set in constructor, never changes
//
//    public Circle(double radius) {
//        if (radius <= 0)
//            throw new IllegalArgumentException("Radius must be positive");
//        this.radius = radius; // set once here
//        // this.radius = 5; ← COMPILE ERROR if tried again
//    }
//
//    public double getArea()      {
//        return PI * radius * radius;
//    }
//    public double getPerimeter() {
//        return 2 * PI * radius;
//    }
//}