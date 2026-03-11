import java.util.*;
public class LinkedHashSetDemo {
    public static void main(String[] args) {

        // Same as HashSet BUT maintains insertion order
        Set<String> set = new LinkedHashSet<>();
        set.add("Banana");
        set.add("Apple");
        set.add("Cherry");
        set.add("Apple"); // duplicate ignored
        set.add("Mango");

        // Iteration preserves INSERTION order
        System.out.println(set); // [Banana, Apple, Cherry, Mango]
        // HashSet would give: [Banana, Cherry, Apple, Mango] (unpredictable)

        // Use case: tracking unique page visits IN ORDER
        Set<String> visitedPages = new LinkedHashSet<>();
        visitedPages.add("/home");
        visitedPages.add("/products");
        visitedPages.add("/home");      // revisit — ignored
        visitedPages.add("/checkout");
        System.out.println(visitedPages); // [/home, /products, /checkout]
    }
}