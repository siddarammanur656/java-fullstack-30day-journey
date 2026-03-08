package Project_shapesystem;

// ── Triangle
class Triangle extends Shape {
    private final double sideA;
    private final double sideB;
    private final double sideC;

    public Triangle(double a, double b, double c, String color, boolean filled) {
        super(color, filled);
        if (a <= 0 || b <= 0 || c <= 0)
            throw new IllegalArgumentException("Sides must be positive");
        if (a + b <= c || a + c <= b || b + c <= a)
            throw new IllegalArgumentException("Invalid triangle sides");
        this.sideA = a;
        this.sideB = b;
        this.sideC = c;
    }

    @Override
    public double getArea() {
        double s = getPerimeter() / 2; // semi-perimeter
        return Math.sqrt(s * (s-sideA) * (s-sideB) * (s-sideC)); // Heron's formula
    }

    @Override
    public double getPerimeter() {
        return sideA + sideB + sideC;
    }
    @Override
    public String getShapeType() {
        return "Triangle";
    }

    @Override
    public void draw() {
        System.out.printf("  Drawing Triangle (%.0f,%.0f,%.0f) in %s:%n",
                           sideA, sideB, sideC, color);
        int h = 6;
        for (int i = 1; i <= h; i++) {
            int spaces = h - i;
            int stars  = 2 * i - 1;
            System.out.print(" ".repeat(spaces));
            if (i == h) {
                System.out.print("*".repeat(stars));
            } else if (filled) {
                System.out.print("*".repeat(stars));
            } else {
                System.out.print("*");
                if (stars > 1) {
                    System.out.print(" ".repeat(stars - 2));
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }

    public String classify() {
        if (sideA == sideB && sideB == sideC) {
            return "Equilateral";
        }
        if (sideA == sideB || sideB == sideC || sideA == sideC) {
            return "Isosceles";
        }
        return "Scalene";
    }
}