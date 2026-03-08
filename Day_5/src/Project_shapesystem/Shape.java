package Project_shapesystem;


//Abstract class :Shape
public abstract class Shape{

    protected String color;
    protected boolean filled;
    private   static int totalShapes = 0;
    private   final  int shapeId;


    public Shape(String color, boolean filled) {
        if (color == null || color.isBlank())
            throw new IllegalArgumentException("Color required");
        this.color  = color;
        this.filled = filled;
        this.shapeId = ++totalShapes;
    }

    //Abstract methods — MUST be implemented by every subclass
    public abstract double getArea();
    public abstract double getPerimeter();
    public abstract String getShapeType();
    public abstract void   draw();

    //  Concrete methods — shared by all shapes
    public String getColor(){
        return color;
    }
    public boolean isFilled(){
        return filled;
    }
    public int getShapeId(){
        return shapeId;
    }

    //Setter
    public void setColor(String color) {
        if (color == null || color.isBlank())
            throw new IllegalArgumentException("Color required");
        this.color = color;
    }

    //Getter
    public static int getTotalShapes() {
        return totalShapes;
    }

    public void printSummary() {
        System.out.printf(
                    "  [#%03d] %-12s | " +   // Shape ID and type
                        "Color: %-10s | " +      // Shape color
                        "Filled: %-5b | " +      // Filled status
                        "Area: %8.2f | " +       // Shape area
                        "Perimeter: %8.2f%n",    // Shape perimeter
                shapeId,
                getShapeType(),
                color,
                filled,
                getArea(),
                getPerimeter()
        );
    }
    @Override
    public String toString() {
        return String.format("%s{id=%d, color='%s', area=%.2f}", getShapeType(), shapeId, color, getArea());
    }

}