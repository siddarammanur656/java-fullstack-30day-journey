package Project_shapesystem;
//Circle
class Circle extends Shape {

    private final double radius;

    public Circle(double radius, String color, boolean filled) {
        super(color, filled);
        if (radius <= 0)
            throw new IllegalArgumentException("Radius must be positive");
        this.radius = radius;
    }

    //implementing abstract methods in child class
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String getShapeType() {
        return "Circle";
    }

    @Override
    public void draw() {
        String fill = filled ? "●" : "○";
        System.out.printf("  Drawing %s Circle (r=%.1f) in %s%n", filled ? "filled" : "hollow", radius, color);
        // Simple ASCII art
        int r = (int) Math.min(radius, 5);
        for (int y = -r; y <= r; y++) {
            for (int x = -r * 2; x <= r * 2; x++) {
                double dist = Math.sqrt((double) (x * x) / 4 + y * y);
                if (filled) System.out.print(dist <= r ? fill : " ");
                else System.out.print(Math.abs(dist - r) < 0.8 ? fill : " ");
            }
            System.out.println();
        }
    }
    public double getRadius(){
        return radius;
    }
    public double getDiameter(){
        return radius * 2;
    }
}