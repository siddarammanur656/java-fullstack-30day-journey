import java.util.Arrays;
import java.util.List;

public class ReduceDemo {
    public static void main(String[] args) {
        List<Integer> numbers= Arrays.asList(1,2,3,4,5);
        int sum=numbers.stream().reduce(0,(a,b)->a+b);//BinaryOperator functional interface

        System.out.println("Sum: "+sum);
    }
}
