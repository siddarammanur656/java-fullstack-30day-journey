import java.util.*;
public class HashMapDeep {
    public static void main(String[] args) {

        Map<String, Integer> scores = new HashMap<>();

        // ── PUT
        scores.put("Alice",   95);
        scores.put("Bob",     82);
        scores.put("Charlie", 78);
        scores.put("Alice",   99); // KEY already exists → REPLACES value
        System.out.println(scores); // {Alice=99, Bob=82, Charlie=78}

        // ── GET
        System.out.println(scores.get("Bob"));      // 82
        System.out.println(scores.get("Dave"));     // null — key not found!
        System.out.println(scores.getOrDefault("Dave", 0)); // 0 — safe!

        // ── CONTAINS
        System.out.println(scores.containsKey("Alice"));   // true
        System.out.println(scores.containsValue(82));      // true

        // ── REMOVE
        scores.remove("Charlie");             // remove by key
        scores.remove("Bob", 99);            // remove only if key=Bob AND value=99

        // ── SIZE
        System.out.println(scores.size());   // 2
        System.out.println(scores.isEmpty());// false

        // ── ITERATION — 4 ways
        // 1. entrySet — most common, get key+value together
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.printf("  %s → %d%n", entry.getKey(), entry.getValue());
        }

        // 2. keySet — when you only need keys
        for (String key : scores.keySet()) {
            System.out.println(key);
        }

        // 3. values — when you only need values
        for (int value : scores.values()) {
            System.out.println(value);
        }

        // 4. forEach with lambda (cleanest)
        scores.forEach((name, score) ->
            System.out.printf("  %-12s: %d%n", name, score));

        // ── POWERFUL NEW METHODS

        // putIfAbsent — add only if key doesn't exist
        scores.putIfAbsent("Alice", 0); // Alice exists → no change
        scores.putIfAbsent("Dave", 70); // Dave doesn't exist → adds

        // computeIfAbsent — compute value if key missing
        Map<String, List<String>> groups = new HashMap<>();
        groups.computeIfAbsent("Engineering", k -> new ArrayList<>()).add("Alice");
        groups.computeIfAbsent("Engineering", k -> new ArrayList<>()).add("Bob");
        groups.computeIfAbsent("HR",          k -> new ArrayList<>()).add("Carol");
        System.out.println(groups);
        // {Engineering=[Alice, Bob], HR=[Carol]}

        // merge — combine existing value with new value
        Map<String, Integer> wordCount = new HashMap<>();
        String[] words = {"apple","banana","apple","cherry","banana","apple"};
        for (String word : words) {
            wordCount.merge(word, 1, Integer::sum); // add 1, or sum if exists
        }
        System.out.println(wordCount); // {apple=3, banana=2, cherry=1}

        // getOrDefault with computation
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Apple", 50);
        inventory.compute("Apple",  (k, v) -> v == null ? 1 : v + 10); // 60
        inventory.compute("Banana", (k, v) -> v == null ? 1 : v + 10); // 1
        System.out.println(inventory); // {Apple=60, Banana=1}
    }
}