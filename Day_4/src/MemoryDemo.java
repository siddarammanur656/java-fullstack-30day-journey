public class MemoryDemo {
    public static void main(String[] args) {
        Student alice =new Student();
        alice.name="Alice";
        alice.age=20;
        alice.gpa=8.89;

        Student copy=alice;
        copy.name="Alice Modified";
        System.out.println(alice.name);//Alice Modified -same  object

        //check if two variable or reference pointing to the same object
        System.out.println(alice==copy); //true same memory address

        //Create truly separate object
        Student newStudent=new Student();
        newStudent.name="Alice";
        newStudent.age=20;
        newStudent.gpa=8.89;

        System.out.println(alice==newStudent); //false-different objects, Even though fields are identical, they're different objects in memory
    }
}