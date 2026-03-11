import java.util.*;

public class ArrayListDeep{
    public static void main(String[] args) {


        // ── CREATION
        List<String> list  = new ArrayList<>();           // empty, default capacity 10
        List<String> sized = new ArrayList<>(100);        // pre-sized — avoids resizing
        List<String> copy  = new ArrayList<>(List.of("A","B","C")); // from existing

        // ── ADDING
        list.add("Alice");          // add to end → O(1) amortized
        list.add("Bob");
        list.add("Charlie");
        list.add(1, "Dave");        // insert at index 1 → O(n) shifts right
        System.out.println(list);   // [Alice, Dave, Bob, Charlie]

        // Add multiple at once
        list.addAll(List.of("Eve", "Frank"));
        list.addAll(1, List.of("X","Y"));  // insert collection at index 1
        System.out.println(list);

        System.out.println("---------------------------------------------");
        // ── ACCESSING
        System.out.println(list.get(0));        // Alice — O(1) direct access
        System.out.println(list.size());        // total elements
        System.out.println(list.isEmpty());     // false
        System.out.println(list.contains("Bob"));   // true — O(n) linear search
        System.out.println(list.indexOf("Bob"));    // first occurrence index
        System.out.println(list.lastIndexOf("Bob")); // last occurrence index

        System.out.println("---------------------------------------------");

        // ── UPDATING
        list.set(0, "ALICE");   // replace at index → O(1)
        System.out.println(list.get(0)); // ALICE

        System.out.println("----------------------------------------------");

        // ── REMOVING
        list.remove(0);             // remove by INDEX → O(n) shifts left
        list.remove("Frank");       // remove by VALUE → O(n) search + shift
        list.removeIf(s -> s.startsWith("X")); // remove matching predicate
        System.out.println(list);

        System.out.println("-----------------------------------------------");
        // ── SUBLIST
        List<String> sub = list.subList(0, 2); // view of elements 0,1 (NOT a copy!)
        // Modifying sub modifies the original list!
        System.out.println(sub);

        System.out.println("----------------------------------------------");
        // ── ITERATION
        // 1. Enhanced for (most common)
        for (String s : list) {
            System.out.print(s + " ");
        }
        System.out.println();
        //Old or Iterative for loop
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i)+" ");
        }
        System.out.println();
        System.out.println("--------------------------------------------");
        // 2. Iterator (safe removal during iteration)
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String s = it.next();
            if (s.length() > 5) {
                it.remove(); // safe! ConcurrentModificationException if you use list.remove() here
                System.out.println();
            }
        }
        System.out.println(list);
        System.out.println("---------------------------------------------");
        // 3. ListIterator (bidirectional)
        ListIterator<String> lit = list.listIterator(list.size()); // start at end
        while (lit.hasPrevious()) {
            System.out.print(lit.previous() + " "); // iterate backwards
        }
        System.out.println();
        System.out.println("---------------------------------------------");
        // 4. forEach with lambda
        list.forEach(s -> System.out.println(s.toUpperCase()));

        System.out.println("----------------------------------------------");
        // ── SORTING ────────────────────────────────────────────
        Collections.sort(list);                      // natural order
        System.out.println(list);
        list.sort(Comparator.naturalOrder());         // same
        System.out.println(list);
        list.sort(Comparator.reverseOrder());         // reverse
        System.out.println(list);
        list.sort(Comparator.comparingInt(String::length)); // by length
        System.out.println(list);

        System.out.println("------------------------------------------");
        // ── CONVERSION ─────────────────────────────────────────
        String[] arr   = list.toArray(new String[0]); // to array
        List<String> immutable = Collections.unmodifiableList(list); // read-only view
        System.out.println(Arrays.toString(arr));
        System.out.println(immutable);
        immutable.add(0,"hello");//Error UnsupportedOperationException

    }
}