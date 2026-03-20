public class FinallyBlock {

    static  int divide(int a, int b){
        try{
            System.out.println("Attempting division");
            int result=a/b;
            System.out.println("Result: "+result);
            return result;
        }catch (ArithmeticException e){
            System.out.println("Caught Division by zero");
            return -1;
        }finally {
            System.out.println("finally block runs no matter what");
        }
    }
    public static void main(String[] args) {
        System.out.println("1.Normal division");
        System.out.println(divide(10,5));

        System.out.println("2.Division by zero");
        System.out.println(divide(10,0));
    }
}
