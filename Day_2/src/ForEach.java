import java.util.ArrayList;
import java.util.List;

//Enhanced for (for-each) — Cleanest Way to Iterate
public class ForEach {
    public static void main(String[] args) {
        int [] numbers ={10, 20, 30, 40,50};

        //Classic for - use when you need the index
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("Index "+i+": "+numbers[i]);
        }

        //Enhanced for - cleaner when you don't need the index
        for(int num:numbers){
            System.out.println(num);
        }

        //Works on any Iterable (ArrayList,Set, etc.)
        ArrayList<String> names= new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");

        for(String name: names){
            System.out.println("Hello, "+name+"!");
        }
    }
}