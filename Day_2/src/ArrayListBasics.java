import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ArrayListBasics{

    public static void main(String[] args) {
        //Always declare with interface type (List), instantiate with ArrayList
        List<String> names=new ArrayList<>();

        //Adding
        names.add("Alice");         // add to end
        names.add("Bob");
        names.add("Charlie");
        names.add(1, "Dave"); // insert at index 1
        System.out.println(names);


        //Accessing
        System.out.println(names.get(0));
        System.out.println(names.size());
        System.out.println(names.contains("Bob"));
        System.out.println(names.indexOf("Bob"));

        //Updating
        names.set(2,"Barbara");
        System.out.println(names);

        //removing
        names.remove("Dave");
        names.remove(0);
        System.out.println(names);

        //Iterating
        for (String name:names){
            System.out.println(name);
        }

        //Sorting
        List<Integer> numbers=new ArrayList<>();
        numbers.add(5);
        numbers.add(2);
        numbers.add(8);
        numbers.add(1);
        numbers.add(9);
        numbers.add(3);
        System.out.println(numbers);
        Collections.sort(numbers);
        System.out.println(numbers);

        //Reverse sort
        numbers.sort(Collections.reverseOrder());
        System.out.println(numbers);

        //Min or Max
        System.out.println(Collections.min(numbers));
        System.out.println(Collections.max(numbers));

        //Clear and check empty
        numbers.clear();
        System.out.println(numbers.isEmpty());
    }
}