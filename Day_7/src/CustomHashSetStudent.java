import java.util.*;

// CRITICAL: Custom objects in HashSet MUST override both!
class Student {
    String name;
    int id;

    Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student s = (Student) o;
        return id == s.id && Objects.equals(name, s.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
public class CustomHashSetStudent{
    public static void main(String[] args) {
        Set<Student> students = new HashSet<>();
        students.add(new Student("Alice", 1));
        students.add(new Student("Alice", 1)); // duplicate — same id+name
        System.out.println(students.size()); // 1 ✅ (only works because we overrode both)
    }
}
