// SOLID Principles Demo

// S: Single Responsibility Principle
// A class should have only one reason to change,Each class should do one job only.
class Invoice {
    private double amount;
    Invoice(double amount) {
        this.amount = amount;
    }
    double getAmount() {
        return amount;
    }
}

// Separate class for printing (SRP)
class InvoicePrinter {
    void print(Invoice invoice) {
        System.out.println("Invoice amount: " + invoice.getAmount());
    }
}

//// ❌ Bad: One class doing too much
//class Report {
//    void generateReport() { /* logic */ }
//    void saveToFile() { /* logic */ }
//}
//

/// / ✅ Good: Split responsibilities
//class ReportGenerator {
//    void generateReport() { /* logic */ }
//}
//
//class ReportSaver {
//    void saveToFile() { /* logic */ }
//}
    //Logic: If saving changes, only ReportSaver changes. If generating changes, only ReportGenerator changes



// O: Open/Closed Principle
//Classes should be open for extension but closed for modification.
// You should add new features without changing existing code
interface Discount {
    double apply(double amount);
}

class NoDiscount implements Discount {
    public double apply(double amount) {
        return amount;
    }
}

class PercentageDiscount implements Discount {
    private double percent;
    PercentageDiscount(double percent) {
        this.percent = percent;
    }
    public double apply(double amount) {
        return amount - (amount * percent / 100);
    }
}
//// Base interface
//interface Shape {
//    double area();
//}
//

/// / ✅ Extend without modifying existing code
//class Circle implements Shape {
//    double radius;
//    Circle(double r) { radius = r; }
//    public double area() { return Math.PI * radius * radius; }
//}
//
//class Rectangle implements Shape {
//    double w, h;
//    Rectangle(double w, double h) { this.w = w; this.h = h; }
//    public double area() { return w * h; }
//}
    // Logic: If you add Triangle, you don’t touch Circle or Rectangle


// --- L: Liskov Substitution Principle ---
abstract class Bird {
    abstract void move();
}

class Sparrow extends Bird {
    public void move() {
        System.out.println("Sparrow flies...");
    }
}

class Ostrich extends Bird {
    public void move() {
        System.out.println("Ostrich runs...");
    }
}
//class Bird {
//    void fly() { System.out.println("Flying..."); }
//}
//
//class Sparrow extends Bird { } //  Works fine
//
//class Ostrich extends Bird {
//    @Override
//    void fly() { throw new UnsupportedOperationException("Can't fly!"); } // ❌ Violates LSP
//}
//Logic: Ostrich breaks the rule because not all birds can fly. Better design: separate FlyingBird and NonFlyingBird

// I: Interface Segregation Principle
//Don’t force classes to implement methods they don’t need.
//Use smaller, specific interfaces instead of one big interface
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class Human implements Workable, Eatable {
    public void work() {
        System.out.println("Human working...");
    }
    public void eat() {
        System.out.println("Human eating...");
    }
}

class Robot implements Workable {
    public void work() { System.out.println("Robot working..."); }
}
//Logic: Robot doesn’t eat, so it shouldn’t be forced to implement eat()

// D: Dependency Inversion Principle
//Depend on abstractions, not concrete classes.
//High-level modules shouldn’t depend on low-level modules, Both should depend on abstractions.
interface MessageService {
    void sendMessage(String msg);
}
// Low-level implementation
class EmailService implements MessageService {
    public void sendMessage(String msg) {
        System.out.println("Email sent: " + msg);
    }
}

class SMSService implements MessageService {
    public void sendMessage(String msg) {
        System.out.println("SMS sent: " + msg);
    }
}
// High-level class depends on abstraction
class Notification {
    private MessageService service;
    Notification(MessageService service) {
        this.service = service;
    }
    void notifyUser(String msg) {
        service.sendMessage(msg);
    }
}
//Easy to switch to SMSService later
////  VIOLATION — high-level class directly depends on low-level class
//class OrderServiceBad {
//    private MySQLDatabase db = new MySQLDatabase(); // tightly coupled!
//    private GmailSender   email = new GmailSender(); // tightly coupled!
//
//    public void placeOrder(Order order) {
//        db.save(order);           // can't swap to PostgreSQL without changing this
//        email.send("Confirmed!"); // can't swap to SendGrid without changing this
//    }
//}
//
/// /  CORRECT — depend on ABSTRACTIONS (interfaces), not implementations
//interface OrderRepository {
//    void save(Order order);
//    Order findById(int id);
//}
//
//interface NotificationService {
//    void send(String message);
//}
//
//// High-level class depends ONLY on interfaces — not concrete classes
//class OrderService {
//    private final OrderRepository      repository;
//    private final NotificationService  notifier;
//
//    // Dependencies INJECTED from outside (Dependency Injection!)
//    public OrderService(OrderRepository repo, NotificationService notifier) {
//        this.repository = repo;
//        this.notifier   = notifier;
//    }
//
//    public void placeOrder(Order order) {
//        repository.save(order);         // works with ANY repository
//        notifier.send("Order placed!"); // works with ANY notifier
//    }
//}
//
//// Low-level implementations — easily swappable
//class MySQLRepository implements OrderRepository {
//    @Override public void  save(Order o)     { System.out.println("Saving to MySQL"); }
//    @Override public Order findById(int id)  { return null; }
//}
//
//class MongoRepository implements OrderRepository {
//    @Override public void  save(Order o)     { System.out.println("Saving to MongoDB"); }
//    @Override public Order findById(int id)  { return null; }
//}
//
//class EmailNotifier implements NotificationService {
//    @Override public void send(String msg)  { System.out.println("Email: " + msg); }
//}
//
//class SMSNotifier implements NotificationService {
//    @Override public void send(String msg)  { System.out.println("SMS: " + msg); }
//}
//

//// Swap implementations without touching OrderService!
//class Order { int id; String item; }

// --- MAIN DEMO ---
public class SolidDemo {
    public static void main(String[] args) {
        // S: Single Responsibility
        Invoice invoice = new Invoice(500);
        InvoicePrinter printer = new InvoicePrinter();
        printer.print(invoice);

        // O: Open/Closed
        Discount discount = new PercentageDiscount(10);
        System.out.println("Discounted amount: " + discount.apply(invoice.getAmount()));

        // L: Liskov Substitution
        Bird sparrow = new Sparrow();
        Bird ostrich = new Ostrich();
        sparrow.move(); // Sparrow flies
        ostrich.move(); // Ostrich runs

        // I: Interface Segregation
        Human human = new Human();
        Robot robot = new Robot();
        human.work();
        human.eat();
        robot.work();

        // D: Dependency Inversion
        Notification emailNotif = new Notification(new EmailService());
        Notification smsNotif = new Notification(new SMSService());
        emailNotif.notifyUser("Hello via Email!");
        smsNotif.notifyUser("Hello via SMS!");
    }
}

//- S → One job per class
//- O → Add new features without changing old code
//- L → Subclass must behave like parent
//- I → Small, specific interfaces
//- D → Depend on interfaces, not concrete classes