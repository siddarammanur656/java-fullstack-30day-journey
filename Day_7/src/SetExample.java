import java.util.*;

public class SetExample {
    public static void main(String[] args) {
        // HashSet Example
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Apple");
        hashSet.add("Banana");
        hashSet.add("Apple"); // Duplicate ignored
        System.out.println("HashSet: " + hashSet);

        // LinkedHashSet Example
        Set<String> linkedSet = new LinkedHashSet<>();
        linkedSet.add("Dog");
        linkedSet.add("Cat");
        linkedSet.add("Dog"); // Duplicate ignored
        System.out.println("LinkedHashSet: " + linkedSet);

        // TreeSet Example
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(5);
        treeSet.add(1);
        treeSet.add(3);
        System.out.println("TreeSet (Sorted): " + treeSet);
    }
}