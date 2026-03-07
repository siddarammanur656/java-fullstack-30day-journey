import java.util.HashMap;
import java.util.HashSet;

public class ContractDemo {
    public static void main(String[] args) {
        Point p1 = new Point(3, 4);
        Point p2 = new Point(3, 4); // equal to p1

        // HashSet — uses hashCode + equals to prevent duplicates
        HashSet<Point> set = new HashSet<>();
        set.add(p1);
        set.add(p2); // should NOT add — it's a duplicate
        System.out.println(set.size()); // 1  (if contract is correct)

        // HashMap — uses hashCode to find bucket, equals to match key
        HashMap<Point, String> map = new HashMap<>();
        map.put(p1, "origin");
        System.out.println(map.get(p2)); // "origin"  (p2 equals p1)
    }
}