import java.util.*;

public class CollectionsUtility {
    public static void main(String[] args) {

        List<Integer> nums = new ArrayList<>(List.of(3,1,4,1,5,9,2,6,5,3));

        // ── SORTING ────────────────────────────────────────────
        Collections.sort(nums);
        System.out.println(nums); // [1,1,2,3,3,4,5,5,6,9]

        Collections.sort(nums, Comparator.reverseOrder());
        System.out.println(nums); // [9,6,5,5,4,3,3,2,1,1]

        // ── SEARCHING (list must be sorted!) ───────────────────
        Collections.sort(nums);
        int idx = Collections.binarySearch(nums, 5);
        System.out.println("Found 5 at index: " + idx);

        // ── MIN/MAX ────────────────────────────────────────────
        System.out.println(Collections.min(nums)); // 1
        System.out.println(Collections.max(nums)); // 9

        // ── FREQUENCY ──────────────────────────────────────────
        System.out.println(Collections.frequency(nums, 3)); // 2

        // ── SHUFFLE ────────────────────────────────────────────
        Collections.shuffle(nums); // randomize order
        Collections.shuffle(nums, new Random(42)); // seeded — reproducible

        // ── REVERSE ────────────────────────────────────────────
        Collections.reverse(nums);

        // ── FILL & COPY ────────────────────────────────────────
        List<String> filled = new ArrayList<>(Arrays.asList(
                              new String[5])); // [null,null,null,null,null]
        Collections.fill(filled, "empty");
        System.out.println(filled); // [empty,empty,empty,empty,empty]

        // ── IMMUTABLE COLLECTIONS ──────────────────────────────
        List<String>        immList = Collections.unmodifiableList(new ArrayList<>(List.of("A","B")));
        Set<String>         immSet  = Collections.unmodifiableSet(new HashSet<>(Set.of("X","Y")));
        Map<String,Integer> immMap  = Collections.unmodifiableMap(new HashMap<>(Map.of("k",1)));
        // immList.add("C"); ← UnsupportedOperationException!

        // Java 9+ immutable factories (preferred)
        List<String>        list9 = List.of("A","B","C");        // immutable
        Set<String>         set9  = Set.of("X","Y","Z");         // immutable
        Map<String,Integer> map9  = Map.of("a",1,"b",2,"c",3);   // immutable
        // list9.add("D"); ← UnsupportedOperationException!

        // ── SINGLETON & EMPTY ──────────────────────────────────
        List<String> single = Collections.singletonList("only"); // immutable list of 1
        List<String> empty  = Collections.emptyList();           // immutable empty list

        // ── DISJOINT — check if two collections share no elements
        System.out.println(Collections.disjoint(
            List.of(1,2,3), List.of(4,5,6))); // true — no common elements
        System.out.println(Collections.disjoint(
            List.of(1,2,3), List.of(3,4,5))); // false — 3 is common

        // ── NCOPIES — create list of n copies ─────────────────
        List<String> copies = Collections.nCopies(5, "Java");
        System.out.println(copies); // [Java, Java, Java, Java, Java]
    }
}