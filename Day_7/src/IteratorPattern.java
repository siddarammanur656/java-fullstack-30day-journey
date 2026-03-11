import java.util.*;
public class IteratorPattern {
    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>(
            List.of(1,2,3,4,5,6,7,8,9,10));

        // ── PROBLEM: ConcurrentModificationException ───────────
        // NEVER modify a collection while iterating with for-each!
        // for (int n : numbers) {
        //     if (n % 2 == 0) numbers.remove(n); // ← CRASH!
        // }

        // ── SOLUTION 1: Iterator.remove() — safe ───────────────
        Iterator<Integer> it = numbers.iterator();
        while (it.hasNext()) {
            int n = it.next();
            if (n % 2 == 0) it.remove(); // removes current element SAFELY
        }
        System.out.println(numbers); // [1,3,5,7,9]

        // ── SOLUTION 2: removeIf (Java 8+) — cleanest ─────────
        List<Integer> nums2 = new ArrayList<>(
            List.of(1,2,3,4,5,6,7,8,9,10));
        nums2.removeIf(n -> n % 2 == 0);
        System.out.println(nums2); // [1,3,5,7,9]

        // ── SOLUTION 3: Collect to new list ────────────────────
        List<Integer> nums3 = new ArrayList<>(
            List.of(1,2,3,4,5,6,7,8,9,10));
        List<Integer> evens = new ArrayList<>();
        for (int n : nums3) {
            if (n % 2 == 0) evens.add(n);
        }
        nums3.removeAll(evens);
        System.out.println(nums3); // [1,3,5,7,9]

        // ── ListIterator — bidirectional + modify ──────────────
        List<String> words = new ArrayList<>(
            List.of("hello","world","java","collections"));
        ListIterator<String> lit = words.listIterator();
        while (lit.hasNext()) {
            String word = lit.next();
            lit.set(word.toUpperCase()); // replace current with uppercase
        }
        System.out.println(words); // [HELLO, WORLD, JAVA, COLLECTIONS]
    }
}