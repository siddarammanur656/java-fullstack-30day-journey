public class Product {
    private String name;
    private double price;
    private int    stock;
    private String category;

    // Most complete constructor — all logic lives HERE
    public Product(String name, double price, int stock, String category) {
        if (price < 0)  throw new IllegalArgumentException("Price cannot be negative");
        if (stock < 0)  throw new IllegalArgumentException("Stock cannot be negative");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name required");

        this.name     = name;
        this.price    = price;
        this.stock    = stock;
        this.category = category;
    }

    // Chains to full constructor with default category
    public Product(String name, double price, int stock) {
        this(name, price, stock, "General"); // calls 4-param constructor
    }

    // Chains to above with default stock
    public Product(String name, double price) {
        this(name, price, 0); // calls 3-param constructor
    }

    // Chains to above with default price
    public Product(String name) {
        this(name, 0.0); // calls 2-param constructor
    }

    @Override
    public String toString() {
        return String.format("[%s] %s — $%.2f (stock: %d)",
                              category, name, price, stock);
    }

    public static void main(String[] args) {
        Product p1 = new Product("Laptop", 999.99, 50, "Electronics");
        Product p2 = new Product("Mouse", 29.99, 100);
        Product p3 = new Product("Keyboard", 79.99);
        Product p4 = new Product("Unknown");

        System.out.println(p1); // [Electronics] Laptop — $999.99 (stock: 50)
        System.out.println(p2); // [General] Mouse — $29.99 (stock: 100)
        System.out.println(p3); // [General] Keyboard — $79.99 (stock: 0)
        System.out.println(p4); // [General] Unknown — $0.00 (stock: 0)
    }
}