import java.util.*;
public class HashSetDeep {
    public static void main(String[] args) {

        Set<String> set = new HashSet<>();

        // ── ADD — duplicates silently ignored ──────────────────
        set.add("Apple");
        set.add("Banana");
        set.add("Cherry");
        boolean added = set.add("Apple"); // duplicate!
        System.out.println(added);        // false — not added
        System.out.println(set.size());   // 3, not 4

        // ── CONTAINS — O(1) average ────────────────────────────
        System.out.println(set.contains("Banana")); // true
        System.out.println(set.contains("Mango"));  // false

        // ── REMOVE — O(1) average ──────────────────────────────
        set.remove("Cherry");

        // ── SET OPERATIONS — powerful! ─────────────────────────
        Set<Integer> setA = new HashSet<>(Set.of(1, 2, 3, 4, 5));
        Set<Integer> setB = new HashSet<>(Set.of(4, 5, 6, 7, 8));

        // Union — all elements from both
        Set<Integer> union = new HashSet<>(setA);
        union.addAll(setB);
        System.out.println("Union: " + union); // [1,2,3,4,5,6,7,8]

        // Intersection — only common elements
        Set<Integer> intersection = new HashSet<>(setA);
        intersection.retainAll(setB);
        System.out.println("Intersection: " + intersection); // [4,5]

        // Difference — in A but not B
        Set<Integer> difference = new HashSet<>(setA);
        difference.removeAll(setB);
        System.out.println("Difference: " + difference); // [1,2,3]

        // ── PRACTICAL: Remove duplicates from a list ───────────
        List<String> withDupes = new ArrayList<>(
            List.of("Alice","Bob","Alice","Charlie","Bob","Dave"));
        Set<String> unique = new LinkedHashSet<>(withDupes); // preserves order
        List<String> deduplicated = new ArrayList<>(unique);
        System.out.println(deduplicated); // [Alice, Bob, Charlie, Dave]
    }
}