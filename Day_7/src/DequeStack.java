import java.util.*;
public class DequeStack {
    public static void main(String[] args) {

        // ── Deque as STACK (LIFO — Last In First Out) ──────────
        // Use ArrayDeque — NOT the legacy Stack class!
        Deque<String> stack = new ArrayDeque<>();

        stack.push("page1"); // pushes to FRONT
        stack.push("page2");
        stack.push("page3");

        System.out.println(stack.peek()); // page3 — top of stack
        System.out.println(stack.pop());  // page3 — removes top
        System.out.println(stack.pop());  // page2
        System.out.println(stack);        // [page1]

        // Real use: browser back button
        Deque<String> browserHistory = new ArrayDeque<>();
        browserHistory.push("google.com");
        browserHistory.push("stackoverflow.com");
        browserHistory.push("github.com");

        System.out.println("Current: " + browserHistory.peek()); // github.com
        browserHistory.pop(); // back button
        System.out.println("After back: " + browserHistory.peek()); // stackoverflow.com

        // ── Deque as DOUBLE-ENDED queue ────────────────────────
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerFirst(1);  // [1]
        deque.offerLast(2);   // [1, 2]
        deque.offerFirst(0);  // [0, 1, 2]
        deque.offerLast(3);   // [0, 1, 2, 3]

        System.out.println(deque.pollFirst()); // 0
        System.out.println(deque.pollLast());  // 3
        System.out.println(deque);             // [1, 2]

        // Real use: sliding window problems, palindrome check
        // Undo/redo functionality in editors
    }
}