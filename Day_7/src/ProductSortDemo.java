import java.util.*;
//2. Comparator — Custom Orderings
public class ProductSortDemo {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>(List.of(
            new Product("Laptop", 75000, 5),
            new Product("Phone", 30000, 4),
            new Product("Tablet", 20000, 3),
            new Product("Monitor", 15000, 4)
        ));

        // ── NATURAL ORDER (Comparable) ───────────────
        Collections.sort(products); // by price ascending
        System.out.println("By price (natural order):");
        products.forEach(System.out::println);

        // ── COMPARATOR: by name ─────────────────────
        products.sort(Comparator.comparing(Product::getName));
        System.out.println("\nBy name:");
        products.forEach(System.out::println);

        // ── COMPARATOR: by rating descending ────────
        products.sort(Comparator.comparingInt(Product::getRating).reversed());
        System.out.println("\nBy rating (desc):");
        products.forEach(System.out::println);

        // ── CHAINED COMPARATOR: rating desc, then price asc ─
        Comparator<Product> byRatingThenPrice =
            Comparator.comparingInt(Product::getRating).reversed()
                      .thenComparingDouble(Product::getPrice);

        products.sort(byRatingThenPrice);
        System.out.println("\nBy rating desc, then price asc:");
        products.forEach(System.out::println);
    }
}