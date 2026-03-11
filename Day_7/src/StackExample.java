import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        // Push elements
        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Stack: " + stack);

        // Pop element (remove from top)
        int top = stack.pop();
        System.out.println("Popped: " + top);
        System.out.println("Stack after pop: " + stack);

        // Peek at the top element
        System.out.println("Top element: " + stack.peek());
    }
}