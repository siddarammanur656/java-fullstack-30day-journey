//## Fibonacci — Branching Recursion
public class Fibonacci {

    //Simple recursive -easy to understand but slow for large  n
    static int fiboRecu(int n){
        if(n==0){
            return 0;  //base case 1
        }
        if(n==1){
            return 1;//base case 2
        }
        return fiboRecu(n-1)+fiboRecu(n-2); //recursive case
    }

    // Why is it slow? For fib(5):
    // fib(5) calls fib(4) and fib(3)
    // fib(4) calls fib(3) and fib(2)  ← fib(3) calculated TWICE!
    // fib(3) calls fib(2) and fib(1)  ← fib(2) calculated multiple times!
    // This grows EXPONENTIALLY — fib(50) takes seconds!

    // Memoization — store results to avoid recalculating
    static long [] memo=new long[100];
    static long fibFast(int n){
        if(n==0){
            return 0;  //base case 1
        }
        if(n==1){
            return 1;//base case 2
        }

        if(memo[n]!=0){
            return memo[n];// return cached result!
        }

        memo[n]=fibFast(n-1)+fibFast(n-2);
        return memo[n];
    }

    //Iterative -most efficient, no stack overhead
    static long fibIterative(int n){
        if(n==0){
            return 0;  //base case 1
        }
        if(n==1){
            return 1;//base case 2
        }
        long start=0;
        long end=1;
        for (int i = 0; i <=n; i++) {
            long next=start+end;
            start=end;
            end=next;
        }
        return end;
    }




    public static void main(String[] args) {
        // Print first 15 Fibonacci numbers
        System.out.print("Fibonacci: ");
        for (int i = 0; i <= 14; i++) {
            System.out.print(fibIterative(i) + " ");
        }
        // 0 1 1 2 3 5 8 13 21 34 55 89 144 233 377

        System.out.println("\nfib(40) slow:  " + fiboRecu(40));   // slow!
        System.out.println("fib(40) fast:  " + fibFast(40));    // instant
        System.out.println("fib(90) iter:  " + fibIterative(90));// instant
    }
}