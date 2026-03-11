class MyDeque {
    private int[] arr;
    private int front, rear, size, capacity;

    public MyDeque(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = -1;
        rear = 0;
        size = 0;
    }

    // Add at front
    public void addFront(int item) {
        if (size == capacity) {
            System.out.println("Deque is full!");
            return;
        }
        if (front == -1) { // empty deque
            front = 0;
            rear = 0;
        } else {
            front = (front - 1 + capacity) % capacity;
        }
        arr[front] = item;
        size++;
    }

    // Add at rear
    public void addRear(int item) {
        if (size == capacity) {
            System.out.println("Deque is full!");
            return;
        }
        if (front == -1) { // empty deque
            front = 0;
            rear = 0;
        } else {
            rear = (rear + 1) % capacity;
        }
        arr[rear] = item;
        size++;
    }

    // Remove from front
    public int removeFront() {
        if (size == 0) {
            System.out.println("Deque is empty!");
            return -1;
        }
        int item = arr[front];
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    // Remove from rear
    public int removeRear() {
        if (size == 0) {
            System.out.println("Deque is empty!");
            return -1;
        }
        int item = arr[rear];
        rear = (rear - 1 + capacity) % capacity;
        size--;
        return item;
    }

    public void display() {
        if (size == 0) {
            System.out.println("Deque is empty!");
            return;
        }
        System.out.print("Deque: ");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[(front + i) % capacity] + " ");
        }
        System.out.println();
    }
}

public class CustomDequeDemo {
    public static void main(String[] args) {
        MyDeque dq = new MyDeque(5);
        dq.addRear(10);
        dq.addRear(20);
        dq.addFront(5);
        dq.display();

        System.out.println("Removed front: " + dq.removeFront());
        System.out.println("Removed rear: " + dq.removeRear());
        dq.display();
    }
}