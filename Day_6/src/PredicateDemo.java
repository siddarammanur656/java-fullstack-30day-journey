import java.util.function.Predicate;

public class PredicateDemo {
    public static void main(String[] args) {
        Predicate<String> isLong= str->str.length()>5;
        System.out.println(isLong.test("Hello"));
        System.out.println(isLong.test("Functional"));
    }
}
