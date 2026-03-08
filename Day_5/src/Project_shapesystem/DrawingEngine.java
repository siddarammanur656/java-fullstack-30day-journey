package Project_shapesystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// ── Drawing Engine — Polymorphic
class DrawingEngine {

    private final List<Shape> canvas = new ArrayList<>();

    public void addShape(Shape s) {
        canvas.add(s);
    }
    public void removeShape(int id) {
        canvas.removeIf(s -> s.getShapeId() == id);
    }
    // Polymorphic — calls each shape's own draw()
    public void drawAll() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("  DRAWING CANVAS (" + canvas.size() + " shapes)");
        System.out.println("═".repeat(60));
        for (Shape s : canvas) {
            s.draw();
            System.out.println();
        }
    }
    // Statistics — all polymorphic method calls
    public void printStats() {
        if (canvas.isEmpty()) {
            System.out.println("Canvas is empty");
            return;
        }

        double totalArea      = 0;
        double totalPerimeter = 0;
        Shape  largest        = canvas.get(0);
        Shape  smallest       = canvas.get(0);

        for (Shape s : canvas) {
            totalArea      += s.getArea();
            totalPerimeter += s.getPerimeter();
            if (s.getArea() > largest.getArea())   largest  = s;
            if (s.getArea() < smallest.getArea())  smallest = s;
        }

        System.out.println("\n" + "═".repeat(60));
        System.out.println("  CANVAS STATISTICS");
        System.out.println("═".repeat(60));
        canvas.forEach(Shape::printSummary); // method reference!
        System.out.println("─".repeat(60));
        System.out.printf("  Total Shapes:     %d%n",    canvas.size());
        System.out.printf("  Total Area:       %.2f%n",  totalArea);
        System.out.printf("  Total Perimeter:  %.2f%n",  totalPerimeter);
        System.out.printf("  Average Area:     %.2f%n",  totalArea / canvas.size());
        System.out.printf("  Largest Shape:    %s%n",    largest);
        System.out.printf("  Smallest Shape:   %s%n",    smallest);

        // Count by type using instanceof + pattern matching
        long circles   = canvas.stream().filter(s -> s instanceof Circle).count();
        long rects     = canvas.stream().filter(s -> s instanceof Rectangle && !(s instanceof Square)).count();
        long squares   = canvas.stream().filter(s -> s instanceof Square).count();
        long triangles = canvas.stream().filter(s -> s instanceof Triangle).count();

        System.out.println("─".repeat(60));
        System.out.printf("  Circles: %d | Rectangles: %d | Squares: %d | Triangles: %d%n", circles, rects, squares, triangles);

        // Sorted by area
        System.out.println("\n  SHAPES BY AREA (ascending):");
        canvas.stream()
                .sorted(Comparator.comparingDouble(Shape::getArea))
                .forEach(s -> System.out.printf("    %-12s → %.2f%n",
                        s.getShapeType(), s.getArea()));
        System.out.println("═".repeat(60));
    }
}