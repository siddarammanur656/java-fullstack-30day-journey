
//Type 1 — Compile-Time Polymorphism (Method Overloading)
class Printer {
    void print(int x)    { System.out.println("int: " + x); }
    void print(String x) { System.out.println("String: " + x); }
    void print(double x) { System.out.println("double: " + x); }
}
// Decision made by COMPILER — static dispatch