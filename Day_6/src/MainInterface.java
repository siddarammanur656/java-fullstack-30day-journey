// ── MAIN DEMO ──────────────────────────────────────────────────
public class MainInterface {
    public static void main(String[] args) {
        Rectangle rect = new Rectangle(10, 5, "Blue");

        // Using Drawable
        rect.draw();
        rect.drawWithBorder();
        rect.drawAt(100, 200);

        // Using Resizable
        rect.resize(2.0);
        System.out.println("Aspect ratio: " + rect.getAspectRatio());
        System.out.println("Is landscape? " + rect.isLandscape());

        // Using Saveable
        rect.save("myRectangle");
        rect.load("myRectangle.dat");

        // Using static method from interface
        Drawable defaultShape = Drawable.createDefault();
        defaultShape.draw();
        System.out.println(defaultShape.getDescription());
    }
}