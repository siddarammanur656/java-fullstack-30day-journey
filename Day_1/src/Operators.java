public class Operators {
    public static void main(String[] args) {
        int a=10;
        int b=3;

        System.out.println(a+b);//13
        System.out.println(a-b);//7
        System.out.println(a*b);//30
        System.out.println(a/b);//3 <- NOT 3.333 Integer division truncates
        System.out.println(a%b); // 1 <- remainder(modulo) - very useful

        //Fix integer division :
        System.out.println((double) a/b);
        System.out.println(a/(double)b);

        //compound assignment
        int score = 100;
        score +=10;  //110
        score -=5; //105
        score *=2; //210
        score /=3; //70
        score %=8; //6

        //Increment / Decrement - Order Matters
        int n=5;
        System.out.println(n++); // prints 5 , then increments -> n is now 6
        System.out.println(++n); //increments first -> n is now  7 , prints 7


    }
}