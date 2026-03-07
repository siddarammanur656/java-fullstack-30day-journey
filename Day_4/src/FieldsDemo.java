//Fields — Instance Variables Deep Dive
public class FieldsDemo {

    //instance fields - each object gets its own copy
    String name;
    int age;
    double gpa;
    boolean enrolled;

    public static void main(String[] args) {
        FieldsDemo s1= new FieldsDemo();
        FieldsDemo s2= new FieldsDemo();


        // Each object has its OWN fields — changing one doesn't affect the other
        s1.name = "Alice";
        s2.name = "Bob";

        System.out.println(s1.name);   //Alice
        System.out.println(s2.name);  //Bob -independent

        // Default values — Java initializes fields automatically
        System.out.println(s1.age);      // 0
        System.out.println(s1.enrolled); // false
        System.out.println(s1.gpa);      // 0.0

        // Local variables are NOT initialized automatically — compile error!
        // int x;
        // System.out.println(x); // ERROR: variable x might not have been initialized
    }
}