//Comparable — Natural Order

public class Product implements Comparable<Product> {
    private String name;
    private double price;
    private int rating;

    public Product(String name, double price, int rating) {
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    // Natural order: sort by price ascending
    @Override
    public int compareTo(Product other) {
        return Double.compare(this.price, other.price);
    }

    @Override
    public String toString() {
        return String.format("%s (₹%.2f, Rating:%d)", name, price, rating);
    }

    // Getters
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getRating() { return rating; }
}