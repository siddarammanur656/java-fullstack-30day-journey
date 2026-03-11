class MyStack {
    private int[] arr;
    private int top;
    private int capacity;

    public MyStack(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        top = -1;
    }

    // Push
    public void push(int item) {
        if (top == capacity - 1) {
            System.out.println("Stack overflow!");
            return;
        }
        arr[++top] = item;
    }

    // Pop
    public int pop() {
        if (top == -1) {
            System.out.println("Stack underflow!");
            return -1;
        }
        return arr[top--];
    }

    // Peek
    public int peek() {
        if (top == -1) {
            System.out.println("Stack is empty!");
            return -1;
        }
        return arr[top];
    }

    public void display() {
        if (top == -1) {
            System.out.println("Stack is empty!");
            return;
        }
        System.out.print("Stack: ");
        for (int i = 0; i <= top; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}

public class CustomStackDemo {
    public static void main(String[] args) {
        MyStack stack = new MyStack(5);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.display();

        System.out.println("Popped: " + stack.pop());
        System.out.println("Top element: " + stack.peek());
        stack.display();
    }
}