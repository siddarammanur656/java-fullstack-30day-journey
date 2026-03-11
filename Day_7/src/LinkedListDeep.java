import java.util.LinkedList;
import java.util.Queue;

public class LinkedListDeep {
    public static void main(String[] args) {

        // LinkedList implements both List AND Deque
        LinkedList<String> ll = new LinkedList<>();

        // ── LIST OPERATIONS ────────────────────────────────────
        ll.add("B");
        ll.add("C");
        ll.add("D");

        // ── DEQUE OPERATIONS (what makes LinkedList special) ───
        ll.addFirst("A");   // O(1) — add to front
        ll.addLast("E");    // O(1) — add to back
        ll.offerFirst("Z"); // same as addFirst but returns boolean

        System.out.println(ll); // [Z, A, B, C, D, E]

        System.out.println(ll.getFirst());  // Z — O(1), throws if empty
        System.out.println(ll.getLast());   // E — O(1)
        System.out.println(ll.peekFirst()); // Z — O(1), returns null if empty (safe)

        ll.removeFirst(); // O(1) — removes Z
        ll.removeLast();  // O(1) — removes E
        System.out.println(ll);

        // ── WHEN LinkedList WINS over ArrayList ────────────────
        // Use LinkedList when you frequently:
        // 1. Add/remove from BOTH ends (queue, deque usage)
        // 2. Use as a stack: push()/pop()
        // 3. Process elements as a queue: offer()/poll()

        // ── WHEN ArrayList WINS ────────────────────────────────
        // Use ArrayList (almost always) when you:
        // 1. Need random access: list.get(500) is O(1) vs O(n)
        // 2. Mostly read, rarely insert in middle
        // 3. Need memory efficiency (no node overhead)

        // As a Queue (FIFO)
        Queue<String> queue = new LinkedList<>();
        queue.offer("first");   // add to tail
        queue.offer("second");
        queue.offer("third");
        System.out.println(queue);
        System.out.println(queue.poll());   // "first" — removes from head
        System.out.println(queue.peek());   // "second" — looks without removing
    }
}