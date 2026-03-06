public class OverloadResolution {

    static void show(int x)    {
        System.out.println("int: " + x);
    }
    static void show(long x)   {
        System.out.println("long: " + x);
    }
    static void show(double x) {
        System.out.println("double: " + x);
    }

    public static void main(String[] args) {
        show(5);     // int: 5     (exact match)
        show(5L);    // long: 5    (exact match — L suffix)
        show(5.0);   // double: 5.0 (exact match — decimal)
        show('A');   // int: 65    (char widens to int!)
    }
}