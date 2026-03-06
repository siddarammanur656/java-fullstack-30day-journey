
//Day 3 Project: Math Utilities Toolkit
public class MathUtils {
    //basic
    static int add(int a,int b){
        return a+b;
    }
    static int subtract(int a,int b){
        return a-b;
    }
    static int multiply(int a,int b){
        return a*b;
    }
    static double divide(double a,double b){
        if(b==0){
            throw new ArithmeticException("Division by  zero");
        }
        return a/b;
    }

    //overloaded : average for different inputs
    static double average(int a,int b){
        return (a+b)/2.0;
    }
    static double average(int a,int b,int c){
        return (a+b+c)/3.0;
    }
    static double average(int [] numbers){
        if(numbers.length==0){
            return 0;
        }
        double sum=0;
        for (int i = 0; i < numbers.length; i++) {
            sum+=numbers[i];
        }
        return sum/ numbers.length;
    }

    //Recursive
    static long factorial(int n){
        if(n<0){
            throw new IllegalArgumentException("Negative Factorial");
        }
        if(n<=1){
            return 1;
        }
        return n*factorial(n-1);
    }

    static long fibonacci(int n){
        if(n<0){
            throw new IllegalArgumentException("Negative Index");
        }
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        long first=0;
        long second=1;
        for (int i = 0; i <=n; i++) {
            long third=first+second;
            first=second;
            second=third;
        }
        return second;
    }
    static double power(double base , int exp){

        // Recursive power: base^exp
        if(exp<0){
            return 1/power(base,-exp);
        }
        // Base cases
        if(exp==0){
            return 1;
        }
        if(exp==1){
            return base;
        }
        // Optimization: fast power using divide-and-conquer
        // base^8 = (base^4)^2 — only 3 multiplications instead of 7!
        if(exp%2==0){
            double  half=power(base, exp/2);
            return half*half;
        }else {
            return base*power(base,exp-1);
        }
    }

    //number theory
    static boolean isPrime(int n){
        if(n<2){
            return false;
        }
        if(n==2){
            return true;
        }
        if(n%2==0){
            return false;
        }
        int c=3;
        while(c*c<=n){
            if(n%c==0){
                return false;
            }
            c+=2;
        }
        return true;
    }

    static int gcd(int a, int b) {
        if (b == 0) {
            return a;   // Base case: when remainder is 0
        }
        return gcd(b, a % b);  // Recursive step
    }

    static int lcm(int a, int b) {
        return Math.abs(a * b) / gcd(a, b); // avoid overflow
    }
    static boolean isPerfectSquare(int n) {
        if (n < 0) {
            return false;
        }
        int sqrt = (int) Math.sqrt(n);
        return sqrt * sqrt == n;
    }

    //String or number utilities
    static boolean isPalindrome(String s){
        s=s.toLowerCase().replaceAll("[^a-z0-9]","");
        if(s.length()<=1){
            return true;
        }
        if(s.charAt(0)!=s.charAt(s.length()-1)){
            return false;
        }
        return isPalindrome(s.substring(1,s.length()-1));
    }
    static int digitSum(int n) {
        int sum = 0;
        n = Math.abs(n);
        while (n != 0) {
            int rem = n % 10;
            sum += rem;
            n = n / 10;
        }
        return sum;
    }
    static int reverseNumber(int n) {
        boolean isNegative = n < 0;
        n = Math.abs(n);
        int reversed = 0;
        while (n != 0) {
            int rem=n%10;
            reversed =reversed*10+rem;
            n=n/10;
        }
        return isNegative ? -reversed : reversed;
    }
    //statistics
    static int max(int[] arr) {
        int max = arr[0];
        for (int n : arr) {
            if (n > max) {
                max = n;
            }
        }
        return max;
    }
    static int min(int[] arr) {
        int min = arr[0];
        for (int n : arr) {
            if (n < min){
                min = n;
            }
        }
        return min;
    }
    static double standardDeviation(int[] data) {
        // Step 1: Find mean
        double sum = 0;
        for (int num : data) {
            sum += num;
        }
        double mean = sum / data.length;

        // Step 2: Find squared differences
        double sqDiffSum = 0;
        for (int num : data) {
            sqDiffSum += (num - mean) * (num - mean);
        }

        // Step 3: Divide by N (population SD)
        double variance = sqDiffSum / data.length;

        // Step 4: Square root
        return Math.sqrt(variance);
    }




    public static void main(String[] args) {
        // Basic operations
        System.out.println("Add: " + add(5, 3));          // 8
        System.out.println("Subtract: " + subtract(5, 3)); // 2
        System.out.println("Multiply: " + multiply(5, 3)); // 15
        System.out.println("Divide: " + divide(10, 2));    // 5.0

        // Average
        System.out.println("Average of 5,3: " + average(5, 3));          // 4.0
        System.out.println("Average of 5,3,7: " + average(5, 3, 7));     // 5.0
        System.out.println("Average of array: " + average(new int[]{2,4,6,8})); // 5.0

        // Recursive
        System.out.println("Factorial of 5: " + factorial(5)); // 120
        System.out.println("Fibonacci of 7: " + fibonacci(7)); // 13
        System.out.println("Power 2^10: " + power(2, 10));     // 1024.0

        // Number theory
        System.out.println("Is 29 prime? " + isPrime(29));     // true
        System.out.println("GCD of 48,18: " + gcd(48, 18));    // 6
        System.out.println("LCM of 12,18: " + lcm(12, 18));    // 36
        System.out.println("Is 49 perfect square? " + isPerfectSquare(49)); // true

        // String/number utilities
        System.out.println("Is 'racecar' palindrome? " + isPalindrome("racecar")); // true
        System.out.println("Digit sum of 1234: " + digitSum(1234));                // 10
        System.out.println("Reverse of -1234: " + reverseNumber(-1234));           // -4321

        // Statistics
        int[] arr = {2, 4, 4, 4, 5, 5, 7, 9};
        System.out.println("Max: " + max(arr));                   // 9
        System.out.println("Min: " + min(arr));                   // 2
        System.out.println("Standard Deviation: " + standardDeviation(arr)); // 2.0

    }
}