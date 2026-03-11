import java.util.*;

public class TreeMapDeep {
    public static void main(String[] args) {
        // Natural order (alphabetical for Strings)
        TreeMap<String, Integer> scores = new TreeMap<>();
        scores.put("Charlie", 78);
        scores.put("Alice", 95);
        scores.put("Bob", 82);
        scores.put("Dave", 88);

        System.out.println(scores);
        // {Alice=95, Bob=82, Charlie=78, Dave=88}

        // Navigation methods
        System.out.println(scores.firstKey());   // Alice
        System.out.println(scores.lastKey());    // Dave
        System.out.println(scores.lowerKey("Charlie"));   // Bob
        System.out.println(scores.higherKey("Charlie"));  // Dave
        System.out.println(scores.floorKey("Carol"));     // Charlie
        System.out.println(scores.ceilingKey("Carol"));   // Dave

        // Submaps
        System.out.println(scores.headMap("Charlie")); // {Alice=95, Bob=82}
        System.out.println(scores.tailMap("Bob"));     // {Bob=82, Charlie=78, Dave=88}
        System.out.println(scores.subMap("Bob", "Dave")); // {Bob=82, Charlie=78}

        // Custom comparator (reverse order)
        TreeMap<Integer, String> reverseMap = new TreeMap<>(Comparator.reverseOrder());
        reverseMap.put(1, "One");
        reverseMap.put(2, "Two");
        reverseMap.put(3, "Three");
        System.out.println(reverseMap); // {3=Three, 2=Two, 1=One}


        // Real use case: frequency map sorted by key
        TreeMap<Character, Integer> charFreq = new TreeMap<>();
        for (char c : "programming".toCharArray()) {
            charFreq.merge(c, 1, Integer::sum);
        }
        charFreq.forEach((ch, freq) ->
                System.out.printf("  '%c': %d%n", ch, freq));
        // outputs characters in alphabetical order
    }
}