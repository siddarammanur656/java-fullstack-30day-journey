import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;

public class TreeSetDeep {
    public static void main(String[] args) {

        // Natural order (String alphabetical)
        TreeSet<String> names = new TreeSet<>();
        names.add("Charlie");
        names.add("Alice");
        names.add("Bob");
        names.add("Dave");
        System.out.println(names); // [Alice, Bob, Charlie, Dave] always sorted!

        // Navigation methods — TreeSet's superpower
        System.out.println(names.first());          // Alice — smallest
        System.out.println(names.last());           // Dave — largest
        System.out.println(names.floor("Carol"));   // Charlie — ≤ "Carol"
        System.out.println(names.ceiling("Carol")); // Dave — ≥ "Carol"
        System.out.println(names.lower("Bob"));     // Alice — strictly < "Bob"
        System.out.println(names.higher("Bob"));    // Charlie — strictly > "Bob"
        System.out.println(names.headSet("Charlie")); // [Alice, Bob] — before Charlie
        System.out.println(names.tailSet("Bob"));     // [Bob, Charlie, Dave] — from Bob

        // Custom sort order
        TreeSet<String> byLength = new TreeSet<>(
            Comparator.comparingInt(String::length)
                      .thenComparing(Comparator.naturalOrder()) // tie-break
        );
        byLength.addAll(List.of("Charlie","Al","Bob","Dave","Eve"));
        System.out.println(byLength); // [Al, Bob, Eve, Dave, Charlie] — by length

        // TreeSet with integers
        TreeSet<Integer> nums = new TreeSet<>(Set.of(5,2,8,1,9,3,7,4,6));
        System.out.println(nums);                      // [1,2,3,4,5,6,7,8,9]
        System.out.println(nums.subSet(3, 7));         // [3,4,5,6] — 3 inclusive, 7 exclusive
        System.out.println(nums.subSet(3, true, 7, true)); // [3,4,5,6,7] — both inclusive
        System.out.println(nums.descendingSet());      // [9,8,7,6,5,4,3,2,1]
    }
}