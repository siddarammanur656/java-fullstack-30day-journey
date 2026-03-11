import java.util.*;

public class LinkedHashMapDeep {
    public static void main(String[] args) {
        // Default: insertion order
        Map<String, Integer> scores = new LinkedHashMap<>();
        scores.put("Alice", 95);
        scores.put("Bob", 82);
        scores.put("Charlie", 78);
        scores.put("Dave", 88);

        System.out.println(scores); 
        // {Alice=95, Bob=82, Charlie=78, Dave=88}

        // Access-order mode (true)
        Map<String, Integer> cache = new LinkedHashMap<>(16, 0.75f, true);
        cache.put("A", 1);
        cache.put("B", 2);
        cache.put("C", 3);
        cache.put("D",4);
        System.out.println(cache);

        cache.get("A"); // Access A → moves to end
        System.out.println(cache); 
        // {B=2, C=3, A=1}
        cache.get("B");
        System.out.println(cache);
    }
}