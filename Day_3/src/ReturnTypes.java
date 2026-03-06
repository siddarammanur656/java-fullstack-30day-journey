import java.util.Arrays;

//Every Return Type Has a Contract
public class ReturnTypes {

    //Return an int -must have return statement with int value
    static int add(int a, int b){
        return a+b;
    }

    //return a String
    static String greet(String name){
        return"Hello, "+name+"!";
    }

    //Return a boolean -great for validation methods
    static boolean isEven(int n){
        return n%2==0; //return true or false directly
    }

    //void-return nothing , but can still use 'return' to exit early
    static void printIfPositive(int n){
        if(n<=0){
            return; // early exit — guard clause pattern
        }
        System.out.println("Positive Number: "+n);
    }

    //return an array
    static int [] getElement(int [] score){
        return score;

    }

    //Not Good practice - Deeply nested - hard to read
    static String processAge(int age){
        if(age>0){
            if(age<150){
                if(age>=18){
                    return "adult";
                }else{
                    return "Minor";
                }
            }else{
                return "Invalid Age";
            }
        }else{
            return "Invalid age";
        }
    }

    //Good Practice :- Guard clauses - fail fast , main logic stay flat
    static String processAgeClean(int age){
        if(age>0 || age>=150){
            return "Invalid Age";
        }
        if(age<18){
            return "Minor";
        }else{
            return "Adult";
        }
    }

    public static void main(String[] args) {
        System.out.println(add(3, 4)); // 7
        System.out.println(greet("Bob"));// Hello, Bob!
        System.out.println(isEven(10));   // true
        printIfPositive(-5);   // (prints nothing)
        printIfPositive(42); // Positive number: 42

        int[] score = {55, 92, 78, 63, 96, 88, 71};
        System.out.println(Arrays.toString(getElement(score)));//[55, 92, 78, 63, 96, 88, 71]
    }
}