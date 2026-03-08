package Project_shapesystem;

// ── Main
public class ShapeSystem {
    public static void main(String[] args) {

        DrawingEngine engine = new DrawingEngine();

        // Add shapes — all stored as Shape references (upcasting)
        engine.addShape(new Circle   (5.0,        "Red",    true));
        engine.addShape(new Circle   (3.0,        "Blue",   false));
        engine.addShape(new Rectangle(8.0, 4.0,   "Green",  true));
        engine.addShape(new Square   (5.0,        "Purple", false));
        engine.addShape(new Triangle (3.0,3.0,3.0,"Orange", true));
        engine.addShape(new Triangle (5.0,4.0,3.0,"Yellow", false));
        engine.addShape(new Square   (3.0,        "Pink",   true));

        // Draw all shapes — polymorphic draw() calls
        engine.drawAll();

        // Statistics
        engine.printStats();

        System.out.println("\n  Total shapes ever created: " + Shape.getTotalShapes());

        // Demonstrate instanceof + downcast
        System.out.println("\n  CIRCLE DETAILS:");
        // Direct use of specific methods after downcast
        Shape s = new Circle(7.0, "Gold", true);
        if (s instanceof Circle c) {
            System.out.printf("  Radius: %.1f | Diameter: %.1f | Area: %.2f%n", c.getRadius(), c.getDiameter(), c.getArea());
        }

        // Demonstrate final
        Circle fc = new Circle(4.0, "Silver", false);
        System.out.printf("%n  Final circle area: %.4f%n", fc.getArea());
    }
}