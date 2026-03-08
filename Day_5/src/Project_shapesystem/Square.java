package Project_shapesystem;

// ── Square (extends Rectangle)
class Square extends Rectangle {

    public Square(double side, String color, boolean filled) {
        super(side, side, color, filled); // width == height
    }

    @Override
    public String getShapeType() {
        return "Square";
    }

    public double getSide() {
        return width;
    }

    @Override
    public void draw() {
        System.out.printf("  Drawing Square (side=%.0f) in %s:%n", width, color);
        int s = (int) Math.min(width, 10);
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                if (i==0 || i==s-1 || j==0 || j==s-1) System.out.print("■");
                else System.out.print(filled ? "█" : " ");
            }
            System.out.println();
        }
    }
}
