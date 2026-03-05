//ArrayList of Objects — Real-World Pattern

import java.util.ArrayList;
import java.util.List;

class Student{
    String name;
    double grade;

    Student(String name , double grade){
        this.name=name;
        this.grade=grade;
    }

    public String toString(){
        return String.format("Name: "+name+" Grade: "+grade);
    }
}

public class ArrayListOfObjects {

    public static void main(String[] args) {
        List<Student> students=new ArrayList<>();
        students.add(new Student("Alice",92.5));
        students.add(new Student("Bob",78.0));
        students.add(new Student("Charlie",85.5));
        students.add(new Student("Diana",96));

        //Find highest grade
        Student top=students.get(0);
        for(Student s:students){
            if(s.grade > top.grade){
                top = s;
            }
        }
        System.out.println("Top student: "+top);

        //Filter-Students  who passed (grade >=  80)
        List<Student> passed=new ArrayList<>();
        for(Student s: students){
            if(s.grade>=80){
                passed.add(s);
            }
        }
        System.out.println("Passed: "+passed);

    }
}