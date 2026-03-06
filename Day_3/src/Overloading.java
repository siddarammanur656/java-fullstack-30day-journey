public class Overloading {

    //Same name 'add' -different parameter types/count
    static int add(int a, int b){
        return a+b;
    }
    static double add(double a, double b){
        return a+b;
    }
    static int add(int a, int b,int c){
        return a+b+c;
    }
    static String add(String a, String b){
        return a+b;
    }

    public static void main(String[] args) {
        System.out.println(add(2, 3));           // calls int version → 5
        System.out.println(add(2.5, 3.1));       // calls double version → 5.6
        System.out.println(add(1, 2, 3));        // calls 3-param version → 6
        System.out.println(add("Hello", "World"));// calls String version → HelloWorld
    }
}

//How java resolves which method to call
// java use this exact decision process at "compile time" .
//step 1: match the exact parameter types
//step 2: try widening (int -> long-> float->double)
//step 3: try autoboxing(int->Integer)
//step 4: try varargs
//step 5: if still ambiguous -> compilation error

