import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamLambdaDemo {
    public static void main(String[] args) {
        List<String> names= Arrays.asList("Siddarama","Arjun","Meera","Anita","Raj");


        names.stream()
                .filter(name -> name.startsWith("A"))   // keep only names starting with A, Predicate functional interface
                .map(String::toUpperCase)               // convert to uppercase. Function functional interface
                .sorted() // Comparator functional interface
                .forEach(System.out::println);          // print each ,Consumer functional interface

    }
}
