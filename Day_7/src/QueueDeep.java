import java.util.*;
public class QueueDeep {
    public static void main(String[] args) {

        // ── ArrayDeque — best general Queue implementation ─────
        Queue<String> queue = new ArrayDeque<>();

        // offer() — add to tail (preferred over add() — returns false vs exception)
        queue.offer("Customer1");
        queue.offer("Customer2");
        queue.offer("Customer3");

        System.out.println(queue);
        System.out.println(queue.peek());   // Customer1 — look without removing
        System.out.println(queue);
        System.out.println(queue.poll());   // Customer1 — remove from head
        System.out.println(queue);
        System.out.println(queue.poll());   // Customer2
        System.out.println(queue);
        System.out.println(queue.size());   // 1 (Customer3 remains)
        System.out.println("------------------------------------------------");

        // ── PriorityQueue — elements sorted by priority ────────
        // Default: min-heap (smallest element first)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(5);
        minHeap.offer(1);
        minHeap.offer(3);
        minHeap.offer(2);
        minHeap.offer(4);

        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " "); // 1 2 3 4 5 — always sorted!
        }
        System.out.println();
        System.out.println("-------------------------------------------");
        // Max-heap — largest first
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.addAll(List.of(5,1,3,2,4));
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " "); // 5 4 3 2 1
        }
        System.out.println();
        System.out.println("-------------------------------------------");

        // PriorityQueue with custom objects
        PriorityQueue<String> byLength = new PriorityQueue<>(Comparator.comparingInt(String::length));
        byLength.addAll(List.of("banana","hi","apple","a","cherry"));
        while (!byLength.isEmpty()) {
            System.out.print(byLength.poll() + " "); // a hi apple banana cherry
        }
    }
}