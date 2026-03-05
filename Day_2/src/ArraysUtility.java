import java.sql.Array;
import java.util.Arrays;

//Arrays Utility Class — Your Best Friend
public class ArraysUtility {
    public static void main(String[] args) {
        int [] numbers = {64, 34, 25, 12, 22, 11, 90};

        //print array nicely (without this you get [I@3764951d])
        System.out.println(Arrays.toString(numbers));

        //Sort - modifies the array in place
        Arrays.sort(numbers);
        System.out.println(Arrays.toString(numbers));

        //Binary search - only works on sorted arrays
        int index = Arrays.binarySearch(numbers,25);
        System.out.println("Found 25 at index: "+index);


        System.out.println("----------------");

        //Copy
        int [] copy=Arrays.copyOf(numbers,numbers.length);
        System.out.println(Arrays.toString(copy));
        int [] partial = Arrays.copyOfRange(numbers,0,4);
        System.out.println(Arrays.toString(partial));

        System.out.println("------------------------");
        //Fill
        int [] zeros= new int [5];
        Arrays.fill(zeros,7);
        System.out.println(Arrays.toString(zeros)); //[7,7,7,7,7]

        //Compare
        System.out.println(Arrays.equals(numbers,copy));

    }
}