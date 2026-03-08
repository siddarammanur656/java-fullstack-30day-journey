package Project_shapesystem;
//Rectangle
class Rectangle extends Shape{
    protected double width;
    protected double height;

    public Rectangle(double width, double height, String color, boolean filled) {
        super(color, filled);
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("Dimensions must be positive");
        this.width  = width;
        this.height = height;
    }

    @Override
    public double getArea(){
        return width * height;
    }
    @Override
    public double getPerimeter(){
        return 2 * (width + height);
    }
    @Override
    public String getShapeType(){
        return "Rectangle";
    }

    @Override
    public void draw() {
        String fill = filled ? "█" : " ";
        String edge = "■";
        System.out.printf("  Drawing Rectangle (%.0fx%.0f) in %s:%n",
                width, height, color);
        int w = (int) Math.min(width, 20);
        int h = (int) Math.min(height, 8);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i == 0 || i == h-1 || j == 0 || j == w-1) System.out.print(edge);
                else System.out.print(filled ? fill : " ");
            }
            System.out.println();
        }
    }
    public boolean isSquare(){
        return width == height;
    }
    public double  getWidth(){
        return width;
    }
    public double  getHeight() {
        return height;
    }
}