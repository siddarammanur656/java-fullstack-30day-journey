//Power Function — Custom Implementation
public class PowerFunction {

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

    public static void main(String[] args) {
        System.out.println(power(2, 10));   // 1024.0
        System.out.println(power(3, 0));    // 1.0
        System.out.println(power(2, -3));   // 0.125
        System.out.println(power(5, 4));    // 625.0
    }
}