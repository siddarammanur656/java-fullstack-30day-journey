public class Counter {

    // Static field — shared by ALL Counter objects
    private static int totalCreated = 0;

    // Instance field — each object has its OWN
    private int id;
    private String label;

    public Counter(String label) {
        totalCreated++;           // increment the shared counter
        this.id    = totalCreated;// this object's unique id
        this.label = label;
    }

    // Instance method — operates on THIS specific object
    public void display() {
        System.out.printf("Counter #%d: %s (total: %d)%n",
                           id, label, totalCreated);
    }

    // Static method — no 'this', no access to instance fields!
    public static int getTotalCreated() {
        return totalCreated;
        // return id; ← COMPILE ERROR — id is instance field!
    }

    // Static utility method — doesn't need object state
    public static String formatCount(int n) {
        return n == 1 ? "1 counter" : n + " counters";
    }

    public static void main(String[] args) {
        System.out.println(Counter.getTotalCreated()); // 0

        Counter c1 = new Counter("Alpha");
        Counter c2 = new Counter("Beta");
        Counter c3 = new Counter("Gamma");

        c1.display(); // Counter #1: Alpha (total: 3)
        c2.display(); // Counter #2: Beta  (total: 3)
        c3.display(); // Counter #3: Gamma (total: 3)

        System.out.println(Counter.getTotalCreated()); // 3
        System.out.println(Counter.formatCount(3));    // "3 counters"
    }
}