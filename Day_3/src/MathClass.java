public class MathClass {
    public static void main(String[] args) {

        // ── ROUNDING ──────────────────────────────────────────
        System.out.println(Math.round(3.5));    // 4  (rounds half up)
        System.out.println(Math.round(3.4));    // 3
        System.out.println(Math.floor(3.9));    // 3.0 (always rounds DOWN)
        System.out.println(Math.ceil(3.1));     // 4.0 (always rounds UP)

        // ── POWER & ROOTS ─────────────────────────────────────
        System.out.println(Math.pow(2, 8));     // 256.0
        System.out.println(Math.sqrt(144));     // 12.0
        System.out.println(Math.cbrt(27));      // 3.0 (cube root)

        // ── ABSOLUTE VALUE ────────────────────────────────────
        System.out.println(Math.abs(-42));      // 42
        System.out.println(Math.abs(-3.14));    // 3.14

        // ── MIN / MAX ─────────────────────────────────────────
        System.out.println(Math.max(10, 20));   // 20
        System.out.println(Math.min(10, 20));   // 10

        // ── LOGARITHMS ────────────────────────────────────────
        System.out.println(Math.log(Math.E));   // 1.0 (natural log)
        System.out.println(Math.log10(1000));   // 3.0

        // ── TRIGONOMETRY (in radians) ─────────────────────────
        System.out.println(Math.sin(Math.PI / 2)); // 1.0
        System.out.println(Math.cos(0));            // 1.0
        System.out.println(Math.toRadians(180));    // π ≈ 3.14159
        System.out.println(Math.toDegrees(Math.PI));// 180.0

        // ── CONSTANTS ─────────────────────────────────────────
        System.out.println(Math.PI);    // 3.141592653589793
        System.out.println(Math.E);     // 2.718281828459045

        // ── RANDOM ────────────────────────────────────────────
        // Math.random() returns [0.0, 1.0)
        double rand = Math.random();              // e.g. 0.7342
        int dice = (int)(Math.random() * 6) + 1; // 1 to 6
        int range = (int)(Math.random() * 91) + 10; // 10 to 100
        System.out.println("Dice: " + dice);
        System.out.println("Range 10-100: " + range);

        // ── USEFUL COMBOS ─────────────────────────────────────
        // Hypotenuse of right triangle (Pythagorean theorem)
        double a = 3, b = 4;
        double hypotenuse = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
        System.out.println("Hypotenuse: " + hypotenuse); // 5.0

        // Clamp a value between min and max
        int value = 150, min = 0, max = 100;
        int clamped = Math.max(min, Math.min(max, value));
        System.out.println("Clamped: " + clamped); // 100
    }
}