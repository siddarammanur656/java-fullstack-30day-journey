import java.util.ArrayDeque;
import java.util.Deque;

public class DequeExample {
    public static void main(String[] args) {
        Deque<String> deque = new ArrayDeque<>();

        // Add elements at both ends
        deque.addFirst("A");  // front
        deque.addLast("B");   // rear
        deque.addLast("C");

        System.out.println("Deque: " + deque);

        // Remove elements from both ends
        String front = deque.removeFirst();
        String rear = deque.removeLast();

        System.out.println("Removed front: " + front);
        System.out.println("Removed rear: " + rear);
        System.out.println("Deque after removals: " + deque);
    }
}