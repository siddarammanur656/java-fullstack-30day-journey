import java.util.Arrays;

//Case 2: Object Reference — the REFERENCE is copied (not the object!)
public class PassByReference {

    static void tryToChange(int [] arr){
        arr[0]=99; //Modifies the actual object the reference points to!
    }

    static void tryToReplace(int [] arr){
        arr =new int[]{10,20,40,50};//replace local copy of reference only
    }

    public static void main(String[] args) {

        int [] arr={1,2,3,4,5};
        tryToChange(arr);
        System.out.println(Arrays.toString(arr)); //99<- changed (same object modified)

        tryToReplace(arr);
        System.out.println(Arrays.toString(arr)); //still 99  (reference reassignment didn't affect original)
    }
}