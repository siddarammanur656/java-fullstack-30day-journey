// Multiple interfaces — a class that CAN DO many things
public class SmartDevice implements Drawable, Resizable, Saveable, Comparable<SmartDevice> {

    private String name;
    private double width;
    private double height;
    private String color;

    public SmartDevice(String name, double width, double height, String color) {
        this.name   = name;
        this.width  = width;
        this.height = height;
        this.color  = color;
    }

    // Implement Drawable
    @Override
    public void draw() {
        System.out.printf("  📱 Drawing %s (%.0fx%.0f) in %s%n", name, width, height, color);
    }

    @Override
    public String getDescription() {
        return String.format("SmartDevice: %s (%.0fx%.0f)", name, width, height);
    }

    // Implement Resizable
    @Override
    public void   resize(double factor) {
        width *= factor; height *= factor;
    }
    @Override
    public double getWidth()  {
        return width;
    }
    @Override
    public double getHeight() {
        return height;
    }

    // Implement Saveable
    @Override
    public void save(String filename) {
        System.out.println("   Saving " + name + " to " + filename);
    }
    @Override
    public void load(String filename) {
        System.out.println("   Loading from " + filename);
    }

    // Implement Comparable — enables sorting
    @Override
    public int compareTo(SmartDevice other) {
        return Double.compare(this.width * this.height,
                              other.width * other.height);
    }

    public String getName() {
        return name;
    }
}