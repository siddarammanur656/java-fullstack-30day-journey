// interface: Drawable
public interface Drawable {

    // Constants (implicitly public static final)
    int MAX_SIZE = 1000;
    String DEFAULT_COLOR = "Black";

    // Abstract methods (implicitly public abstract)
    void draw();
    void resize(double factor);
    String getDescription();

    // Default methods (Java 8+)
    default void drawWithBorder() {
        System.out.println("Drawing border...");
        draw(); // calls implementor’s version
        System.out.println("Border drawn.");
    }

    default void drawAt(int x, int y) {
        System.out.printf("Positioning at (%d,%d)...%n", x, y);
        draw();
    }

    // Static method (Java 8+)
    static Drawable createDefault() {
        return new Drawable() {
            public void draw() {
                System.out.println("Default shape");
            }
            public void resize(double f) {
                System.out.println("Resizing");
            }
            public String getDescription() {
                return "Default Drawable";
            }
        };
    }

    // Private method (Java 9+) — used internally
    private void logDraw(String action) {
        System.out.println("[LOG] " + action + " at " + System.currentTimeMillis());
    }
}