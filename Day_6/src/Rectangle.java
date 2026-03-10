// ── CLASS: Rectangle implementing multiple interfaces ──────────
public class Rectangle implements Drawable, Resizable, Saveable {
    private double width;
    private double height;
    private String color;

    public Rectangle(double width, double height, String color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    // Drawable methods
    @Override
    public void draw() {
        System.out.printf("Drawing a %s rectangle (%.1f x %.1f)%n", color, width, height);
    }

    @Override
    public void resize(double factor) {
        width *= factor;
        height *= factor;
        System.out.printf("Resized rectangle to (%.1f x %.1f)%n", width, height);
    }

    @Override
    public String getDescription() {
        return String.format("Rectangle [%s, %.1f x %.1f]", color, width, height);
    }

    // Resizable methods
    @Override
    public double getWidth() { return width; }
    @Override
    public double getHeight() { return height; }

    // Saveable methods
    @Override
    public void save(String filename) {
        System.out.printf("Saving rectangle to %s%s%n", filename, getDefaultExtension());
    }

    @Override
    public void load(String filename) {
        System.out.printf("Loading rectangle from %s%n", filename);
    }
}